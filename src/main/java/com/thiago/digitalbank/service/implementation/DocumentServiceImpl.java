package com.thiago.digitalbank.service.implementation;

import com.thiago.digitalbank.Model.Client;
import com.thiago.digitalbank.Model.DocumentClient;
import com.thiago.digitalbank.repository.ClientRepository;
import com.thiago.digitalbank.repository.DocumentRepository;
import com.thiago.digitalbank.service.interfaces.ClientService;
import com.thiago.digitalbank.service.interfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DocumentRepository documentRepository;

    public void saveFilesDocClient(String directory, Long id, MultipartFile fileFront, MultipartFile FileBack) {
        Client client = clientService.responseIfExistsAddressToClientById(id);

        Path directoryPath = Paths.get(directory, "cliente_" + id);

        gereDiretorioDestinoDocumento(directoryPath, fileFront);
        String directoryFileFront = FindLocaleDoc(directoryPath, fileFront.getOriginalFilename());

        gereDiretorioDestinoDocumento(directoryPath, FileBack);
        String  directoryFileBack= FindLocaleDoc(directoryPath, FileBack.getOriginalFilename());

        saveDocClient(client, directoryFileFront, directoryFileBack);

    }

    @Override
    public void saveDocClient(Client client, String docFront, String docBack) {
        DocumentClient doc = newDocument(docFront, docBack);
        client.setDocumentClient(documentRepository.save(doc));

        clientRepository.save(client);

    }

    @Override
    public String FindLocaleDoc(Path directoryPath, String fileName) {
        return directoryPath.toString() + "\\" + fileName;
    }

    private Path gereDiretorioDestinoDocumento(Path directoryPath, MultipartFile file) {

        String nameFileFront = responseNameDirectoryDestinationDoc(directoryPath.toString(), file);
        Path filePathFront= directoryPath.resolve(nameFileFront);
        try {
            Files.createDirectories(directoryPath);
            file.transferTo(filePathFront.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo: ", e);
        }
        return directoryPath;
    }

    private String responseNameDirectoryDestinationDoc(String diretorioPath, MultipartFile arquivo) {
        return diretorioPath + "\\" + arquivo.getOriginalFilename();
    }

    private DocumentClient newDocument(String docFront, String docBack) {
        DocumentClient doc = new DocumentClient();
        doc.setDocumentFront(docFront);
        doc.setDocumentBack(docBack);
        return doc;
    }
}
