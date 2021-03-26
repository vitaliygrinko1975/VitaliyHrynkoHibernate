package ua.nure.hrynko.SummaryTask4.db;

import ua.nure.hrynko.SummaryTask4.db.dto.Users;

/**
 * Role entity.

 */

public enum Role {
	ADMIN, CLIENT, MANAGER;
	
	public static Role getRole(Users user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
