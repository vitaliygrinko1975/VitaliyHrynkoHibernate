package ua.nure.hrynko.SummaryTask4.db;

public class Query {

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////


    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";

    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    public static final String SQL_FIND_ALL_MENU_ITEMS = "SELECT * FROM menu";

    public static final String SQL_FIND_ORDERS_BY_STATUS_AND_USER = "SELECT * FROM orders WHERE status_id=? AND user_id=?";

    public static final String SQL_FIND_MENU_ITEMS_BY_ORDER = "select * from menu where id in (select menu_id from orders_menu where order_id=?)";

    public static final String SQL_FIND_ORDERS_BY_STATUS = "SELECT * FROM orders WHERE status_id=?";

    //public static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";

    public static final String SQL_UPDATE_CAR = "UPDATE menu SET name=?, price=?, category=? WHERE id=?";

    public static final String SQL_UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=?"
            + "	WHERE id=?";

    public static final String SQL_GET_USER_ORDER_BEANS = "SELECT o.id, u.first_name, u.last_name, o.bill, s.name"
            + "	FROM users u, orders o, status s"
            + "	WHERE o.user_id=u.id AND o.status_id=s.id";


}
