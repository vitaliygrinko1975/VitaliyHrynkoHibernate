package ua.nure.hrynko.SummaryTask4.db;

import ua.nure.hrynko.SummaryTask4.db.dto.Users;

/**
 * Role entity.

 */

public enum RoleEnum {
	ADMIN, CLIENT;
	
	public static RoleEnum getRole(Users user) {
 		int roleId = user.getRole().getId().intValue();
		return RoleEnum.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
