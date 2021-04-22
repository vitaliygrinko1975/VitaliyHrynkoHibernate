package ua.nure.hrynko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Fields;
import ua.nure.hrynko.SummaryTask4.db.Querys;
import ua.nure.hrynko.SummaryTask4.db.dao.interfaces.RolesDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Roles;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.nure.hrynko.SummaryTask4.db.DBManager.getConnection;

public class MySqlRolesDAO implements RolesDAO {
    private static final Logger LOG = Logger.getLogger(MySqlRolesDAO.class);

    private static MySqlRolesDAO instance;

    public static synchronized MySqlRolesDAO getInstance() {
        if (instance == null) {
            instance = new MySqlRolesDAO();
        }
        return instance;
    }

    @Override
    public Roles findRoleById(Long roleId) {
        Roles role = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ROLE_BY_ID);
            pstmt.setLong(1, roleId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                role = extractRole(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return role;
    }

    private Roles extractRole(ResultSet rs) throws SQLException
    {
        Roles role = new Roles();
        role.setId(rs.getLong(Fields.ENTITY_ID));
        role.setName(rs.getString(Fields.CATEGORY_NAME));
        return role;
    }
}
