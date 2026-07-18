package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author rique
 */
public interface ITarefa {
    int getId();
     String getTitulo();
    String getDescricao();
    boolean isConcluida();
    void setConcluida(boolean concluida);
    String getDetalhes();
}
