package pl.com.bottega.microecom.catalog.repositories;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BankService {
    @PreAuthorize("isAnonymous()")
    Account readAccount(Long id);

    @PreAuthorize("hasAuthority('ROLE_TELLER')")
    Account[] findAccounts();

    @PreAuthorize("hasAuthority('ROLE_TELLER') || isRememberMe()")
    Account post(Account account, double amount);
}