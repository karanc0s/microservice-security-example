package com.karan.loansservice.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component("AuditingIMPL")
public class AuditingIMPL implements AuditorAware<String> {


    /**
     * @return Current Auditor
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("LOANS_R_MS");
    }
}
