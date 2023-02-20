package org.gunitha.sitemanagementsystem.security;

import org.gunitha.sitemanagementsystem.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, Long>{

}
