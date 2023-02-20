package org.gunitha.sitemanagementsystem.model.role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	@GeneratedValue(generator = "ROLE_SEQUENCE")
	@SequenceGenerator(sequenceName = "ROLE_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "ROLE_SEQUENCE")
	private Long id;

	private String name;
	
	@Enumerated(EnumType.STRING)
	private RoleLevel roleLevel;
	/*
	 * @Enumerated(EnumType.STRING) private RoleType roleType;
	 */
	/*
	 * @Embedded
	 * 
	 * @AttributeOverrides({ @AttributeOverride(name = "write", column
	 * = @Column(name = "WRITE")),
	 * 
	 * @AttributeOverride(name = "read", column = @Column(name = "READ")),
	 * 
	 * @AttributeOverride(name = "update", column = @Column(name = "UPDATE")),
	 * 
	 * @AttributeOverride(name = "share", column = @Column(name = "SHARE")) })
	 * private Privilege privileges;
	 */

	@Enumerated(EnumType.STRING)
	private PrivilegeType privilegeType;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(RoleType roleType, PrivilegeType privilegeType,RoleLevel roleLevel) {
		super();
		this.name = roleLevel + "_" + privilegeType;
		this.privilegeType = privilegeType;
		this.roleLevel=roleLevel;
	}

}