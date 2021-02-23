package ua.nure.hrynko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.SummaryTask4.Path;
import ua.nure.hrynko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(ReturnCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("ReturnCommand starts");

        String days = request.getParameter("days");

        LOG.trace("Request parameter: days --> " + days);

        int payPrices =  Integer.parseInt(request.getParameter("payPrice"));

        LOG.trace("Request parameter: payPrices --> " + payPrices);

        request.setAttribute("daysReturn", days);
        request.setAttribute("payPriceReturn", payPrices);


        LOG.debug("ReturnCommand finished");

        return Path.PAGE_LIST_CLIENT_TO_PAY;
    }

}


