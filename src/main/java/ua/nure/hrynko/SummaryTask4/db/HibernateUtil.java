package ua.nure.hrynko.SummaryTask4.db;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ua.nure.hrynko.SummaryTask4.db.dto.*;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(Orders.class);
            configuration.addAnnotatedClass(OrdersMenu.class);
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

