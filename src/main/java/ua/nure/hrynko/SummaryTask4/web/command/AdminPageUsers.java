package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlUsersDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Users;
import ua.nure.hrynko.SummaryTask4.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminPageUsers extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageUsers.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("Command starts");
        List<Users> userList = MySqlUsersDAO.getInstance().findAllUsers();
        LOG.trace("Found in DB: userList --> " + userList);


        // put user order beans list to request
        request.setAttribute("userList", userList);
        LOG.trace("Set the request attribute: userList --> " + userList);

        LOG.debug("Commands finished");

        return Path.PAGE_ADMIN_USERS;

    }
}