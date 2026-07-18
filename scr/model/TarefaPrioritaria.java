/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// src/model/TarefaPrioritaria.java
package model;

public class TarefaPrioritaria extends Tarefa {
    private int prioridade; // 1 = Alta, 2 = Média, 3 = Baixa
    
    public TarefaPrioritaria(int id, String titulo, String descricao, int prioridade) {
        super(id, titulo, descricao);
        this.prioridade = prioridade;
    }
    
    public int getPrioridade() {
        return prioridade;
    }
    
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    
    @Override
    public String getDetalhes() {
        String prioridadeStr = switch(prioridade) {
            case 1 -> "🔴 ALTA";
            case 2 -> "🟡 MÉDIA";
            case 3 -> "🟢 BAIXA";
            default -> "⚪ N/A";
        };
        return super.getDetalhes() + " | Prioridade: " + prioridadeStr;
    }
}