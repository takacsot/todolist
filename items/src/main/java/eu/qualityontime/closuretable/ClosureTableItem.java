package eu.qualityontime.closuretable;

import org.javalite.activejdbc.*;

public interface ClosureTableItem <T extends Model> {

  public void addDescendant(T m);

  public LazyList<T> ancestors();

  public T ancestor();

  public LazyList<T> allDescendants();

  public LazyList<T> directDescendants() ;

}
