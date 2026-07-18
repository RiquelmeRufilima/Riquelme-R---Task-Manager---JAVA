/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import exceptions.TarefaNaoEncontradaException;
import model.ITarefa;
import model.Tarefa;
import model.TarefaPrioritaria;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rique
 */
public class TaskManager {
    private List<ITarefa> tarefas;
    private int proximoId;
    public TaskManager(){
    this.tarefas = new ArrayList<>();
    this.proximoId = 1;
    }
    
     // Criar nova tarefa
    public ITarefa criarTarefa(String titulo, String descricao) {
        Tarefa tarefa = new Tarefa(proximoId++, titulo, descricao);
        tarefas.add(tarefa);
        return tarefa;
    }
    
    // Criar tarefa prioritária
    public ITarefa criarTarefaPrioritaria(String titulo, String descricao, int prioridade) {
        TarefaPrioritaria tarefa = new TarefaPrioritaria(proximoId++, titulo, descricao, prioridade);
        tarefas.add(tarefa);
        return tarefa;
    }
    
    // Listar todas as tarefas
    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa cadastrada.");
            return;
        }
        
        System.out.println("\n📋 LISTA DE TAREFAS:");
        System.out.println("=".repeat(60));
        for (ITarefa tarefa : tarefas) {
            System.out.println(tarefa.getDetalhes());
        }
        System.out.println("=".repeat(60));
        System.out.println("Total: " + tarefas.size() + " tarefa(s)");
    }
    
    // Concluir tarefa
    public void concluirTarefa(int id) throws TarefaNaoEncontradaException {
        for (ITarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setConcluida(true);
                System.out.println("✅ Tarefa '" + tarefa.getTitulo() + "' concluída com sucesso!");
                return;
            }
        }
        throw new TarefaNaoEncontradaException(id);
    }
    
    // Remover tarefa
    public void removerTarefa(int id) throws TarefaNaoEncontradaException {
        for (ITarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefas.remove(tarefa);
                System.out.println("🗑️ Tarefa '" + tarefa.getTitulo() + "' removida com sucesso!");
                return;
            }
        }
        throw new TarefaNaoEncontradaException(id);
    }
    
    // Buscar tarefa por ID
    public ITarefa buscarTarefa(int id) throws TarefaNaoEncontradaException {
        for (ITarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        throw new TarefaNaoEncontradaException(id);
    }
}
