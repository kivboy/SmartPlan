package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.file.FileCreateRequest;
import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.entity.File;
import by.vadarod.smartplan.mapper.FileMapper;
import by.vadarod.smartplan.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileResponse addFile(FileCreateRequest createRequest) {
        return fileMapper.toResponse(fileRepository.save(fileMapper.toEntity(createRequest)));
    }

    @Override
    public FileResponse findFileById(Long fileId) {
        Optional<File> fileOptional = fileRepository.findById(fileId);
        if (fileOptional.isPresent()) {
            return fileMapper.toResponse(fileOptional.get());
        } else {
            return new FileResponse();
        }
    }

    @Override
    public void deleteFileById(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
