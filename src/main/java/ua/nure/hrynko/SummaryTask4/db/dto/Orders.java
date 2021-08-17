package ua.nure.hrynko.SummaryTask4.db.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Orders entity.

 */
@Entity(name = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 5692708766041889396L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "bill", nullable = false)
	private Integer bill;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")

	private Users user;

	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}


	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

//	@Override
//	public String toString() {
//		return "Order [bill=" + bill + ", userId=" + userId + ", getId()=" + getId() + "]";
//	}


	@Override
	public String toString() {
		return "Orders{" +
				"id=" + id +
				", bill=" + bill +
				", user=" + user +
				'}';
	}
}
