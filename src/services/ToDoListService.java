package services;

import java.util.List;

import entities.Task;

public interface ToDoListService {
	
	void addTask(Task task);
	void removeTask(Task task);
	void changeTaskStatus(Task task);
	List<Task> getList();
	void saveList();
}
