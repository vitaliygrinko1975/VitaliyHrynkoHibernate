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
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

public class SortedCarUpPriceCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(SortedCarUpPriceCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        // get menu items list
        List<Menu> menuItems = MySqlMenuDAO.getInstance().findMenuItems();
        LOG.trace("Found in DB: menuItemsList --> " + menuItems);

        // sort menu by category
		sort(menuItems, new Comparator<Menu>() {
			public int compare(Menu o1, Menu o2) {
				return (int)(o1.getPrice() - o2.getPrice());
			}
		});

        // put menu items list to the request
        request.setAttribute("menuItems", menuItems);
        LOG.trace("Set the request attribute: menuItems --> " + menuItems);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_CAR;
    }
}
