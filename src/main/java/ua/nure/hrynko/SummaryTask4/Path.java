package ua.nure.hrynko.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).

 */
public final class Path {

    // pages
    public static final String PAGE_WELCOME = "/welcome.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_LIST_ADMIN = "/WEB-INF/jsp/admin/admin_page.jsp";
    public static final String PAGE_LIST_ADMIN_ADDCAR = "/WEB-INF/jsp/admin/admin_page_addcar.jsp";
    public static final String PAGE_LIST_ADMIN_UPDATECAR = "/WEB-INF/jsp/admin/admin_page_updatecar.jsp";
    public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/manager/manager_page.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE_LIST_CAR = "/WEB-INF/jsp/client/list_car.jsp";
    public static final String PAGE_LIST_CLIENT_MAKEORDERS = "/WEB-INF/jsp/client/list_car_order.jsp";
    public static final String PAGE_LIST_MANAGER = "/WEB-INF/jsp/manager/manager_page.jsp";
    public static final String PAGE_LIST_CLIENT_TO_PAY = "/WEB-INF/jsp/client/client_page_to_pay.jsp";
    public static final String PAGE_LOGINING = "/WEB-INF/jsp/client/logining_page.jsp";
    public static final String PAGE_ADMIN_USERS = "/WEB-INF/jsp/admin/admin_page_users.jsp";
    public static final String PAGE_ADMIN_ADD_NEW_ADMIN = "/WEB-INF/jsp/admin/admin_page_add_new_admin.jsp";
    public static final String PAGE_LIST_CAR_BY_CLASS = "/WEB-INF/jsp/client/list_cars_selected_by_class.jsp";
    // commands
    public static final String COMMAND_LIST_ADMIN= "/controller?command=listAdmin";
    public static final String COMMAND_LIST_ADMIN_ADD_CAR = "/controller?command=addPage";
    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";
    public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";

}