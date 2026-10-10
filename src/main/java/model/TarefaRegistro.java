package model;
/** Mantém os dados da tarefa incluindo sua prioridade opcional para o formulário e a tabela. */
public class TarefaRegistro extends Tarefa {
 private Integer prioridade;
 public TarefaRegistro(int id,String titulo,String descricao,boolean concluida,Integer prioridade){
  super(id,titulo,descricao);setConcluida(concluida);setPrioridade(prioridade);
 }
 public Integer getPrioridade(){return prioridade;}
 public void setPrioridade(Integer prioridade){
  if(prioridade!=null&&(prioridade<1||prioridade>3)) throw new IllegalArgumentException("Prioridade inválida.");
  this.prioridade=prioridade;
 }
 public String getPrioridadeTexto(){return prioridade==null?"Normal":switch(prioridade){case 1->"Alta";case 2->"Média";default->"Baixa";};}
 public String getStatusTexto(){return isConcluida()?"Concluída":"Pendente";}
}
