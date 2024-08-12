package org.example.task.domain.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ProjectDto {
    Long id;

    String title;

    String description;

    ProjectState state;

    OffsetDateTime dateTime;

}
