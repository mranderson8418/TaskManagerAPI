package com.taskmanager.service;

import com.taskmanager.dto.TaskDto;
import com.taskmanager.dto.TaskResponse;

public interface TaskService {

	public TaskDto createTask(TaskDto taskDto);

	public TaskResponse getAllTasks(int pageNo, int pageSize);

	public TaskDto getTaskById(int id);

	public TaskDto updateTask(TaskDto taskUpdate, int id);

	public void deleteByTaskId(int id);

}
