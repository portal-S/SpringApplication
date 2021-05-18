package com.portal.springrest.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.portal.springrest.model.FileBD;
import com.portal.springrest.repository.interfaces.FileRepository;
import com.portal.springrest.service.interfaces.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private FileRepository repository;

    @Override
    public File getById(int id) {
        FileBD fileBD = repository.findOne(id);
        S3Object s3object = amazonS3.getObject("springrestportal", fileBD.getName());
        File file = new File(fileBD.getName());
        try (S3ObjectInputStream inputStream = s3object.getObjectContent(); OutputStream outputStream = new FileOutputStream(file);) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            outputStream.write(bytes);
        } catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public List<FileBD> readAll() {
        return null;
    }

    @Override
    public void create(InputStream stream, String filename, FileBD fileBD) {
        amazonS3.putObject("springrestportal", filename, stream, new ObjectMetadata());
        repository.save(fileBD);

    }

    @Override
    public void delete(int id) {
        amazonS3.deleteObject("springrestportal", "file");
    }
}
