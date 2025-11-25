package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.file.FileCreateRequest;
import by.vadarod.smartplan.dto.file.FileResponse;

public interface FileService {
    FileResponse addFile(FileCreateRequest createRequest);
    FileResponse getFileById(Long fileId);
    void deleteFileById(Long fileId);
}
