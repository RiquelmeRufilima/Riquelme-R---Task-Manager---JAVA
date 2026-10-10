package controller;
import dao.TarefaDAO;
import model.TarefaRegistro;
import repository.RepositorioGenerico;
import java.sql.SQLException;
import java.util.List;
/** Serviço intermediário, agora persistido em SQLite (não apenas em memória). */
public class TaskManager {
 private final TarefaDAO dao;
 public TaskManager(TarefaDAO dao){this.dao=dao;}
 public TarefaRegistro criarTarefa(String titulo,String descricao) throws SQLException {return dao.inserir(titulo,descricao,null);}
 public TarefaRegistro criarTarefaPrioritaria(String titulo,String descricao,int prioridade) throws SQLException {return dao.inserir(titulo,descricao,prioridade);}
 public List<TarefaRegistro> listarTarefas() throws SQLException {
  RepositorioGenerico<TarefaRegistro> repo=new RepositorioGenerico<>();
  repo.adicionarTodos(dao.listar());return repo.listar();
 }
 public void concluirTarefa(int id) throws SQLException {dao.alterarConclusao(id,true);}
 public void removerTarefa(int id) throws SQLException {dao.excluir(id);}
 public void editarTarefa(TarefaRegistro tarefa) throws SQLException {dao.atualizar(tarefa);}
 public void definirConclusao(int id,boolean concluida) throws SQLException {dao.alterarConclusao(id,concluida);}
}
