package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ua.nure.hrynko.SummaryTask4.db.HibernateSessionFactoryUtil;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.UserDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import javax.persistence.Entity;
import java.util.List;


public class MySqlUsersDAO extends MySqlAbstractDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(MySqlUsersDAO.class);

    public MySqlUsersDAO() {super(Users.class, Users.class.getAnnotation(Entity.class).name());}

    private static MySqlUsersDAO instance;
    public static synchronized MySqlUsersDAO getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlUsersDAO();
        }
        return instance;
    }
    public void addClientToUsersDb(String login, String password, String firstName, String lastName) throws DBException {
        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoleId(1);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.getTransaction();
        t.begin();
        try {
            session.saveOrUpdate(user);
            LOG.trace("add client to SQL succesful--> " );
        } catch (Exception e) {
            LOG.trace("ERRor--> " );
            t.rollback();

        }
        t.commit();
    }

    public void addAdminToUsersDb(String login, String password, String firstName, String lastName) throws DBException {
        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoleId(0);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.getTransaction();
        t.begin();
        try {
            session.saveOrUpdate(user);
            LOG.trace("add admin to SQL succesful--> " );
        } catch (Exception e) {
            LOG.trace("ERRor--> " );
            t.rollback();

        }
        t.commit();
    }


    public Users findUserByLogin(String login) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Criteria c = session.createCriteria(Users.class);
            c.add(Restrictions.eq("login", login));
            return (Users) c.uniqueResult();
        }
    }

    public List<Users> findAllUsers() throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from " + tableName);
            return query.list();
        }

    }

}