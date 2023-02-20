package org.gunitha.sitemanagementsystem.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserType {
	
	USER(User.DISCRIMINATOR_VALUE),APPLICATION_USER(ApplicationUser.DISCRIMINATOR_VALUE),ACCOUNT_USER(AccountUser.DISCRIMINATOR_VALUE),
		DEALER_USER(DealerUser.DISCRIMINATOR_VALUE),SITE_ENGINEER_USER(SiteEngineer.DISCRIMINATOR_VALUE),
		SITE_OWNER_USER(SiteOwner.DISCRIMINATOR_VALUE);
		
	@Getter
	private final String discriminatorValue;
	
	
}
