package ua.nure.hrynko.SummaryTask4.db;

import ua.nure.hrynko.SummaryTask4.db.dto.User;

/**
 * Role entity.

 */

public enum Role {
	ADMIN, CLIENT, MANAGER;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
