package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlMenuDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Menu;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SortedCarUpNameCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(SortedCarUpNameCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        // get menu items list
        List<Menu> menuItems = MySqlMenuDAO.getInstance().findCarSortedUpByName();
        LOG.trace("Found in DB: menuItemsList --> " + menuItems);


        // put menu items list to the request
        request.setAttribute("menuItems", menuItems);
        LOG.trace("Set the request attribute: menuItems --> " + menuItems);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_CAR;
    }
}
