package p1;

public class PList {
	
	static class Node {
		
		// field 
	    private Processes data;
	    private int id;
	    @SuppressWarnings("unused")
		private Node next;
	    
	    /**
	     * Creates a node with the given element and next node.
	     * @param e  the element to be stored
	     * @param n  reference to a node that should follow the new node
	     */
	     public Node(Processes e, Node n) {
	        this.data = e;
	        this.next = n;
	    }
	}
	
	static class Processes {
		//field
		private String[] resourceArray = new String[3];
		//constructor
		public Processes(String input) {
			for (int i = 0; i < input.length(); i++) {
				 if (Character.valueOf(input.charAt(i)).compareTo(Character.valueOf('A')) == 0) {
					 this.resourceArray[0] = "A"; 
				 } else if (Character.valueOf(input.charAt(i)).compareTo(Character.valueOf('B')) == 0) {
					 this.resourceArray[1] = "B";
				 } else if (Character.valueOf(input.charAt(i)).compareTo(Character.valueOf('C')) == 0) {
					 this.resourceArray[2] = "C"; 
				 }
			}
		}
		//access method
		
		/**
		 * @return the process represented in array.
		 */
		public String[] getProcess() {
			return this.resourceArray;
		}
		
		/**
		 * @return the String format of the resource required by the process.
		 */
		public String toString() {
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < 3; i++) {
				String temp = resourceArray[i];
				if (temp != null) {
					sb.append(temp);
				}
			}
			return sb.toString();
		}
	}
	
	// instance variables
	Node head = null;               
	private Node tail = null;               
	private int size = 0; 
	private String[] resource = {"A","B","C"};
	// constructor
	public PList() {}
	
	//methods
	/**
	 * Add a cycle to the linked list
	 * @param e
	 */
	public void add(Processes e) {
		Node newest = new Node(e, null );    
		if (size == 0)
			head = newest;                         
		else
			tail.next = newest;                  
		tail = newest;
		size++;
	}
	
	  /**
	   * Removes and returns the first element of the list.
	   * @return the removed element (or null if empty)
	   */
	  public void removeFirst() {                   // removes and returns the first element
	    if (size == 0) return;              // nothing to remove
	    head = head.next;                   // will become null if list had only one node
	    size--;
	    if (size == 0)
	      tail = null;                           // special case as list is now empty
	  }
	
	/**
	 * Remove the required resource of a process in the resource pool
	 * @param p the process to run
	 */
	public String[] removeResource(Processes p, String[] a) {
		String[] temp = a;
		for (int i = 0; i < 3; i++) {
			try {
				if (a[i].compareTo(p.resourceArray[i]) == 0) {
					temp[i] = null;
				}
			} catch (NullPointerException ex) { }
		}
		a = temp;
		return a;
	}
	
	/**
	 * return the number of processes
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * return the number of cycles
	 * @return
	 */
	public int getCycle() {
		int count = 1;
		Node node = head;
		while (node.next != null) {
			if (!ifContain(node.data, resource)) {
				count++;
				// reset the resource poll
				resource[0] = "A";
				resource[1] = "B";
				resource[2] = "C";
			}
			resource = removeResource(node.data, resource);
			node = node.next;
		}
		return count;
	}
	
	/**
	 * Check if all the required resource for a process is included in the resource pool
	 * @param e the process to check
	 * @return true if the resource pool contains the required resource for the process
	 */
	public boolean ifContain(Processes e,  String[] a) {
		String[] tmp = e.getProcess();
		boolean ifcontain = true;
		for (int i = 0; i < 3; i++) {
			if (tmp[i] != null && a[i] == null) {
				ifcontain = false;
			}
		}
		return ifcontain;
	}
	
	public void addRandomProcess() {
		StringBuffer sb = new StringBuffer("");
		int rand = (int)(Math.random() * 7);
		switch (rand) {
		case 0:
			sb.append("A");
			break;
		case 1:
			sb.append("B");
			break;
		case 2:
			sb.append("C");
			break;
		case 3:
			sb.append("AB");
			break;
		case 4:
			sb.append("AC");
			break;
		case 5:
			sb.append("BC");
			break;
		case 6:
			sb.append("ABC");
			break;
		}
		Processes temp = new Processes(sb.toString());
		add(temp);
	}
	
	public String findCycle() {
		StringBuilder sb = new StringBuilder("Total number of cycles needed: ");
		sb.append(getCycle());
		return sb.toString();
	}
}
