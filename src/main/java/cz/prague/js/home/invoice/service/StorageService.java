package cz.prague.js.home.invoice.service;

import cz.prague.js.home.invoice.model.GoogleExtensionFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file) throws IOException, GeneralSecurityException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    GoogleExtensionFile getFileByName(String fileName) throws IOException, GeneralSecurityException;

}
