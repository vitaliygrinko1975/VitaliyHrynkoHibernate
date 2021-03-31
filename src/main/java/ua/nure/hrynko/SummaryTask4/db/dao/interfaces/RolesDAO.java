package ua.nure.hrynko.SummaryTask4.db.dao.interfaces;

import ua.nure.hrynko.SummaryTask4.db.dto.Roles;

public interface RolesDAO {
    Roles findRoleById(Long roleId);
}
