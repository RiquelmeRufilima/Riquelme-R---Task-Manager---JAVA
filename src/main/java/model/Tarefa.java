package model;
public class Tarefa implements ITarefa {
 private final int id;
 private String titulo;
 private String descricao;
 private boolean concluida;
 public Tarefa(int id, String titulo, String descricao) {
  this.id=id; setTitulo(titulo); setDescricao(descricao);
 }
 public int getId(){return id;}
 public String getTitulo(){return titulo;}
 public void setTitulo(String titulo){
  if(titulo==null || titulo.isBlank()) throw new IllegalArgumentException("Informe o título da tarefa.");
  this.titulo=titulo.trim();
 }
 public String getDescricao(){return descricao;}
 public void setDescricao(String descricao){this.descricao=descricao==null?"":descricao.trim();}
 public boolean isConcluida(){return concluida;}
 public void setConcluida(boolean concluida){this.concluida=concluida;}
 public String getDetalhes(){return String.format("[%d] %s - %s %s",id,titulo,descricao,concluida?"CONCLUÍDA":"PENDENTE");}
}
