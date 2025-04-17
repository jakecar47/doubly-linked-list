/** 
 * File: DoublyLinkedList.java
 * Name: Jake Caruana
 * Email: jtc94964@uga.edu
 * Class: CSCI2720
 * Date Created: 9/26/2024
 */

package project2;

//DoublyLinkedList class implementing operations on a doubly linked list
public class DoublyLinkedList<T extends Comparable<T>> {
	private NodeType<T> head;
	private NodeType<T> tail;

	// Constructor to create an empty list
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
	}

	// Insert method - inserts a new item in sorted order
	public void insertItem(T item) {
		NodeType<T> newNode = new NodeType<>(item);

		if (head == null) { // If the list is empty
			head = newNode;
			tail = newNode;
		} else {
			NodeType<T> current = head;

			// Traverse the list to find the correct position for insertion
			while (current != null && current.info.compareTo(item) < 0) {
				current = current.next;
			}

			// If the data already exists
			if (current != null && current.info.compareTo(item) == 0) {
				System.out.println("Item already exists");
				return;
			}

			// Insert at the beginning
			if (current == head) {
				newNode.next = head;
				head.back = newNode;
				head = newNode;
			}
			// Insert at the end
			else if (current == null) {
				tail.next = newNode;
				newNode.back = tail;
				tail = newNode;
			}
			// Insert in the middle
			else {
				newNode.back = current.back;
				newNode.next = current;
				current.back.next = newNode;
				current.back = newNode;
			}
		}
	}

	// Delete method - deletes a node with the given data
	public void deleteItem(T item) {
		if (head == null) {
			System.out.println("You cannot delete from an empty list");
			return;
		}

		NodeType<T> current = head;

		while (current != null && current.info.compareTo(item) != 0) {
			current = current.next;
		}

		// If the item does not exist in the list
		if (current == null) {
			System.out.println("The item is not present in the list");
			return;
		}

		// Deleting the only element in the list
		if (head == tail) {
			head = null;
			tail = null;
		}
		// Deleting the first element
		else if (current == head) {
			head = head.next;
			head.back = null;
		}
		// Deleting the last element
		else if (current == tail) {
			tail = tail.back;
			tail.next = null;
		}
		// Deleting a middle element
		else {
			current.back.next = current.next;
			current.next.back = current.back;
		}
	}

	// Reverse the linked list
	public void reverseList() {
		if (head == null) {
			System.out.println("The list is empty");
			return;
		}

		NodeType<T> current = head;
		NodeType<T> temp = null;

		// Swap the next and prev pointers of all nodes
		while (current != null) {
			temp = current.back;
			current.back = current.next;
			current.next = temp;
			current = current.back;  // Move to the next node in the original sequence
		}

		// Adjust head and tail
		if (temp != null) {
			head = temp.back;
		}

		System.out.println("The reversed list: " + this);
	}

	// Delete a subsection between two bounds (inclusive)
	public void deleteSubsection(T lowerBound, T upperBound) {
		if (head == null) {
			System.out.println("The list is empty");
			return;
		}

		NodeType<T> current = head;
		
		// find values inside lower and upper bound
		while (current != null && current.info.compareTo(lowerBound) < 0) {
			current = current.next;
		}

		while (current != null && current.info.compareTo(upperBound) <= 0) {
			NodeType<T> temp = current;
			current = current.next;
			deleteItem(temp.info); // Delete each node within the range
		}
	}

	// Swap alternate nodes in the list
	public void swapAlternate() {
		NodeType<T> current = head;
		
		// iterate through list and swap alt values
		while (current != null && current.next != null) {
			T temp = current.info;
			current.info = current.next.info;
			current.next.info = temp;
			current = current.next.next; // Move two nodes ahead
		}
	}

	// Print the list in reverse
	public void printReverse() {
		NodeType<T> current = tail;
		while (current != null) {
			System.out.print(current.info + " ");
			current = current.back;
		}
	}

	// Print the list
	public void print() {
		NodeType<T> current = head;
		while (current != null) {
			System.out.print(current.info + " ");
			current = current.next;
		}
	}
}