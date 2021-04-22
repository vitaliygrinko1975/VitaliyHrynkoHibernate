package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlCarsDAO;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeOrdersPageCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(MakeOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("MakeOrdersPageCommand starts");

        MySqlCarsDAO findedcar = MySqlCarsDAO.getInstance();

        String id = request.getParameter("makeOrderButt");

        LOG.trace("Request parameter: id --> " + id);

        request.setAttribute("car", findedcar.findCarToCarsDb(Integer.parseInt(id)));

        LOG.trace("find car to id: --> " + findedcar.findCarToCarsDb(Integer.parseInt(id)));

        LOG.debug("MakeOrdersPageCommand finished");

        return Path.PAGE_LIST_CLIENT_MAKE_ORDERS;
    }
}