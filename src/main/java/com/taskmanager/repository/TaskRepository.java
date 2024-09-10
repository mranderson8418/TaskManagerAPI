
package com.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskmanager.model.Task;

@Repository // Mark this Longerface as a Spring Data repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	void save(String string);

	public void deleteById(int id);

	@Query("SELECT t FROM Task t WHERE t.username = :username")
	Page<Task> findAllTasksByUsername(@Param("username") String username, PageRequest pageRequest);

}
