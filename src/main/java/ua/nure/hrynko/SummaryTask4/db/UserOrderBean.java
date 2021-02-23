package ua.nure.hrynko.SummaryTask4.db;

import java.io.Serializable;

/**
 * Provide records for virtual table:
 * <pre>
 * |order.id|user.firstName|user.lastName|order.bill|status.name|
 * </pre>

 */
public class UserOrderBean implements Serializable {

	private static final long serialVersionUID = -5654982557199337483L;

	private Long id;

	private long orderId;

	private String userFirstName;

	private String userLastName;

	private int orderBill;

	private String statusName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public int getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(int orderBill) {
		this.orderBill = orderBill;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "OrderUserBean [orderId=" + orderId + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName
				+ ", orderBill=" + orderBill + ", statusName=" + statusName
				+ "]";
	}
}
