package ua.nure.hrynko.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.

 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 8466257860808346236L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
