package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TarefaTest {
 @Test void deveCriarTarefa(){Tarefa t=new Tarefa(1,"Estudar","Java");assertEquals("Estudar",t.getTitulo());assertFalse(t.isConcluida());}
 @Test void deveConcluirTarefa(){Tarefa t=new Tarefa(1,"Estudar","");t.setConcluida(true);assertTrue(t.isConcluida());}
 @Test void deveRejeitarTituloVazio(){assertThrows(IllegalArgumentException.class,()->new Tarefa(1,"  ",""));}
 @Test void deveValidarPrioridade(){assertThrows(IllegalArgumentException.class,()->new TarefaPrioritaria(1,"Teste","",4));}
}
