package project.util;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import project.exception.WebAppException;
import project.model.RequestLog;
import project.model.Student;
import project.model.StudyClass;

public class HibernateUtil {

  private static SessionFactory sessionFactory;

  /**
   * This method returns the instance of SessionFactory
   *
   * @return
   */
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/aston");
        settings.put(Environment.USER, "postgres");
        settings.put(Environment.PASS, "postgres");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.ISOLATION, "2");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(StudyClass.class);
        configuration.addAnnotatedClass(RequestLog.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
      } catch (Exception e) {
        e.printStackTrace();
        throw new WebAppException("Exception during creation of sessionFactory");
      }
    }
    return sessionFactory;
  }
}
