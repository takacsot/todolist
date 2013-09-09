package eu.qualityontime.closuretable;

import java.util.List;

import org.javalite.activejdbc.*;

public class ClosureTableItemImpl<T extends Model & ClosureTableItem<?>> implements ClosureTableItem<T> {
  private final T baseObj;
  private final Class<? extends Model> closure_table;

  public ClosureTableItemImpl(T baseObj, Class<? extends Model> closure_table) {
    super();
    this.baseObj = baseObj;
    this.closure_table = closure_table;
  }

  @Override
  public void addDescendant(T m) {
    List<? extends Model> closure_recs = Model.where(closure_table, "ancestor = ?", m.getId());
    for (Model r : closure_recs) {
      Model closure_row = Model.create(closure_table, "ancestor", baseObj.getId(), "descendant", r.get("descendant"),
          "path_length", r.getLong("path_length") + 1);
      closure_row.saveIt();
    }
  }

  @Override
  public LazyList<T> ancestors() {
    return (LazyList<T>) Model.findBySQL(
        baseObj.getClass(),
        "select s.* from " + Model.getTableName(baseObj.getClass()) + " as s " + "join "
            + Model.getTableName(closure_table)
            + " as t on(s.id = t.ancestor) where t.descendant = ? order by path_length desc", baseObj.getId());
  }

  @Override
  public T ancestor() {
    List<T> ans = ancestors();
    return 0 == ans.size() ? null : ans.get(0);
  }

  @Override
  public LazyList<T> allDescendants() {
    return (LazyList<T>) Model
        .findBySQL(
            baseObj.getClass(),
            "select s.* from "
                + Model.getTableName(baseObj.getClass())
                + " as s "
                + "join "
                + Model.getTableName(closure_table)
                + " as t on(s.id = t.descendant) where t.ancestor = ? and t.ancestor <> t.descendant order by path_length ",
            baseObj.getId());
  }

  @Override
  public LazyList<T> directDescendants() {
    return (LazyList<T>) Model.findBySQL(
        baseObj.getClass(),
        "select s.* from " + Model.getTableName(baseObj.getClass()) + " as s " + "join "
            + Model.getTableName(closure_table)
            + " as t on(s.id = t.descendant) where t.ancestor = ? and t.path_length=1 order by path_length ",
        baseObj.getId());
  }

}
