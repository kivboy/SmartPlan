package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.file.FileResponse;

public interface FileService {

    void deleteFileById(Long fileId);
    FileResponse saveFile(Long taskId, String fileName, String fileType, byte[] data);
    FileResponse getFileMetadata(Long fileId);
    byte[] getFileData(Long fileId);
}
