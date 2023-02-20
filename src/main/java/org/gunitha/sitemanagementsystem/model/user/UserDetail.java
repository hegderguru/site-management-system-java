package org.gunitha.sitemanagementsystem.model.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 5506517315496129003L;

	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private List<GrantedAuthority> authorities;

	public UserDetail(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.accountNonExpired = !user.getExpired();
		this.accountNonLocked = !user.getLocked();
		this.credentialsNonExpired = !user.getCredentialExpired();
		this.enabled = user.getEnabled();
		this.authorities = user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getAuthority()))
				.collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}