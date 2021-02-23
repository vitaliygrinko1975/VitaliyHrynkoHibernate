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

public class SelectCarByClass extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(SelectCarByClass.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        MySqlMenuDAO selectedByClass = MySqlMenuDAO.getInstance();

        String classCars = request.getParameter("selectClass");

        if  (selectedByClass.thereIsSuchClass(classCars)) {
            List<Menu> listcarItems = selectedByClass.selectCarsByClass(classCars);

            LOG.trace("Found in DB: menuItemsList --> " + listcarItems);


            // put menu items list to the request
            request.setAttribute("listcarItems", listcarItems);
            LOG.trace("Set the request attribute:listcarItems --> " );

            LOG.debug("Command finished");

        }return Path.PAGE_LIST_CAR_BY_CLASS;
    }
}




//