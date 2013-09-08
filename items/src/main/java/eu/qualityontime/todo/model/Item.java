package eu.qualityontime.todo.model;

import eu.qualityontime.closuretable.AClosureTableItem;


public class Item extends AClosureTableItem<Item> {
  static{
    validatePresenceOf("name"/*, "state"*/);
  }
  
  public Item(){
    super(ItemTreePath.class);
  }
}
