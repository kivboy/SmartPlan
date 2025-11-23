package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.file.FileCreateRequest;
import by.vadarod.smartplan.dto.file.FileResponse;
import by.vadarod.smartplan.entity.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toEntity(FileCreateRequest createRequest);
    FileResponse toResponse(File file);
}
