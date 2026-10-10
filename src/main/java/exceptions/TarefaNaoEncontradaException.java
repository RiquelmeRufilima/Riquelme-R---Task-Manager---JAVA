package exceptions;
public class TarefaNaoEncontradaException extends Exception {
 public TarefaNaoEncontradaException(int id){super("Tarefa com ID "+id+" não foi encontrada.");}
}
