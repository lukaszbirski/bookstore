package birski.bookstore.services.daos;

import birski.bookstore.models.daos.LocalFileManager;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static birski.bookstore.configs.ApiConfig.*;

@Service
public class LocalFileService {

    private static final Logger logger = LoggerFactory.getLogger(LocalFileService.class);

    private ServletContext servletContext;
    private String uploads = UPLOADS_DIRECTORY;

    public LocalFileService(ServletContext servletContext) {
        this.servletContext = servletContext;
        createContextDirectory();
    }

    private void createContextDirectory(){

        //uploads = UPLOADS_DIRECTORY;
        logger.info("Path to uploaded file: {}", uploads);

        if (Strings.isBlank(uploads)){
            logger.error("Path to file not configured!");
        }
        Path path = Paths.get(uploads);

        if (Files.notExists(path)){
            try {
                logger.info("Try to create directory: {}", path);
                Files.createDirectories(path);
            }catch (IOException e){
                logger.error("Cannot create directory: {}. Exception: {}", path, e.getMessage());
            }
            logger.info("Path created on: {}", path);
        }
    }

    public ResponseEntity<?> getFile(String fileName){
        Path path = Paths.get(uploads + fileName);

        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        }catch (MalformedURLException e){
            logger.error("Cannot get resource: {}", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        File targetFile;
        try{
            targetFile = resource.getFile();
        }catch (IOException e){
            logger.error("Cannot get file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String contentType;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            logger.error("Cannot get contentType: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + targetFile.getName()+"\"").contentLength(targetFile.length()).body(resource);
    }

    public List<LocalFileManager> getFiles(){
        Stream<Path> files;
        try {
            files = Files.walk(Paths.get(uploads)).filter(Files::isRegularFile);
        } catch (IOException e) {
            logger.error("Cannot get files on path: {} Error: {}", uploads, e.getMessage());
            return null;
        }
        List<LocalFileManager> localFileManagers = new ArrayList<>();

        files.forEach(f -> {
            BasicFileAttributes basicFileAttributes;
            try {
                basicFileAttributes = Files.readAttributes(f.toAbsolutePath(), BasicFileAttributes.class);
            } catch (IOException e) {
                logger.error("Error while getting basicFileAttributes from file: {}", e.getMessage());
                return;
            }

            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(DOWNLOAD_URI).path(f.getFileName().toString()).toUriString();

            String deleteUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(DELETE_URI).path(f.getFileName().toString()).toUriString();

            LocalFileManager localFileManager = new LocalFileManager();
            localFileManager.setName(f.getFileName().toString());
            localFileManager.setCreationTime(basicFileAttributes.creationTime().toString());
            localFileManager.setLastModified(basicFileAttributes.lastModifiedTime().toString());
            localFileManager.setSize(basicFileAttributes.size());
            localFileManager.setDownloadUri(downloadUri);
            localFileManager.setDeleteUri(deleteUri);
            try {
                localFileManager.setFileType(Files.probeContentType(f.toAbsolutePath()));
            } catch (IOException e) {
                logger.error("Error while getting probeContentType from file: {}", e.getMessage());
                return;
            }

            localFileManagers.add(localFileManager);

        });

        return localFileManagers;
    }

    public ResponseEntity<String> deleteFile(String filename){

        File file = new File(uploads + filename);
        if (file.delete()){
            logger.info("Deleted file: {}", file.getName());
            return new ResponseEntity<>("Deleted file " + file.getName(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("File not found: " + file.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> uploadFile(MultipartFile file){
        Path path = Paths.get(uploads + file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error("Error while getting file {} from the input: {}", file.getOriginalFilename(), e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
    }
}
