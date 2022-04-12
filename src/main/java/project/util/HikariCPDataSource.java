package project.util;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {

  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource dataSource;
  
  static {
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/aston");
    config.setUsername("postgres");
    config.setPassword("postgres");
    config.setDriverClassName("org.postgresql.Driver");
    dataSource = new HikariDataSource(config);
  }

  /**
   * This method returns connection to the database, provided by the Hikari DataSource.
   *
   * @return
   * @throws SQLException
   */
  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
  
  private HikariCPDataSource() {};
}
