package ua.nure.hrynko.SummaryTask4.db.dto;

/**
 * Orders entity.

 */
public class Orders extends Entity {

	private static final long serialVersionUID = 5692708766041889396L;

	private Integer bill;

	private Long userId;

	private int statusId;

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Orders [bill=" + bill + ", userId=" + userId + ", statusId="
				+ statusId + ", getId()=" + getId() + "]";
	}

}
