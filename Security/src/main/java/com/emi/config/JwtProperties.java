package com.emi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Data
@ConfigurationProperties(prefix="jwt")
@Getter
@Setter
@Qualifier("property")
public class JwtProperties {
	
	private String secret;
	private long expiration;

}
