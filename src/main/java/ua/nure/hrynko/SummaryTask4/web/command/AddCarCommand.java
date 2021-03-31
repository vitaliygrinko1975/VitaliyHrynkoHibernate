package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

public class AddCarCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AddCarCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AddCarCommand starts");

        MySqlCarsDAO addedCar = MySqlCarsDAO.getInstance();

        String name = request.getParameter("addName");

        LOG.trace("Request parameter: name --> " + name);

        int price =  Integer.parseInt(request.getParameter("addPrice"));

        LOG.trace("Request parameter: price --> " + price);

        String category = request.getParameter("addCategory");

        LOG.trace("Request parameter: category --> " + category);

        addedCar.addCarToCarsDb(name,price,category);

        LOG.trace("add name car : --> " );

        LOG.debug("AddCarCommand finished");

        return Path.COMMAND_LIST_ADMIN;
    }

}

