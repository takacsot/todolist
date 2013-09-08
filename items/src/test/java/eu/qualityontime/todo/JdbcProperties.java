package eu.qualityontime.todo;

import java.util.Properties;

public class JdbcProperties {

  @SuppressWarnings("unused")
  private static final JdbcProperties JDBC_PROPERTIES = new JdbcProperties();

  private static String driver, url, user, password, db;

  private JdbcProperties() {
    try {
      Properties jdbcProperties = new Properties();
      jdbcProperties.load(getClass().getResourceAsStream("/jdbc.properties"));
      driver = jdbcProperties.getProperty("jdbc.driver");
      url = jdbcProperties.getProperty("jdbc.url");
      user = jdbcProperties.getProperty("jdbc.user");
      password = jdbcProperties.getProperty("jdbc.password");
      db = jdbcProperties.getProperty("db");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String driver() {
    return driver;
  }

  public static String url() {
    return url;
  }

  public static String user() {
    return user;
  }

  public static String password() {
    return password;
  }

  public static String db() {
    return db;
  }
}
