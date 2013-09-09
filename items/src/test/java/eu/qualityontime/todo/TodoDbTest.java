package eu.qualityontime.todo;

import static eu.qualityontime.todo.JdbcProperties.*;

import java.io.*;
import java.sql.*;

import org.apache.commons.lang.StringUtils;
import org.javalite.activejdbc.*;
import org.junit.*;

import eu.qualityontime.closuretable.ClosureTableItem;
import eu.qualityontime.todo.model.*;

public abstract class TodoDbTest {
  protected ModelContext<Item> Item = Model.with(Item.class);
  protected ModelContext<Timelog> Timelog = Model.with(Timelog.class);
  protected ModelContext<ItemTreePath> ItemTreePath = Model.with(ItemTreePath.class);
  static boolean schemaGenerated = false;

  @Before
  public void setup() throws Exception {
    Base.open(driver(), url(), user(), password());
    if (!schemaGenerated) {
      generateSchema();
      schemaGenerated = true;
      System.out.println("DB: " + db() + ", Driver:" + driver());
    }

    Base.connection().setAutoCommit(false);
  }

  @After
  public void after() {

    try {
      Base.connection().rollback();
    } catch (SQLException e) {
      e.printStackTrace(); // To change body of catch statement use File |
                           // Settings | File Templates.
    }
    Base.close();
  }

  protected void generateSchema() throws SQLException, ClassNotFoundException {
    resetSchema(getStatements(";", "h2_schema.sql"));
  }

  public String[] getStatements(String delimiter, String file) {
    try {

      System.out.println("Getting statements from file: " + file);
      InputStreamReader isr = new InputStreamReader(TodoDbTest.class.getClassLoader().getResourceAsStream(file));
      BufferedReader reader = new BufferedReader(isr);
      StringBuffer text = new StringBuffer();
      String t;
      while ((t = reader.readLine()) != null) {
        text.append(t + '\n');
      }
      return text.toString().split(delimiter);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static void resetSchema(String[] statements) throws SQLException {
    Connection connection = Base.connection();
    for (String statement : statements) {
      if (StringUtils.isBlank(statement))
        continue;
      System.out.println(statement);
      Statement st = connection.createStatement();
      st.executeUpdate(statement);
      st.close();
    }
  }

  protected void cleanTable(String... tables) throws SQLException {
    Connection con = Base.connection();
    for (String table : tables) {
      String delSql = "delete from " + table;
      Statement s = con.createStatement();
      s.executeUpdate(delSql);
      s.close();
    }
  }

  protected void printTree(ClosureTableItem<?> o){
    System.out.println(toTree(o));
  }

  protected String toTree(ClosureTableItem<?> obj) {
    StringBuilder sb = new StringBuilder();
    toTree(0, obj, sb);
    return sb.toString();
  }

  @SuppressWarnings("rawtypes")
  private void toTree(int i, ClosureTableItem<?> obj, StringBuilder sb) {
    for(int j = 0; j<i;j++){
      sb.append(" ");
    }
    sb.append(obj.toString()).append("\n");
    for(Model ch: obj.directDescendants()){
      toTree(i+2, (ClosureTableItem)ch, sb);
    }
  }
}
