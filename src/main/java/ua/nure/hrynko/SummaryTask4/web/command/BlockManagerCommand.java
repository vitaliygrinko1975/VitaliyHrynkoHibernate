package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.db.dao.MySqlStatusDAO;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockManagerCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(BlockManagerCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("BlockManagerCommand starts");

        MySqlStatusDAO blockedStatus = MySqlStatusDAO.getInstance();

        String id = request.getParameter("blockButt");

        LOG.trace("Request parameter: id --> " + id);

        request.setAttribute("status blocking", blockedStatus.updateByIdToStatusDbOnBlocking(id));

        LOG.debug("BlockManagerCommand finished");

        return Path.PAGE_LIST_ORDERS;
    }
}