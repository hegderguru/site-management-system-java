package org.gunitha.sitemanagementsystem.model.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }),
		@UniqueConstraint(columnNames = { "number" }) })
public class Application {

	@Id
	@GeneratedValue(generator = "APPLICATION_SEQUENCE")
	@SequenceGenerator(sequenceName = "APPLICATION_SEQUENCE", initialValue = 1000, allocationSize = 20, name = "APPLICATION_SEQUENCE")
	private Long id;

	private String name;

	private String number;
	
	private String email;
	
	private String phone;

	
}