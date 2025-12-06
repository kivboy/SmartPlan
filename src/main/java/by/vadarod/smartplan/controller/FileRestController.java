package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.file.FileCreateRequest;
import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("files")
public class FileRestController {
    private final FileService fileService;

    @PostMapping
    public FileResponse addFile(@RequestBody FileCreateRequest createRequest) {
        return fileService.addFile(createRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponse> getFileById(@PathVariable("id") Long fileId) {
        FileResponse fileResponse = fileService.getFileById(fileId);
        return new ResponseEntity<>(fileResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long fileId) {
        fileService.deleteFileById(fileId);
    }
}
