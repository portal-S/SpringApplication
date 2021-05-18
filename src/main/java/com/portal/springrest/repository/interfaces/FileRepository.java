package com.portal.springrest.repository.interfaces;

import com.portal.springrest.model.FileBD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileBD, Integer> {
}
