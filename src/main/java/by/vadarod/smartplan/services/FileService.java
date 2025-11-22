package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.File;

public interface FileService {
    File addFile(File file);
    File findFileById(Long fileId);
    void deleteFileById(Long fileId);
}
