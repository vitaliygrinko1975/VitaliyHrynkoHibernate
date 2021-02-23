package ua.nure.hrynko.SummaryTask4.db.dto;

import java.io.Serializable;

public class OrdersMenu implements Serializable {
    private Long orderId;
    private Long menuId;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    @Override
    public String toString() {
        return "OrderMenu [orderId=" + orderId + ", menuId=" + menuId +"]";

    }
}
