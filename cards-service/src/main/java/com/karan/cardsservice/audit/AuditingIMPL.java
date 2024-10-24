package com.karan.cardsservice.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component("AuditingIMPL")
public class AuditingIMPL implements AuditorAware<String> {



    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_R_MS");
    }
}
