package ua.nure.hrynko.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.db.dto.Cars;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

/**
 * Lists cars items.
 */
public class ListCarCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListCarCommand.class);

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		// get menu items list
		List<Cars> carsItems = MySqlCarsDAO.getInstance().findCars();
		LOG.trace("Found in DB: carsItens --> " + carsItems);
		

		// put menu items list to the request
		request.setAttribute("carsItems", carsItems);
		LOG.trace("Set the request attribute: carsItems --> " + carsItems);
		
		LOG.debug("Command finished");
		return Path.PAGE_LIST_CAR;
	}

}