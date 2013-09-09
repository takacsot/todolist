package eu.qualityontime.closuretable;

import org.javalite.activejdbc.*;

@SuppressWarnings("rawtypes")
public interface ClosureTableItem <T extends Model & ClosureTableItem> {

  public void addDescendant(T m);

  public LazyList<T> ancestors();

  public T ancestor();

  public LazyList<T> allDescendants();

  public LazyList<T> directDescendants() ;

}
