package ua.nure.hrynko.SummaryTask4.db.dto;

import javax.persistence.*;
import java.io.Serializable;
@Entity(name = "orders_cars")

public class OrdersCars implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Long carId;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setMenuId(Long menuId) {
        this.carId = menuId;
    }

    public Long getMenuId() {
        return carId;
    }

    @Override
    public String toString() {
        return "OrdersCars [orderId=" + orderId + ", menuId=" + carId +"]";

    }

   }
