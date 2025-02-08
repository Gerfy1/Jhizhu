package com.jizhu.Jhizhu.repository;

import com.jizhu.Jhizhu.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepositoty extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(Long UserId);
}
