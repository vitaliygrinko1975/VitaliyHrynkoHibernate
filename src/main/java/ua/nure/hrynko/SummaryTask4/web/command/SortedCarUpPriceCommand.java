package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.db.models.Cars;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.*;

public class SortedCarUpPriceCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(SortedCarUpPriceCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        // get cars items list
        List<Cars> carsItems = MySqlCarsDAO.getInstance().findCars();
        LOG.trace("Found in DB: carsItem --> " + carsItems);

        // sort cars by category
		sort(carsItems, (o1, o2) -> (int)(o1.getPrice() - o2.getPrice()));

        // put cars items list to the request
        request.setAttribute("carsItems", carsItems);
        LOG.trace("Set the request attribute: carsItems --> " + carsItems);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_CAR;
    }
}
