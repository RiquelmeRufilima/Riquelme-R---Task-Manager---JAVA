package model;
public interface ITarefa {
 int getId(); String getTitulo(); String getDescricao(); boolean isConcluida();
 void setConcluida(boolean concluida); String getDetalhes();
}
