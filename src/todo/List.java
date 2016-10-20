package todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class List {
	Scanner sc = new Scanner(System.in);
	ArrayList<ListItem> list1 = new ArrayList<ListItem>();

	public int getMenuChoice() {
		int numResponse = 0;
		while (true) {
			System.out.println("What would you like to do?" + "\n1) View List" + "\n2) Add Items to the List"
					+ "\n3) Remove Items from the List" + "\n4) Modify an item on the List" + "\n5) Quit and Save");
			String response = sc.nextLine();
			try {
				numResponse = Integer.parseInt(response);
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number.");
			}
			if (numResponse > 0 && numResponse < 6) {
				break;
			} else {
				System.out.println("Please enter 1-5");
			}
		}
		return numResponse;
	}

	public String printList() {
		StringBuilder st = new StringBuilder();
		st.append("Your List, Sorted by Priorty - The Highest is on Top!\n"
				+ "*****************************************************\n");
		Collections.sort(list1, (a, b) -> a.getPriority().compareTo(b.getPriority()));
		int i = 1;
		for (ListItem item : list1) {
			st.append(i).append(" : ").append(item.getName()).append(" : ").append(item.getCategory()).append("\n");
			i++;
		}
		return st.toString();
	}

	public void addListItem() {
		String addResponse = "";
		while (!addResponse.equals("quit")) {
			System.out.println("What would you like to add?" + "\nType quit to exit");
			addResponse = sc.nextLine();
			if (!addResponse.equals("quit")) {
				System.out.println("What category would you like to assign to this item?");
				String category = sc.nextLine();
				System.out.println("What priority is this item? Enter (l)ow, (m)edium, or (h)igh.");
				String priority = sc.nextLine();
				int prio = 3;
				if (priority.contains("m")) {
					prio = 2;
				} else if (priority.contains("h")) {
					prio = 1;
				}
				ListItem item = new ListItem(addResponse, prio, category);
				list1.add(item);

			}
		}
	}

	public void saveList() {
		System.out.println("Saving List");
		try {
			FileWriter fw = new FileWriter("itemList.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < list1.size(); i++) {
				bw.write(list1.get(i).getName());
				bw.write(",");
				bw.write(list1.get(i).getPriority());
				bw.write(",");
				bw.write(list1.get(i).getCategory());
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ListItem> getList() {
		ArrayList<ListItem> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("itemList.txt"));
			try {
				String line = br.readLine();
				while (line != null && line != ("")) {
					String[] lineItem = line.split(",");
					String name = lineItem[0];
					int priority = 1;
					if (lineItem != null) {
						try {
							priority = Integer.parseInt(lineItem[1]);
						} catch (NumberFormatException e) {
							System.out.println("Error, item not an integer");
						}
					}
					String category = lineItem[2];
					ListItem item = new ListItem(name, priority, category);
					list.add(item);
					line = br.readLine();
				}
			} catch (IOException e) {
				System.out.println("Io exception encountered.");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found.");
		}
		list1 = list;
		return list;
	}

	public void removeItems() {
		while (true) {
			System.out.println(printList());
			System.out.println("Which item would you like to remove? Please enter the corresponding number.\n"
					+ "Press any non-number to exit.");
			String num = sc.nextLine();
			try {
				int choice = Integer.parseInt(num);
				list1.remove((choice - 1));

			} catch (NumberFormatException e) {
				System.out.println("Okay, going back to main menu");
				break;
			}
		}
	}

	public void modifyItems() {
		while (true) {
			System.out.println(printList());
			System.out.println("Which item would you like to change? Please enter the corresponding number.\n"
					+ "Press any non-number to exit.");
			String num = sc.nextLine();
			try {
				int choice = Integer.parseInt(num);
				System.out.println("What would you like to change it to?");
				String response = sc.nextLine();
				System.out.println("Would you like to change it's priority? (y)es or (n)o");
				String yN = sc.nextLine();

				if (yN.contains("y")) {
					System.out.println("What priority would you like to assign it to? (h)igh, (m)edium, or (l)ow?");
					String priority = sc.nextLine();
					int prio = 3;
					if (priority.contains("m")) {
						prio = 2;
					} else if (priority.contains("h")) {
						prio = 1;
					}
					System.out.println("Would you like to change the item's category? (y)es or (n)o");
					String answer = sc.nextLine();
					if (answer.contains("y")) {
						System.out.println("Okay, what would you like the category to be?");
						String cat = sc.nextLine();
						ListItem item = new ListItem(response, prio, cat);
						list1.set((choice - 1), item);
					} else {
						ListItem item = new ListItem(response, prio, list1.get((choice - 1)).getCategory());
						list1.set((choice - 1), item);
					}

				} else {
					System.out.println("Would you like to change the item's category? (y)es or (n)o");
					String answer = sc.nextLine();
					if (answer.contains("y")) {
						System.out.println("Okay, what would you like the category to be?");
						String cat = sc.nextLine();
						ListItem item = new ListItem(response,
								Integer.parseInt((list1.get((choice - 1)).getPriority())), cat);
						list1.set((choice - 1), item);
					} else {

						ListItem item = new ListItem(response,
								Integer.parseInt((list1.get((choice - 1)).getPriority())),
								list1.get((choice - 1)).getCategory());
						list1.set((choice - 1), item);
					}
				}

			} catch (NumberFormatException e) {
				System.out.println("Okay, going back to main menu");
				break;
			}
		}
	}

}