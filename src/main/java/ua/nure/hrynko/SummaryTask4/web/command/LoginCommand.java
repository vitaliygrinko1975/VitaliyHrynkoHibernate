package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.DBManager;
import ua.nure.hrynko.SummaryTask4.db.Role;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlUserDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.User;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command.
 *
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from a request
        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = MySqlUserDAO.getInstance().findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            forward = Path.COMMAND_LIST_ADMIN;
        }

        if (userRole == Role.CLIENT) {
            forward = Path.COMMAND_LIST_MENU;
        }

        if (userRole == Role.MANAGER) {
            forward = Path.COMMAND_LIST_ORDERS;
        }
        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");
        return forward;
    }

}