package org.gunitha.sitemanagementsystem.repository.role;

import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.Role;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.model.role.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

//	Role findByRoleTypeAndPrivilegeType(RoleType roleType, PrivilegeType privilegeType);

	/* Role findByRoleType(String roleType); */

	Role findByRoleLevelAndPrivilegeType(RoleLevel roleLevel, PrivilegeType privilegeType);
}
