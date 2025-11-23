package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.file.FileCreateRequest;
import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.entity.File;

public interface FileService {
    FileResponse addFile(FileCreateRequest createRequest);
    FileResponse findFileById(Long fileId);
    void deleteFileById(Long fileId);
}
