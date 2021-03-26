package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SortedCarDownNameCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(SortedCarDownNameCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("SortedCarDownNameCommand starts");

        // get cars list
        List<Cars> carsItems = MySqlCarsDAO.getInstance().findCarSortedDownByName();
        LOG.trace("Found in DB: carsItemsList --> " + carsItems);


        // put cars list to the request
        request.setAttribute("carsItems", carsItems);
        LOG.trace("Set the request attribute: carsItems --> " + carsItems);

        LOG.debug("SortedCarDownNameCommand finished");
        return Path.PAGE_LIST_CAR;
    }
}

