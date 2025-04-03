package cursorArray;

public class cursorArray<T extends Comparable<T>> {

	// Array of Nodes and its size
	private Node<T>[] cursorArray;
	private int size;

	public cursorArray(int size) {
		this.size = size;
		cursorArray = new Node[size];
	}

	// Initialization of the cursor array
	public void initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++) {
			cursorArray[i] = new Node<>(null, i + 1);
		}
		cursorArray[cursorArray.length - 1] = new Node<>(null, 0);
	}

	// Malloc-like function to allocate memory
	public int malloc() {
		int p = cursorArray[0].next;
		cursorArray[0].next = cursorArray[p].next;
		return p;
	}

	// Free the allocated space
	public void free(int p) {
		cursorArray[p] = new Node<>(null, cursorArray[0].next);
		cursorArray[0].next = p;
	}

	// Check if the node is null (unused slot)
	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	// Check if the list is empty
	public boolean isEmpty(int l) {
		return cursorArray[l].next == 0;
	}

	// Check if it's the last element
	public boolean isLast(int p) {
		return cursorArray[p].next == 0;
	}

	// Create a new list
	public int createList() {
		int l = malloc();
		if (l == 0) {
			System.out.println("Error: Out of space!!!");
		} else {
			cursorArray[l] = new Node<T>((T) "-", 0);
		}
		return l;
	}

	// Insert a node at the head of the list
	public void insertAtHead(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new Node<>(data, cursorArray[l].next);
			cursorArray[l].next = p;
		} else {
			System.out.println("Error: Out of space!!!");
		}
	}

	// Traverse the list
	public void traverseList(int l) {
		System.out.print("list_" + l + "-->");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			System.out.print(cursorArray[l].data + "-->");
		}
		System.out.println("null");
	}

	// Find the previous node given a data element
	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].next].data.equals(data))
				return l;
			l = cursorArray[l].next;
		}
		return -1; // not found
	}

	// Delete a node with given data
	public void delete(T data, int l) {
		int p = findPrevious(data, l);
		if (p != -1) {
			int c = cursorArray[p].next;
			Node<T> temp = cursorArray[c];
			cursorArray[p].next = temp.next;
			free(c);
		}
	}

	// Print the cursor array for debugging purposes
	public void print() {
		System.out.println("data\tNext\n================");
		for (int i = 0; i < cursorArray.length; i++) {
			System.out.println(i + "\t" + cursorArray[i]);
		}
	}

}
