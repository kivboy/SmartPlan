package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.file.FileCreateRequest;
import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/files")
@Tag(name = "Файлы", description = "Сервисы по работе с файлами")
public class FileRestController {
    private final FileService fileService;

    @PostMapping
    @Operation(summary = "Добавление файла", description = "Добавление нового файла")
    public FileResponse addFile(@RequestBody @Validated FileCreateRequest createRequest) {
        return fileService.addFile(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение файла", description = "Получение файла по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "File not found", content = @Content)
    })
    public ResponseEntity<FileResponse> getFileById(@PathVariable("id") Long fileId) {
        FileResponse fileResponse = fileService.getFileById(fileId);
        return new ResponseEntity<>(fileResponse,  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление файла", description = "Удаление файла по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "File not found", content = @Content)
    })
    public void delete(@PathVariable("id") Long fileId) {
        fileService.deleteFileById(fileId);
    }
}
