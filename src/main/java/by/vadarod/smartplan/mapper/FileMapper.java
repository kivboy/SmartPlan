package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {
    @Mapping(source = "task.id", target = "taskId")
    FileResponse toResponse(File file);
}
