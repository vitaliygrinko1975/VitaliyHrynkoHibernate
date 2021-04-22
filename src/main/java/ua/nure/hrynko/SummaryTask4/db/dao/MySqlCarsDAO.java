package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ua.nure.hrynko.SummaryTask4.db.HibernateSessionFactoryUtil;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.CarsDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import org.hibernate.Transaction;
import javax.persistence.Entity;
import java.util.List;

public class MySqlCarsDAO extends MySqlAbstractDAO implements CarsDAO {

    private static final Logger LOG = Logger.getLogger(MySqlCarsDAO.class);

    public MySqlCarsDAO() {
        super(Cars.class, Cars.class.getAnnotation(Entity.class).name());
    }

    private static MySqlCarsDAO instance;

    public static synchronized MySqlCarsDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlCarsDAO();
        }
        return instance;
    }

    @Override
    public void deleteCarToCarsDb(long id) throws NullPointerException {
        delete(getById(id));
    }

    @Override
    public List<Cars> findCars() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from " + tableName);
            return query.list();
        }
    }

    @Override
    public void updateCarToCarsDb(long id, String name, int price, String category) {
        Cars cars = new Cars();
        cars.setId(id);
        cars.setName(name);
        cars.setPrice(price);
        cars.setCategory(category);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.getTransaction();
        t.begin();
        try {
            session.saveOrUpdate(cars);
        } catch (Exception e) {
            t.rollback();

        }
        t.commit();
    }

    @Override
    public void addCarToCarsDb(String name, int price, String category) throws DBException {
        Cars cars = new Cars();
        cars.setName(name);
        cars.setPrice(price);
        cars.setCategory(category);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.getTransaction();
        t.begin();
        try {
            session.saveOrUpdate(cars);
        } catch (Exception e) {
            t.rollback();

        }
        t.commit();
    }

    @Override
    public Cars findCarToCarsDb(long id) throws DBException {
        return (Cars) getById(id);
    }


    public List<Cars> selectCarsByCategory(String selectableCategory) throws DBException {

        try (Session session = sessionFactory.openSession()) {
            Criteria cr = session.createCriteria(aClass);
            cr.add(Restrictions.like("category", "%" + selectableCategory + "%"));
            return cr.list();
        }
    }

    public List<Cars> findCarSortedUpByName() throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Criteria cr = session.createCriteria(aClass);
            cr.addOrder(Order.asc("name"));
            return cr.list();
        }
    }

    public List<Cars> findCarSortedDownByName() throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Criteria cr = session.createCriteria(aClass);
            cr.addOrder(Order.desc("name"));
            return cr.list();
        }
    }
}


