/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/model/Tarefa.java
package model;

public class Tarefa implements ITarefa {
    private int id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    
    public Tarefa(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public String getTitulo() {
        return titulo;
    }
    
    @Override
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public boolean isConcluida() {
        return concluida;
    }
    
    @Override
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    
    @Override
    public String getDetalhes() {
        return String.format("[%d] %s - %s %s", 
            id, 
            titulo, 
            descricao, 
            concluida ? "✅ CONCLUÍDA" : "⏳ PENDENTE"
        );
    }
}