package eu.qualityontime.todo.model;

import static org.junit.Assert.*;

import org.javalite.activejdbc.validation.ValidationException;
import org.junit.*;

import eu.qualityontime.todo.TodoDbTest;

public class ItemTest extends TodoDbTest{

  @Test
  public void simple_create() {
    Item i = Item.create("name", "test", "state", "open");
    i.saveIt();
  }

  @Test(expected=ValidationException.class)
  @Ignore("temporarily")
  public void validate() throws Exception {
    Item.createIt("name","test");
  }
  
  @Test
  public void create_with_timelog() throws Exception {
    Item i = Item.create("name", "x", "state", "open");
    i.saveIt();
    Timelog t = Timelog.create("item_id", i.getId());
    i.add(t);
    assertTrue(i.save());
    assertEquals(1, i.getAll(Timelog.class).size());
    //Item.findAll().dump();
  }
}
