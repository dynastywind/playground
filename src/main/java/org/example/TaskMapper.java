package org.example;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    void insert(Task task);
    List<Task> search(@Param("request") SearchRequest request);
}
