package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.exception.model.ErrorResponse;
import by.vadarod.smartplan.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
@Tag(name = "Файлы", description = "Сервисы по работе с файлами")
public class FileRestController {
    private final FileService fileService;


    // Загрузка файла
    @PostMapping("/upload")
    @Operation(summary = "Загрузка файла", description = "Добавление метаинформации и данных нового файла")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = FileResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task for file not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<FileResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam(value = "taskId", required = false) Long taskId
    ) throws IOException {
        FileResponse savedFile = fileService.saveFile(
                taskId,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getBytes()
        );
        return ResponseEntity.ok(savedFile);
    }



    // Получение метаданных одного файла
    @GetMapping("/{id}")
    @Operation(summary = "Получение метаданных файла", description = "Получение метаданных файла по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = FileResponse.class))),
            @ApiResponse(responseCode = "404", description = "File not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<FileResponse> getFileMetadata(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getFileMetadata(id));
    }

    // Скачивание содержимого файла
    @GetMapping("/{id}/download")
    @Operation(summary = "Скачивание файла", description = "Скачивание файла по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "File not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileResponse file = fileService.getFileMetadata(id);
        byte[] data = fileService.getFileData(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .body(data);
    }


    /*
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление файла", description = "Удаление файла по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "File not found", content = @Content)
    })
    public void delete(@PathVariable("id") Long fileId) {
        fileService.deleteFileById(fileId);
    }*/
}
