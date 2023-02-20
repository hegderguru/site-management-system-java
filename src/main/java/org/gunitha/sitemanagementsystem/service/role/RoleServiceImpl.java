package org.gunitha.sitemanagementsystem.service.role;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.Role;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.model.role.RoleType;
import org.gunitha.sitemanagementsystem.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/*
	 * @Override public Role findByRoleTypeAndPrivilegeType(RoleType roleType,
	 * PrivilegeType privilegeType) { return
	 * roleRepository.findByRoleTypeAndPrivilegeType(roleType, privilegeType); }
	 */
	@Override
	public Role findByRoleLevelAndPrivilegeType(RoleLevel roleLevel, PrivilegeType privilegeType) {
		return roleRepository.findByRoleLevelAndPrivilegeType(roleLevel, privilegeType);
	}

}
