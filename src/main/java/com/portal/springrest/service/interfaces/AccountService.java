package com.portal.springrest.service.interfaces;

import com.portal.springrest.model.Account;
import com.portal.springrest.model.FileBD;

import java.io.InputStream;
import java.util.List;

public interface AccountService {

    Account getBuId(int id);

    List<Account> readAll();

    void create(Account account);

    void delete(int id);

}
