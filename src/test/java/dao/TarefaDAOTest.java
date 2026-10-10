package dao;
import database.Conexao;
import model.TarefaRegistro;
import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
class TarefaDAOTest {
 private Connection memoria;
 private TarefaDAO dao;
 @BeforeEach void preparar() throws Exception {
  // A mesma conexão âncora mantém o banco SQLite em memória vivo durante o teste.
  String url="jdbc:sqlite:file:testes"+java.util.UUID.randomUUID()+"?mode=memory&cache=shared";
  memoria=DriverManager.getConnection(url);
  Conexao banco=new Conexao(url);banco.inicializar();dao=new TarefaDAO(banco);
 }
 @AfterEach void finalizar() throws Exception {memoria.close();}
 @Test void deveInserirEConsultar() throws Exception {
  TarefaRegistro t=dao.inserir("Comprar pão","Na padaria",2);
  assertTrue(t.getId()>0);assertEquals("Comprar pão",dao.buscarPorId(t.getId()).orElseThrow().getTitulo());
  assertEquals(1,dao.listar().size());
 }
 @Test void deveAtualizarConcluirEExcluir() throws Exception {
  TarefaRegistro t=dao.inserir("Antiga","",null);
  TarefaRegistro modificada=new TarefaRegistro(t.getId(),"Nova","Detalhes",false,1);
  assertTrue(dao.atualizar(modificada));
  assertTrue(dao.alterarConclusao(t.getId(),true));
  assertEquals("Nova",dao.buscarPorId(t.getId()).orElseThrow().getTitulo());
  assertTrue(dao.buscarPorId(t.getId()).orElseThrow().isConcluida());
  assertTrue(dao.excluir(t.getId()));assertTrue(dao.buscarPorId(t.getId()).isEmpty());
 }
 @Test void deveRejeitarTituloInvalido() {assertThrows(IllegalArgumentException.class,()->dao.inserir(" ","",null));}
}
