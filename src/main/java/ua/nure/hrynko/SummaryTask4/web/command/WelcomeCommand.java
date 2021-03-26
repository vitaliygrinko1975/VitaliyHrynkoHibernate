package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class WelcomeCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(WelcomeCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        // get menu items list
//        List<Cars> carsItems = MySqlCarsDAO.getInstance().findCars();
//        LOG.trace("Found in DB: carsItems --> " + carsItems);
//
//
//        // put cars items list to the request
//        request.setAttribute("carsItems", carsItems);
//        LOG.trace("Set the request attribute: carsItems --> " + carsItems);
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
        LOG.debug("Command finished");
        return Path.PAGE_WELCOME;



    }
}