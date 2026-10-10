package database;
import java.sql.*;
import java.nio.file.*;
/** Banco real em ~/.taskmanager/tarefas.db; também aceita URL JDBC para testes. */
public class Conexao {
 private final String url;
 public Conexao(){
  this(criarUrlPadrao());
 }
 public Conexao(String url){this.url=url;}
 private static String criarUrlPadrao(){
  try {Path pasta=Path.of(System.getProperty("user.home"),".taskmanager");Files.createDirectories(pasta);
   return "jdbc:sqlite:"+pasta.resolve("tarefas.db").toAbsolutePath();
  }catch(Exception e){throw new IllegalStateException("Não foi possível preparar a pasta de dados.",e);}
 }
 public Connection conectar() throws SQLException {return DriverManager.getConnection(url);}
 public void inicializar() throws SQLException {
  try(Connection c=conectar();Statement stmt=c.createStatement()){
   stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, descricao TEXT NOT NULL DEFAULT '', concluida INTEGER NOT NULL DEFAULT 0, prioridade INTEGER CHECK(prioridade IS NULL OR prioridade BETWEEN 1 AND 3))");
  }
 }
}
