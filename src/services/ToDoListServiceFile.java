package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Task;

public class ToDoListServiceFile implements ToDoListService{
	
	private File source;
	private List<Task> list = new ArrayList<>();
	
	public ToDoListServiceFile() {
	}
	public ToDoListServiceFile(File source) {
		this.source = source;
		
		// Reading tasks from source file
		try(BufferedReader br = new BufferedReader(new FileReader(source))){
			String line = br.readLine();
			
			// setting status
			while (line != null) {
				boolean status = false;
				if(line.charAt(3) == 'X') {
					status = true;
				} else {
					status = false;
				}
				
				// setting name
				String name = line.substring(6);
				
				list.add(new Task(name, status));
				
				br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<Task> getList() {
		return list;
	}

	public File getSource() {
		return source;
	}
	public void setSource(File source) {
		this.source = source;
	}
	
	
	@Override
	public void addTask(Task task) {
		list.add(task);
	}

	@Override
	public void removeTask(Task task) {
		list.remove(task);
	}
	
	public void removeTask(int position) {
		if(position < list.size()) {
			list.remove(position);
		}
	}
	
	public void removeTask(String name) {
		Task task = list.stream().filter(x -> x.getName().contains(name)).findFirst().orElse(null);
		list.remove(task);
	}

	@Override
	public void changeTaskStatus(Task task) {
		if(task.getStatus()) {
			task.setStatus(false);
		} else {
			task.setStatus(true);
		}
	}
	public void changeTaskStatus(int position) {
		Task task = list.get(position);
		if(task.getStatus()) {
			task.setStatus(false);
		} else {
			task.setStatus(true);
		}
	}
	public void changeTaskStatus(String name) {
		Task task = list.stream().filter(x -> x.getName().contains(name)).findFirst().orElse(null);
		if(task.getStatus()) {
			task.setStatus(false);
		} else {
			task.setStatus(true);
		}
	}

	@Override
	public void saveList() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(source))){
			for(Task t : list) {
				bw.write(t.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
