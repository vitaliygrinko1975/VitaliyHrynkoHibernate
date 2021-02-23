package ua.nure.hrynko.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Category entity.

 */
public class Category implements Serializable {

	private static final long serialVersionUID = 2386302708905518585L;

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", getId()=" + getId() + "]";
	}

}
