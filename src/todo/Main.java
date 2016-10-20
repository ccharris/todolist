package todo;

class Main {
	public static void main(String[] args) {
		List list = new List();
		list.getList();
		System.out.println("Welcome to the List Maker.");
		while (true) {
			int choice = list.getMenuChoice();
			if (choice == 1) {
				System.out.println(list.printList());
			} else if (choice == 2) {
				list.addListItem();
			} else if (choice == 3) {
				list.removeItems();
			} else if (choice == 4) {
				list.modifyItems();
			} else if (choice == 5) {
				break;
			}
		}
		list.saveList();
		System.out.println("Good-bye! Good Luck on your To Dos!");
	}
}
