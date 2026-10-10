package model;
public class TarefaPrioritaria extends Tarefa {
 private int prioridade;
 public TarefaPrioritaria(int id,String titulo,String descricao,int prioridade){super(id,titulo,descricao);setPrioridade(prioridade);}
 public int getPrioridade(){return prioridade;}
 public void setPrioridade(int prioridade){
  if(prioridade<1||prioridade>3) throw new IllegalArgumentException("Prioridade deve ser 1 (alta), 2 (média) ou 3 (baixa).");
  this.prioridade=prioridade;
 }
 @Override public String getDetalhes(){return super.getDetalhes()+" | Prioridade: "+switch(prioridade){case 1->"ALTA";case 2->"MÉDIA";default->"BAIXA";};}
}
