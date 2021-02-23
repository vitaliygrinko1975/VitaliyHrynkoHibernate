package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlUserDAO;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddManagerCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AddManagerCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        MySqlUserDAO addedmanager = MySqlUserDAO.getInstance();

        String login = request.getParameter("addLoginPageAdmin");

        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("addPasswordPageAdmin");

        LOG.trace("Request parameter: password --> " + password);

        String name = request.getParameter("addNamePageAdmin");

        LOG.trace("Request parameter: name --> " + name);

        String lastname = request.getParameter("addLastNamePageAdmin");

        LOG.trace("Request parameter: lastname --> " + lastname);

        addedmanager.addManagerToUsersDb(login,password,name,lastname);

        LOG.trace("add manager command : --> " );

        LOG.debug("Command finished");

        return Path.COMMAND_LIST_ADMIN;
    }

}
