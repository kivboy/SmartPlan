package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.File;
import by.vadarod.smartplan.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File addFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File findFileById(Long fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        if (file.isPresent()) {
            return file.get();
        } else {
            return new File();
        }
    }

    @Override
    public void deleteFileById(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
