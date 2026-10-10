package controller;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import model.TarefaRegistro;
import java.util.*;
public class TelaPrincipalController {
 private final TaskManager tarefas;
 public TelaPrincipalController(TaskManager tarefas){this.tarefas=tarefas;}
 @FXML private TableView<TarefaRegistro> tabela;
 @FXML private TableColumn<TarefaRegistro,String> colunaTitulo,colunaDescricao,colunaPrioridade,colunaStatus;
 @FXML private TableColumn<TarefaRegistro,Integer> colunaId;
 @FXML private Label contador;
 @FXML private TextField campoBusca;
 @FXML private CheckBox apenasPendentes;
 private List<TarefaRegistro> lista=List.of();
 @FXML public void initialize(){
  colunaId.setCellValueFactory(c->new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId()));
  colunaTitulo.setCellValueFactory(c->new javafx.beans.property.SimpleStringProperty(c.getValue().getTitulo()));
  colunaDescricao.setCellValueFactory(c->new javafx.beans.property.SimpleStringProperty(c.getValue().getDescricao()));
  colunaPrioridade.setCellValueFactory(c->new javafx.beans.property.SimpleStringProperty(c.getValue().getPrioridadeTexto()));
  colunaStatus.setCellValueFactory(c->new javafx.beans.property.SimpleStringProperty(c.getValue().getStatusTexto()));
  campoBusca.textProperty().addListener((obs,antigo,novo)->filtrar());
  apenasPendentes.setOnAction(e->filtrar());
  atualizar();
 }
 @FXML private void atualizar(){
  try{lista=tarefas.listarTarefas();filtrar();}
  catch(Exception ex){erro("Erro ao consultar as tarefas",ex);}
 }
 private void filtrar(){
  String termo=campoBusca.getText()==null?"":campoBusca.getText().toLowerCase(Locale.ROOT).trim();
  var filtrada=lista.stream().filter(t->(!apenasPendentes.isSelected()||!t.isConcluida()) && (t.getTitulo().toLowerCase(Locale.ROOT).contains(termo)||t.getDescricao().toLowerCase(Locale.ROOT).contains(termo))).toList();
  tabela.getItems().setAll(filtrada);
  long concluidas=lista.stream().filter(TarefaRegistro::isConcluida).count();
  contador.setText("Total: "+lista.size()+"  •  Pendentes: "+(lista.size()-concluidas)+"  •  Concluídas: "+concluidas);
 }
 @FXML private void novaTarefa(){abrirFormulario(null);}
 @FXML private void editarSelecionada(){
  TarefaRegistro selecionada=tabela.getSelectionModel().getSelectedItem();
  if(selecionada==null){aviso("Selecione uma tarefa para editar.");return;}
  abrirFormulario(selecionada);
 }
 private void abrirFormulario(TarefaRegistro existente){
  try{
   FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/formulario.fxml"));
   Parent root=loader.load();
   FormularioController formulario=loader.getController();formulario.configurar(tarefas,existente);
   Stage janela=new Stage();janela.initOwner(tabela.getScene().getWindow());janela.initModality(Modality.WINDOW_MODAL);
   janela.setTitle(existente==null?"Nova tarefa":"Editar tarefa");
   Scene cena=new Scene(root,470,380);cena.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());
   janela.setScene(cena);janela.showAndWait();if(formulario.foiSalvo())atualizar();
  }catch(Exception ex){erro("Erro ao abrir formulário",ex);}
 }
 @FXML private void alternarConclusao(){
  TarefaRegistro selecionada=tabela.getSelectionModel().getSelectedItem();
  if(selecionada==null){aviso("Selecione uma tarefa.");return;}
  try{tarefas.definirConclusao(selecionada.getId(),!selecionada.isConcluida());atualizar();}
  catch(Exception ex){erro("Erro ao alterar status",ex);}
 }
 @FXML private void excluirSelecionada(){
  TarefaRegistro selecionada=tabela.getSelectionModel().getSelectedItem();
  if(selecionada==null){aviso("Selecione uma tarefa para excluir.");return;}
  Alert confirmar=new Alert(Alert.AlertType.CONFIRMATION,"Deseja realmente excluir a tarefa ‘"+selecionada.getTitulo()+"’?",ButtonType.YES,ButtonType.NO);
  confirmar.setHeaderText("Confirmar exclusão");
  if(confirmar.showAndWait().orElse(ButtonType.NO)==ButtonType.YES){
   try{tarefas.removerTarefa(selecionada.getId());atualizar();}catch(Exception ex){erro("Erro ao excluir tarefa",ex);}
  }
 }
 private void aviso(String msg){new Alert(Alert.AlertType.INFORMATION,msg,ButtonType.OK).showAndWait();}
 private void erro(String msg,Exception ex){new Alert(Alert.AlertType.ERROR,msg+"\n"+ex.getMessage(),ButtonType.OK).showAndWait();}
}
