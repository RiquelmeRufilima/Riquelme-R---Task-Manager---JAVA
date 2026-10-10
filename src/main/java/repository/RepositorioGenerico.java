package repository;
import java.util.*;
import java.util.function.Predicate;
/** Demonstra T, ?, ? extends T e ? super T. */
public class RepositorioGenerico<T> {
 private final List<T> itens=new ArrayList<>();
 public void adicionar(T item){itens.add(Objects.requireNonNull(item));}
 public void adicionarTodos(Collection<? extends T> novos){itens.addAll(novos);}
 public List<T> listar(){return List.copyOf(itens);}
 public List<T> buscar(Predicate<? super T> filtro){return itens.stream().filter(filtro).toList();}
 public boolean remover(T item){return itens.remove(item);}
 public void copiarPara(Collection<? super T> destino){destino.addAll(itens);}
 public static int contar(Collection<?> colecao){return colecao.size();}
}
