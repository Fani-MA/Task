package org.example.task.domain.task;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class TaskDto {
    Long id;
    String author;
    String executor;
    Priority priority;
    OffsetDateTime startTime;
    OffsetDateTime endTime;
}
