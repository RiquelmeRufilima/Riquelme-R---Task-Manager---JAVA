package app;
import database.Conexao;
import dao.TarefaDAO;
import controller.TaskManager;
import controller.TelaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MainApplication extends Application {
 @Override public void start(Stage stage) throws Exception {
  Conexao banco=new Conexao();banco.inicializar();
  TaskManager servico=new TaskManager(new TarefaDAO(banco));
  FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
  loader.setControllerFactory(tipo->{if(tipo==TelaPrincipalController.class)return new TelaPrincipalController(servico);try{return tipo.getDeclaredConstructor().newInstance();}catch(Exception e){throw new RuntimeException(e);}});
  Scene cena=new Scene(loader.load(),920,620);
  cena.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());
  stage.setTitle("TaskManager - Gerenciador de Tarefas");stage.setScene(cena);stage.setMinWidth(750);stage.setMinHeight(480);stage.show();
 }
 public static void main(String[] args){launch(args);}
}
