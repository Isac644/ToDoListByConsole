package entities;

public class Task {
	
	private String name;
	private Boolean status;
	
	public Task() {
	}
	public Task(String name, Boolean status) {
		super();
		this.name = name;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String toString() {
		if (status = false) {
			return "- [ ] " + name;
		} else {
			return "- [X] " + name;
		}
	}
}
