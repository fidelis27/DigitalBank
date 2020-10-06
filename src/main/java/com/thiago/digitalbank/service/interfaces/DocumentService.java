package com.thiago.digitalbank.service.interfaces;

import com.thiago.digitalbank.Model.Client;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface DocumentService {

    void saveFilesDocClient(String directory, Long id, MultipartFile fileFront, MultipartFile FileBack);

    void saveDocClient(Client client, String docFront, String docBack);

    String FindLocaleDoc(Path directoryPath, String fileName);
}
