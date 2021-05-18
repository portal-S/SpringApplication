package com.portal.springrest.service.interfaces;

import com.portal.springrest.model.FileBD;
import com.portal.springrest.model.User;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface FileService {

    File getById(int id);

    List<FileBD> readAll();

    void create(InputStream stream, String filename, FileBD fileBD);

    void delete(int id);
}
