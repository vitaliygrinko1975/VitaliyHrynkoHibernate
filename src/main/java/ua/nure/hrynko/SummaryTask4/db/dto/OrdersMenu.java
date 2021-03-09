package ua.nure.hrynko.SummaryTask4.db.dto;

import javax.persistence.*;
import java.io.Serializable;
@Entity(name = "orders_menu")

public class OrdersMenu implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
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
        return "OrdersMenu [orderId=" + orderId + ", menuId=" + menuId +"]";

    }

   }
