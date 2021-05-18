package com.portal.springrest.service;

import com.portal.springrest.model.Account;
import com.portal.springrest.model.FileBD;
import com.portal.springrest.model.User;
import com.portal.springrest.repository.interfaces.AccountRepository;
import com.portal.springrest.service.interfaces.AccountService;
import com.portal.springrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Override
    public Account getBuId(int id) {
        return accountRepository.findOne(id);
    }

    @Override
    public List<Account> readAll() {
        return accountRepository.findAll();
    }

    @Override
    public void create(Account account) {
       Account savedAccount = accountRepository.save(account);
       userService.create(new User(savedAccount));
    }

    @Override
    public void delete(int id) {
        accountRepository.delete(id);
    }
}
