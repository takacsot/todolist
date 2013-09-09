package eu.qualityontime.todo.model;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.qualityontime.todo.TodoDbTest;

public class ItemHierarchyTest extends TodoDbTest {

  @Test
  public void create_single() throws Exception {
    cleanTable("item_tree_paths", "items");
    Item i = Item.create("name", "s", "state", "s");
    i.saveIt();
    //ItemTreePath.findAll().dump();
    //i.getAll(ItemTreePath.class).dump();
  }

  @Test
  public void assiocations() throws Exception {
    cleanTable("item_tree_paths", "items");
    Item i1 = Item.createIt("name", "1");
    Item i2 = Item.createIt("name", "2");
    ItemTreePath.createIt("ancestor", i1.getId(), "descendant", i2.getId(), "path_length", 1);
    //    Item.findAll().dump();
    //    ItemTreePath.findAll().dump();
    //    System.out.println("=======");
    //    System.out.println(i1.allDescendants());
    //    System.out.println(i1.directDescendants());
    //    System.out.println("=======");
    //    System.out.println(i2.ancestors());
    //    System.out.println(i2.ancestor());
  }

  @Test
  public void addLeaf() throws Exception {
    cleanTable("item_tree_paths", "items");
    Item parent = new Item("p");
    Item leaf = new Item("leaf");
    parent.saveIt();
    leaf.saveIt();
    //    Item.findAll().dump();
    //    ItemTreePath.findAll().dump();
    assertEquals(0, parent.allDescendants().size());
    parent.addDescendant(leaf);
    assertEquals(1, parent.allDescendants().size());
  }

  @Test
  public void add_subtree() throws Exception {
    ItemTreePath.deleteAll();
    Item.deleteAll();

    Item subtree = new Item("subtree root");
    subtree.saveIt();
    Item leaf = Item.createIt("name", "subleaf");
    subtree.addDescendant(leaf);
    Item new_parent = Item.createIt("name", "new root", "state", "x");
    new_parent.addDescendant(subtree);
    ItemTreePath.findAll().dump();
    assertEquals(2, new_parent.allDescendants().size());
  }
}
