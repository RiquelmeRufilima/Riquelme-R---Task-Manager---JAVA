package controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.TarefaRegistro;
public class FormularioController {
 @FXML private TextField titulo;
 @FXML private TextArea descricao;
 @FXML private ComboBox<String> prioridade;
 @FXML private CheckBox concluida;
 private TaskManager servico;
 private TarefaRegistro existente;
 private boolean salvo;
 @FXML private void initialize(){prioridade.getItems().addAll("Normal","Alta","Média","Baixa");prioridade.getSelectionModel().selectFirst();}
 public void configurar(TaskManager servico,TarefaRegistro existente){
  this.servico=servico;this.existente=existente;
  if(existente!=null){titulo.setText(existente.getTitulo());descricao.setText(existente.getDescricao());prioridade.getSelectionModel().select(existente.getPrioridade()==null?0:existente.getPrioridade());concluida.setSelected(existente.isConcluida());}
 }
 @FXML private void salvar(){
  try{
   String nome=titulo.getText();String detalhe=descricao.getText();
   int indice=prioridade.getSelectionModel().getSelectedIndex();Integer nivel=indice==0?null:indice;
   if(existente==null){
    TarefaRegistro nova=nivel==null?servico.criarTarefa(nome,detalhe):servico.criarTarefaPrioritaria(nome,detalhe,nivel);
    if(concluida.isSelected())servico.definirConclusao(nova.getId(),true);
   }else{
    // não altera objeto selecionado antes da validação
    TarefaRegistro modificada=new TarefaRegistro(existente.getId(),nome,detalhe,concluida.isSelected(),nivel);
    servico.editarTarefa(modificada);
   }
   salvo=true;fechar();
  }catch(Exception ex){new Alert(Alert.AlertType.ERROR,"Não foi possível salvar:
"+ex.getMessage(),ButtonType.OK).showAndWait();}
 }
 @FXML private void cancelar(){fechar();}
 private void fechar(){((Stage)titulo.getScene().getWindow()).close();}
 public boolean foiSalvo(){return salvo;}
}
