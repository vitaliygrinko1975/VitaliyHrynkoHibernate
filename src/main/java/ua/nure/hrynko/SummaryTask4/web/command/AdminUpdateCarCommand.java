package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUpdateCarCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminUpdateCarCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        MySqlCarsDAO updatedCar = MySqlCarsDAO.getInstance();

       long id = Long.parseLong(request.getParameter("carForUpdateButt"));


        LOG.trace("Request parameter: id --> " + id);


        String name = request.getParameter("updateNameCar");

        LOG.trace("Request parameter: name --> " + name);

        int price =  Integer.parseInt(request.getParameter("updatePriceCar"));

        LOG.trace("Request parameter: price --> " + price);

        String category = request.getParameter("updateCategoryCar");

        LOG.trace("Request parameter: category --> " + category);

        updatedCar.updateCarToCarsDb(id,name,price,category);

        LOG.trace("update  car by ID: --> " );

        LOG.debug("AdminUpdateCarCommand finished");

        return Path.COMMAND_LIST_ADMIN;
    }

}

