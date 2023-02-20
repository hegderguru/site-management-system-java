package org.gunitha.sitemanagementsystem.model.user;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthUserBean {
	
	private String username;
	
	private String password;

}
