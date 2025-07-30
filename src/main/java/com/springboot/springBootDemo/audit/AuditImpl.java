package com.springboot.springBootDemo.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("auditImpl")
public class AuditImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
}
