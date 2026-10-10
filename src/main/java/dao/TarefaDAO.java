package dao;
import database.Conexao;
import model.TarefaRegistro;
import java.sql.*;
import java.util.*;
public class TarefaDAO {
 private final Conexao banco;
 public TarefaDAO(Conexao banco){this.banco=banco;}
 public TarefaRegistro inserir(String titulo,String descricao,Integer prioridade) throws SQLException {
  TarefaRegistro validador=new TarefaRegistro(0,titulo,descricao,false,prioridade);
  String sql="INSERT INTO tarefas (titulo,descricao,concluida,prioridade) VALUES (?,?,?,?)";
  try(Connection c=banco.conectar();PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
   ps.setString(1,validador.getTitulo());ps.setString(2,validador.getDescricao());ps.setInt(3,0);
   definirPrioridade(ps,4,prioridade);ps.executeUpdate();
   try(ResultSet chaves=ps.getGeneratedKeys()){
    if(chaves.next()) return new TarefaRegistro(chaves.getInt(1),validador.getTitulo(),validador.getDescricao(),false,prioridade);
   }
   throw new SQLException("Não foi possível obter o ID da nova tarefa.");
  }
 }
 public List<TarefaRegistro> listar() throws SQLException {
  List<TarefaRegistro> lista=new ArrayList<>();
  try(Connection c=banco.conectar();PreparedStatement ps=c.prepareStatement("SELECT id,titulo,descricao,concluida,prioridade FROM tarefas ORDER BY id DESC");ResultSet rs=ps.executeQuery()){
   while(rs.next()) lista.add(mapear(rs));
  }
  return lista;
 }
 public Optional<TarefaRegistro> buscarPorId(int id) throws SQLException {
  try(Connection c=banco.conectar();PreparedStatement ps=c.prepareStatement("SELECT id,titulo,descricao,concluida,prioridade FROM tarefas WHERE id=?")){
   ps.setInt(1,id);try(ResultSet rs=ps.executeQuery()){return rs.next()?Optional.of(mapear(rs)):Optional.empty();}
  }
 }
 public boolean atualizar(TarefaRegistro tarefa) throws SQLException {
  // validação antecipada via modelo
  tarefa.setTitulo(tarefa.getTitulo());tarefa.setPrioridade(tarefa.getPrioridade());
  try(Connection c=banco.conectar();PreparedStatement ps=c.prepareStatement("UPDATE tarefas SET titulo=?,descricao=?,concluida=?,prioridade=? WHERE id=?")){
   ps.setString(1,tarefa.getTitulo());ps.setString(2,tarefa.getDescricao());ps.setInt(3,tarefa.isConcluida()?1:0);
   definirPrioridade(ps,4,tarefa.getPrioridade());ps.setInt(5,tarefa.getId());return ps.executeUpdate()>0;
  }
 }
 public boolean alterarConclusao(int id,boolean concluida) throws SQLException {
  try(Connection c=banco.conectar();PreparedStatement ps=c.prepareStatement("UPDATE tarefas SET concluida=? WHERE id=?")){
   ps.setInt(1,concluida?1:0);ps.setInt(2,id);return ps.executeUpdate()>0;
  }
 }
 public boolean excluir(int id) throws SQLException {
  try(Connection c=banco.conectar();PreparedStatement ps=c.prepareStatement("DELETE FROM tarefas WHERE id=?")){
   ps.setInt(1,id);return ps.executeUpdate()>0;
  }
 }
 private static void definirPrioridade(PreparedStatement ps,int indice,Integer prioridade) throws SQLException {
  if(prioridade==null) ps.setNull(indice,Types.INTEGER);else ps.setInt(indice,prioridade);
 }
 private static TarefaRegistro mapear(ResultSet rs) throws SQLException {
  int p=rs.getInt("prioridade");Integer prioridade=rs.wasNull()?null:p;
  return new TarefaRegistro(rs.getInt("id"),rs.getString("titulo"),rs.getString("descricao"),rs.getInt("concluida")==1,prioridade);
 }
}
