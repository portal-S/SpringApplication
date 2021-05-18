package com.portal.springrest.controller;

import com.portal.springrest.model.FileBD;
import com.portal.springrest.model.User;
import com.portal.springrest.service.interfaces.AccountService;
import com.portal.springrest.service.interfaces.FileService;
import com.portal.springrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("api/v1/files/")
public class FileRestControllerV1 {

    @Autowired
    private FileService service;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('file.update')")
    public ResponseEntity<?> addFile(@RequestParam("file") MultipartFile file, @RequestParam Integer accId){
        if(accId == null || file == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            service.create(file.getInputStream(), file.getOriginalFilename(), new FileBD(file.getOriginalFilename(), userService.getBuId(accId)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('file.update')")
    public String delFile(@PathVariable("id") Integer id){
        service.delete(id);
        return "Deleted";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('file.read')")
    public ResponseEntity<?> getFile(@PathVariable("id") Integer id, HttpServletResponse response){
        File file = service.getById(id);
        try {
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e){
           e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
