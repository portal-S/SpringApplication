package com.portal.springrest.security;

import com.portal.springrest.model.Account;
import com.portal.springrest.repository.interfaces.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("accServImpl")
public class AccountDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository repository;

    @Autowired
    public AccountDetailsServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = repository.findByName(s).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return SecurityAccount.fromAccount(account);
    }
}
