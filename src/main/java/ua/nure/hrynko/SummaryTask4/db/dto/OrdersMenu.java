package ua.nure.hrynko.SummaryTask4.db.dto;

public class OrdersMenu extends Entity {
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
        return "OrderMenu [orderId=" + orderId + ", menuId=" + menuId +
                ", getId()=" + getId() + "]";


    }
}
