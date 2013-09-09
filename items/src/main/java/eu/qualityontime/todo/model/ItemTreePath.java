package eu.qualityontime.todo.model;

import org.javalite.activejdbc.Model;

public class ItemTreePath extends Model {
  // , ancestor int(11) not null
  // , descendant int(11) not null
  // , path_length int(11) not null
  
  @Override
  public String toString(){
    return "{ancestor="+get("ancestor")+", descendant="+get("descendant")+", path_length="+get("path_length")+"}";
  }
}
