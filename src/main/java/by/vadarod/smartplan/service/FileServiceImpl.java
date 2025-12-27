package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.entity.File;
import by.vadarod.smartplan.entity.FileContent;
import by.vadarod.smartplan.entity.Task;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.FileMapper;
import by.vadarod.smartplan.repository.FileContentRepository;
import by.vadarod.smartplan.repository.FileRepository;
import by.vadarod.smartplan.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileContentRepository fileContentRepository;
    private final FileMapper fileMapper;
    private final TaskRepository taskRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository,
                           FileContentRepository fileContentRepository,
                           FileMapper fileMapper,
                           TaskRepository taskRepository) {
        this.fileRepository = fileRepository;
        this.fileContentRepository = fileContentRepository;
        this.fileMapper = fileMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public void deleteFileById(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    @Override
    @Transactional
    public FileResponse saveFile(Long taskId, String fileName, String fileType, byte[] data) {
        File file = new File();
        file.setFileName(fileName);
        file.setFileType(fileType);
        file.setTimestamp(LocalDateTime.now());

        if (taskId != null) {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found for taskId = " + taskId));
            file.setTask(task);
        }

        File savedFile = fileRepository.save(file);

        FileContent content = new FileContent();
        content.setFile(savedFile);
        content.setData(data);
        fileContentRepository.save(content);

        return fileMapper.toResponse(savedFile);
    }

    @Override
    @Transactional(readOnly = true)
    public FileResponse getFileMetadata(Long fileId) {
        Optional<File> optionalFile = fileRepository.findById(fileId);
        if (optionalFile.isPresent()) {
            return fileMapper.toResponse(optionalFile.get());
        } else {
            throw new EntityNotFoundException("File not found for id=" + fileId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getFileData(Long fileId) {
        FileContent content = fileContentRepository.findByFileId(fileId);
        if (content == null) {
            throw new EntityNotFoundException("File content not found for fileId=" + fileId);
        }
        return content.getData();
    }

//    @Transactional(readOnly = true)
//    public List<FileResponse> getAllFiles() {
//        return fileRepository.findAll()
//                .stream()
//                .map(FileMapper::toResponse)
//                .toList();
//    }

}
