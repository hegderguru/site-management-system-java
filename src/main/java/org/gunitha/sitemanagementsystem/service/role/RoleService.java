package org.gunitha.sitemanagementsystem.service.role;

import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.Role;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.model.role.RoleType;

public interface RoleService {

	Role findRoleByName(String name);

	/*
	 * Role findByRoleTypeAndPrivilegeType(RoleType roleType, PrivilegeType
	 * privilegeType);
	 */
	
	Role findByRoleLevelAndPrivilegeType(RoleLevel roleLevel, PrivilegeType privilegeType);

}
