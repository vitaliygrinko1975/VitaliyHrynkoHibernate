package ua.nure.hrynko.SummaryTask4.db;

import ua.nure.hrynko.SummaryTask4.db.models.Users;

/**
 * Role entity.

 */

public enum RoleEnum {
	ADMIN, CLIENT;
	
	public static RoleEnum getRoleEnum(Users user) {
 		//int roleId = user.getRole().getId();
		int roleId = user.getRoleId();
		return RoleEnum.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
