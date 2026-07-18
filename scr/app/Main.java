/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;


import controller.TaskManager;
import exceptions.TarefaNaoEncontradaException;
import model.ITarefa;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author rique
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();
    
 public static void main(String[] args) {
        System.out.println("🚀 Iniciando TaskManager...\n");
        
        boolean executando = true;
        
        while(executando){
         try{
         exibirMenu();
         int opcao = lerOpcao();
         
         // Switch para gerenciar as opções
                switch (opcao) {
                    case 1:
                        criarNovaTarefa();
                        break;
                    case 2:
                        taskManager.listarTarefas();
                        break;
                    case 3:
                        concluirTarefa();
                        break;
                    case 4:
                        removerTarefa();
                        break;
                    case 5:
                        System.out.println("👋 Saindo do TaskManager... Até logo!");
                        executando = false;
                        break;
                    default:
                        System.out.println("❌ Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Erro: Digite um número válido!");
                scanner.nextLine(); // Limpar buffer
            } catch (TarefaNaoEncontradaException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
  private static void exibirMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("    📌 GERENCIADOR DE TAREFAS");
        System.out.println("=".repeat(40));
        System.out.println("1 - 📝 Criar nova tarefa");
        System.out.println("2 - 📋 Listar tarefas");
        System.out.println("3 - ✅ Concluir tarefa");
        System.out.println("4 - 🗑️ Remover tarefa");
        System.out.println("5 - 🚪 Sair");
        System.out.println("=".repeat(40));
        System.out.print("Escolha uma opção: ");
    }
    
  private static int lerOpcao() throws InputMismatchException {
        return scanner.nextInt();
    }
    
        private static void criarNovaTarefa() {
        System.out.print("📌 Título da tarefa: ");
        scanner.nextLine(); // Limpar buffer
        String titulo = scanner.nextLine();
        
        System.out.print("📝 Descrição (opcional): ");
        String descricao = scanner.nextLine();
        
        System.out.print("🔢 É uma tarefa prioritária? (S/N): ");
        String resposta = scanner.nextLine().toUpperCase();
        
         ITarefa tarefa;
        if (resposta.equals("S")) {
            System.out.print("📊 Nível de prioridade (1-Alta, 2-Média, 3-Baixa): ");
            int prioridade = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            tarefa = taskManager.criarTarefaPrioritaria(titulo, descricao, prioridade);
        } else {
            tarefa = taskManager.criarTarefa(titulo, descricao);
        }
        
        System.out.println("✅ Tarefa criada com sucesso! (ID: " + tarefa.getId() + ")");
    }
    
    private static void concluirTarefa() throws TarefaNaoEncontradaException {
        System.out.print("🔢 ID da tarefa a ser concluída: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        taskManager.concluirTarefa(id);
    }
    
    private static void removerTarefa() throws TarefaNaoEncontradaException {
        System.out.print("🔢 ID da tarefa a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        taskManager.removerTarefa(id);
    }
 
 
 
 
}
    
    
         
           
            
            
       
 
 

