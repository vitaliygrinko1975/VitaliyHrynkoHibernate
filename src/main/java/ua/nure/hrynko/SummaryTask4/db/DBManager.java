package ua.nure.hrynko.SummaryTask4.db;
import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;
import ua.nure.hrynko.SummaryTask4.exception.Messages;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static DBManager instance;
	private static DataSource ds;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// ST4DB - the name of data source
			ds = (DataSource) envContext.lookup("jdbc/st4db");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}


	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in
	 * your WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return DB connection.
	 */
	public static Connection getConnection() throws DBException {
		Connection con;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	// //////////////////////////////////////////////////////////s
	// Methods to obtain beans
	// //////////////////////////////////////////////////////////



	
	/**
	 * Returns a user with the given identifier.
	 *
	 *            User identifier.
	 * @return User entity.
	 * @throws DBException
	 */
    public boolean checkforlogin(String loginClient) throws DBException {
        List<Users> usersList = new ArrayList<Users>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(("SELECTE login FROM users WHERE login = ?"));
            pstmt.setString(1, loginClient);
            while (rs.next()) {return true;}
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS_ITEMS, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }



	
    // //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 */
	private static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 */
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	// //////////////////////////////////////////////////////////
	// Other methods
	// //////////////////////////////////////////////////////////

	

	/**
	 * Extracts a category entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a category entity will be extracted.
	 * @return Category entity.
	 */
//	private Category extractCategory(ResultSet rs) throws SQLException {
//		Category category = new Category();
//		category.setId(rs.getLong(Fields.ENTITY_ID));
//		category.setName(rs.getString(Fields.CATEGORY_NAME));
//		return category;
//	}






	/**************** THIS METHOD IS NOT USED IN THE PROJECT *******/
	/**
	 * Returns a DB connection. This method is just for a example how to use the
	 * DriverManager to obtain a DB connection. It does not use a pool
	 * connections and not used in this project. It is preferable to use
	 * {@link #getConnection()} method instead.
	 * 
	 * @return A DB connection.
	 */
	public Connection getConnectionWithDriverManager() throws SQLException {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException ex) {
			LOG.error("Cannot obtain a connection", ex);
		}
		Connection connection = DriverManager
				.getConnection("jdbc:derby://localhost:1527/st4db;create=true;user=test;password=test");
		connection
				.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		connection.setAutoCommit(false);
		return connection;
	}
}