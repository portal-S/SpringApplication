package com.portal.springrest.controller;


import com.portal.springrest.model.Account;
import com.portal.springrest.model.User;
import com.portal.springrest.service.interfaces.AccountService;
import com.portal.springrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts/")
public class AccountRestControllerV1 {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('acc.read')")
    public ResponseEntity<Account> getAcc(@PathVariable("id") Integer accId){
        if(accId == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Account account = accountService.getBuId(accId);
        if(account == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('acc.read')")
    public ResponseEntity<List<Account>> getAccAll(){
        return new ResponseEntity<>(accountService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('acc.update')")
    public ResponseEntity<Account> delAcc(@PathVariable("id") Integer id){
        if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('acc.update')")
    public ResponseEntity<Account> saveAcc(@RequestBody @Valid Account account){
        if (account == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        accountService.create(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }


}
