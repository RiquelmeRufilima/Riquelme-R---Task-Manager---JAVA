package repository;
import model.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class RepositorioGenericoTest {
 @Test void deveUsarExtendsSuperEWildcard(){
  RepositorioGenerico<ITarefa> repo=new RepositorioGenerico<>();
  List<TarefaPrioritaria> entradas=List.of(new TarefaPrioritaria(1,"A","",1));
  repo.adicionarTodos(entradas);
  List<Object> destino=new ArrayList<>();repo.copiarPara(destino);
  assertEquals(1,RepositorioGenerico.contar(destino));
  assertEquals(1,repo.buscar(t->t.getId()==1).size());
  assertTrue(repo.remover(entradas.get(0)));
 }
}
