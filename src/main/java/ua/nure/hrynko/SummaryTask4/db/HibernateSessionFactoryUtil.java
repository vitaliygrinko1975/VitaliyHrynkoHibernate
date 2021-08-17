package ua.nure.hrynko.SummaryTask4.db;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ua.nure.hrynko.SummaryTask4.db.dto.*;

public class HibernateSessionFactoryUtil  {
    private static  SessionFactory sessionFactory;

    static {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Cars.class);
            configuration.addAnnotatedClass(Orders.class);
            configuration.addAnnotatedClass(OrdersCars.class);
            configuration.addAnnotatedClass(Users.class);
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
   }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

