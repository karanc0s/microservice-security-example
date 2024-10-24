package com.karan.accountsservice.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditingIMPL")
public class AuditingIMPL implements AuditorAware<String> {
    /**
     * @return - current auditor of application
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_R_MS");
    }
}
