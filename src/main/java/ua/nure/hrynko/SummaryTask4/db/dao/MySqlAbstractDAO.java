package ua.nure.hrynko.SummaryTask4.db.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.nure.hrynko.SummaryTask4.db.HibernateSessionFactoryUtil;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.AbstractDAO;
import org.hibernate.query.Query;
import java.util.List;

public abstract class MySqlAbstractDAO <T> implements AbstractDAO {

        protected SessionFactory factory = HibernateSessionFactoryUtil.getSessionFactory();
        protected final Class aClass;
        protected final String tableName;
        protected MySqlAbstractDAO(Class aClass, String tableName) {
            this.aClass = aClass;
            this.tableName = tableName;
        }

       @Override
        public void save(Object obj) {
            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                session.save(obj);
                tx.commit();
            }
        }

        @Override
        public void update(Object obj) {
            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                session.update(obj);
                tx.commit();
            }
        }

        @Override
        public void delete(Object obj) {
            try (Session session = factory.openSession()) {
                Transaction tx = session.beginTransaction();
                session.delete(obj);
                tx.commit();
            }
        }

        @Override
        public T getById(Long id) {
            try (Session session = factory.openSession()) {
                return (T) session.get(aClass, id);
            }
        }

        @Override
        public List<T> getAll() {
            try (Session session = factory.openSession()) {
                Query query = session.createQuery("from " + tableName);
                return query.list();
            }
        }
 }


