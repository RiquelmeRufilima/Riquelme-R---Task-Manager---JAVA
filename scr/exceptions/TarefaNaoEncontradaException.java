/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author rique
 */
public class TarefaNaoEncontradaException extends Exception {
    public TarefaNaoEncontradaException(int id){
        super("Tarefa com ID " + id + " não foi encontrada.");  
    }
       public TarefaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
