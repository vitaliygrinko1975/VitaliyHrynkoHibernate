package ua.nure.hrynko.SummaryTask4.db.dto;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;



/**
 * Cars item entity.

 */
@Entity(name = "cars")
public class Cars implements Serializable {

	private static final long serialVersionUID = 4716395168539434663L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 256)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Size(max = 256)
	@Column(name = "price", nullable = false)
	private Integer price;

	@NotNull
	@Size(max = 256)
	@Column(name = "category", nullable = false)
	private String category;



	public void setId(Long id) { this.id = id; }

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Cars [id=" + getId() + ", name=" + name + ", price=" + price + ", category="
				+ category + ", getId()=" + getId() + "]";
	}


}