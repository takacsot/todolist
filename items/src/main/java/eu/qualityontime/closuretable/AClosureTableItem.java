package eu.qualityontime.closuretable;

import org.javalite.activejdbc.*;

public abstract class AClosureTableItem<T extends Model & ClosureTableItem<?>> extends Model implements ClosureTableItem<T> {
  protected final ClosureTableItem<T> impl;
  protected Class<? extends Model> closure_table;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public AClosureTableItem(Class<? extends Model> closure_table) {
    super();
    this.closure_table = closure_table;
    this.impl = new ClosureTableItemImpl(this, closure_table);
  }

  @Override
  public void afterCreate() {
    Model.createIt(closure_table, "ancestor", getId(), "descendant", getId(), "path_length", 0);
  }


  @Override
  public void addDescendant(T m) {
    impl.addDescendant(m);
  }

  @Override
  public LazyList<T> ancestors() {
    return impl.ancestors();
  }

  @Override
  public T ancestor() {
    return impl.ancestor();
  }

  @Override
  public LazyList<T> allDescendants() {
    return impl.allDescendants();
  }

  @Override
  public LazyList<T> directDescendants() {
    return impl.directDescendants();
  }
}
