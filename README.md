# Java Process Scheduling Simulator 📊

## Overview 📋
This Java application simulates an operating system's process scheduling mechanism, demonstrating how resources are allocated to processes in a queue. The program implements a singly linked list data structure to manage processes and their required resources, showcasing fundamental concepts in process scheduling algorithms.

![Process Scheduling Simulation](process_simulation.png)

## Project Features 🌟
- **Resource Management:** Simulates allocation of resources (A, B, C) to processes
- **Process Queue Implementation:** Uses a custom linked list to maintain process order
- **Two Simulation Modes:**
  - Part A: Predefined process list from file input
  - Part B: Random process generation with growth simulation
- **Cycle Analysis:** Calculates the number of cycles needed to complete all processes
- **Growth Monitoring:** Tracks queue length over time in extended simulations

## Simulation Rules 🔄
- Each process requires one or more resources (A, B, C)
- A process can only start if all its required resources are available
- Processes must execute in order (no skipping ahead in the queue)
- When a process starts, it uses its resources for exactly one cycle
- After each cycle in Part B, two new random processes are added to the queue

## Technical Implementation ⚙️

### Data Structures
The project uses a custom singly linked list implementation with two main classes:
- `PList`: Manages the list of processes and resource allocation
- `Processes`: Represents individual processes with their required resources

```java
public class PList {
    static class Node {
        private Processes data;
        private int id;
        private Node next;
        
        public Node(Processes e, Node n) {
            this.data = e;
            this.next = n;
        }
    }
    
    // List management variables
    Node head = null;               
    private Node tail = null;               
    private int size = 0; 
    private String[] resource = {"A","B","C"};
}
```

### Resource Allocation Algorithm
```java
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
```

### Random Process Generation
```java
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
    // Additional cases for other resource combinations
    }
    Processes temp = new Processes(sb.toString());
    add(temp);
}
```

## Sample Output 📋

### Part A: Fixed Process List
```
Part A:
Total number of cycles needed: 6
```

### Part B: Growth Simulation
```
Part B:
Length of processes at cycle 100: 104
Length of processes at cycle 200: 107
Length of processes at cycle 300: 63
Length of processes at cycle 400: 139
```

## Project Structure 📂
- `PList.java`: Core data structure for process management
- `PartA.java`: Implementation of the fixed process list simulation
- `PartB.java`: Implementation of the random process growth simulation
- `Test.java`: Combined tester for both parts
- `inputFile.txt`: Sample input file with predefined processes

## How to Run 🚀
1. Compile all Java source files
2. Ensure `inputFile.txt` is in the correct directory
3. Run `Test.java` to execute both Part A and Part B

## Skills Demonstrated 💪
- **Data Structure Implementation:** Custom linked list design
- **Algorithm Design:** Resource allocation scheduling
- **Simulation Logic:** Computer systems process management
- **File I/O:** Reading structured process data from files
- **Random Generation:** Creating varied test cases
