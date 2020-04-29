package birski.bookstore.restcontrollers.dtos;

import birski.bookstore.models.daos.LocalFileManager;
import birski.bookstore.services.daos.LocalFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static birski.bookstore.configs.ApiConfig.*;

@RestController
@CrossOrigin
@RequestMapping(LOCAL_FILE_REST_CONTROLLER_REQUEST_MAPPING)
public class LocalFileRestController {

    private LocalFileService localFileService;

    public LocalFileRestController(LocalFileService localFileService) {
        this.localFileService = localFileService;
    }

    @GetMapping(FILE_URL)
    @PreAuthorize("hasRole('USER')")
    public List<LocalFileManager> getFiles(){
        return localFileService.getFiles();
    }

    @GetMapping(FILE_URL + DOWNLOAD_URL + FILE_NAME_PATH_VARIABLE_URL)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> downloadFile(@PathVariable String filename){
        return localFileService.getFile(filename);
    }

    @PostMapping(FILE_URL)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        return localFileService.uploadFile(file);
    }

    @DeleteMapping(FILE_URL + DELETE_URL + FILE_NAME_PATH_VARIABLE_URL)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteFiles(@PathVariable String filename){
        return localFileService.deleteFile(filename);
    }
}
