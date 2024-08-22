package org.example.task.domain.task;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class TaskResponse {
    String author;
    String executor;
    String priority;
    OffsetDateTime startTime;
    OffsetDateTime endTime;
}
