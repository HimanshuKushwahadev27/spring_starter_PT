package com.emi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleHierarchyConfig {

	@Bean
	 RoleHierarchy roleHierarchy() {
		return RoleHierarchyImpl.fromHierarchy("""
				            ROLE_ADMIN > ROLE_AUTHOR
                            ROLE_AUTHOR > ROLE_USER
	                        """);
	}
}
