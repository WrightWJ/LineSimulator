package project3;

import javax.swing.JOptionPane;
/**
 * 
 * @author Wrightwj William Wright
 *
 */
public class Sim {

	private static double arrivalRate;
	private static boolean expressLine;
	private static int numLines;

	//decides true or false if there are 1 lines or 1 line per server
	private static boolean numServers;
	private static double runTime;


	//get input and run the simulator
	public static void main(String[] args) 
	{
		//must take in a number
		arrivalRate = Double.parseDouble(JOptionPane.showInputDialog("Please enter Arrival Rate in 'seconds' "));
		//must take in true or false
		expressLine = Boolean.parseBoolean(JOptionPane.showInputDialog("Is there an express lane? (True or False)"));
		//must pass a positive number
		numLines = Math.abs(Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of servers, in whole numbers\n for 1, enter 1")));
		numServers = Boolean.parseBoolean(JOptionPane.showInputDialog("Enter true (one line for all servers)\nor false (one line per server)"));
		runTime = Double.parseDouble(JOptionPane.showInputDialog("Please enter the amount of time (in 'seconds') you want the program to run for")); 
		//start
		runSim();
	}		
	//run the simulator
	private static void runSim() {
		//create new line with given amounts
		Lines li = new Lines(numLines,numServers);
		//set express to user input
		li.setExpress(expressLine);
		//while time is less than total time, distribute customers at the given ate
		for (int time=0;time<runTime;time++) 
		{
			if (time%arrivalRate == 0) 
			{
				li.distributeCustomer(new Customer(time));
			}
			//move customers through lines
			li.processCustomers();
		}
		//print output
		System.out.println(li.toString());
	}
}
