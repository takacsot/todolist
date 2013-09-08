package eu.qualityontime.todo.model;

import org.junit.Test;

import eu.qualityontime.todo.TodoDbTest;

public class ItemHierarchyTest extends TodoDbTest {

  @Test
  public void create_single() {
    Item i = Item.create("name", "s", "state", "s");
    i.saveIt();
    //ItemTreePath.findAll().dump();
    //i.getAll(ItemTreePath.class).dump();
  }

  @Test
  public void assiocations() throws Exception {
    Item i1 = Item.createIt("name", "1");
    Item i2 = Item.createIt("name","2");
    ItemTreePath.createIt("ancestor", i1.getId(), "descendant", i2.getId(), "path_length", 1);
    Item.findAll().dump();
    ItemTreePath.findAll().dump();
    System.out.println("=======");
    System.out.println(i1.allDescendants());
    System.out.println(i1.directDescendants());
    System.out.println("=======");
    System.out.println(i2.ancestors());
    System.out.println(i2.ancestor());
    
  }
}
