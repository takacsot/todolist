package eu.qualityontime.todo.model;

import org.javalite.activejdbc.Model;

public class Tag extends Model {
  static{
    validatePresenceOf("name");
  }
}
