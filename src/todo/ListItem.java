package todo;

public class ListItem {
	String name;
	int priority;
	String category;
	public ListItem(String name, int priority, String category){
		this.name = name;
		this.priority = priority;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriority() {
		return Integer.toString(priority);
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
