package p1;


import p1.PList.Processes;
import java.io.*;

public class Test {
	public static void main(String [] args) throws IOException{
		//part 1:
		System.out.println("Part A:");
		File file = new File("inputFile");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String st = br.readLine();
	    PList processList = createCycles(st);
	    System.out.println(processList.findCycle());
	    
	    //part 2:
	    System.out.println("Part B:");
	    createCycles();
	}
	
	/**
	 * create a Process list from a String
	 * @param input String to create input list
	 * @return the process list
	 */
	public static PList createCycles(String input) {
		PList processList = new PList();
		String[] arrOfStr = input.split(";");
		for (int i = 0; i < arrOfStr.length; i++) {
			Processes temp = new Processes(arrOfStr[i]);
			processList.add(temp);
		}
		return processList;
	}
	
	/**
	 * create a Process list with 20 processes； 
	 * add 2 more process to the end of the list
	 * when making a new cycle.
	 * Output the length of the list of processes 
	 * every 100th cycle to watch its growth
	 * @param input String to create input list
	 * @return the process list
	 */
	public static PList createCycles() {
		// create the initial 20 random accesses
		PList processList = new PList();

		for (int i = 0; i < 20; i++) {
			// add new processes at the end when new cycle created.
			processList.addRandomProcess();
		}
		int prevSize;
		int hundredth = 100;
		int totalCycle = 0;
		while (processList.getSize() != 0) {
			// print cycle and processes at hundredth
			if (hundredth <= totalCycle && hundredth <= 900) {
				StringBuffer sb = new StringBuffer("Length of processes at cycle ");
				sb.append(hundredth);
				sb.append(": ");
				sb.append(processList.getSize());
				System.out.println(sb.toString());
				hundredth += 100;
			}
			// get previous number of cycle of the list
			prevSize = processList.getCycle();
			// remove the first element
			processList.removeFirst();
			if (processList.getSize() == 0) break;
			// if a cycle is needed, created 2 processes
			if (processList.getCycle() < prevSize) {
				totalCycle++;
				processList.addRandomProcess();
				processList.addRandomProcess();
			}
			if (totalCycle > 1000) {
				StringBuffer sb = new StringBuffer("Remaining Processes After 1000 cycles: ");
				sb.append(processList.getSize());
				System.out.println(sb.toString());
				break;
			} 
		}
		

		return processList;
	}
	
	
}
