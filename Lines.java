package project3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// TODO: Auto-generated Javadoc
/**
 * The Class Lines.
 * @author WrightWJ William Wright
 */
public class Lines {
	
	/** The Constant MAX_LENGTH. */
	private static final int MAX_LENGTH = 5;
	
	/** The num servers. */
	//true if numServers is 1, false if number of servers is equal to number of lanes.
	private boolean numServers;
	
	/** The a list. */
	private ArrayList<Queue<Customer>> aList;
	
	/** The express. */
	private boolean express;
	
	/** The cust served. */
	private ArrayList<Customer> custServed;
	/**
	 * pre: please pass this method valid integers between 1 and 5
	 * post: will create an ArrayList of queues implemented by LinkedLists of customers.
	 * @param numLines the number of lines
	 * @param numServers the number of servers
	 */
	public Lines(int numLines, boolean numServers) {

		if (numLines > MAX_LENGTH || numLines < 1) 
		{
			numLines=MAX_LENGTH;
			numServers=false;
		} 
		else 
		{
			//if numservers = true, one server
			//false means servers == lines
			this.numServers=numServers;
		}
		custServed = new ArrayList<Customer>();
		aList = new ArrayList<Queue<Customer>>();
		//add blank queues to aList
		for(int i = 0;i<numLines;i++) {
			aList.add(new LinkedList<Customer>());
		}
	}
	
	/**
	 * Distribute customer to the shortest line.
	 *
	 * @param c the custoemr to be distributed
	 */
	public void distributeCustomer(Customer c) 
	{
		int index = getShortest(aList);
		if (aList.size() == 1 && !numServers) {
			//this is only called if there is one line and multiple servers

			distributeCustomersToServers(c);
			return;
		}
		if (c.isExpress() && express) {
			c.setQueue(0);
			aList.get(0).add(c);
		}
		else
		{
			c.setQueue(index);
			aList.get(index).add(c);
		}
	}
	//distribute 1 line to N servers
	/**
	 * Distribute customers to servers.
	 *
	 * @param c the c
	 */
	//ignores express lane because there are N different servers and only 1 line
	private void distributeCustomersToServers(Customer c) {
		//given one queue, I need to create more lists based off the queue. Will create more lines based on queue.
		int i = getShortest(aList);
		c.setQueue(i);
		aList.get(i).add(c);


	}
	
	/**
	 * Gets the shortest list.
	 *
	 * @param list the list of queues of customers
	 * @return the shortest line
	 */
	//janky but it works well so whatever 
	public int getShortest(ArrayList<Queue<Customer>> list) {
		int index=0;
		int smallest = Integer.MAX_VALUE;
		int expressIndex = 0;

		if (list.size()==0) {
			return 0;
		}
		if (express) {
			expressIndex = 1;
		}
		for (int i = expressIndex; i <list.size();i++) 
		{
			if (smallest > list.get(i).size()) 
			{
				smallest = list.get(i).size();
				index = i;
			}

		}
		return index;
	}
	
	/**
	 * Process customers through servers
	 */
	public void processCustomers() {
		for (Queue<Customer> que: aList) {
			if (que.isEmpty()){
				return;
			}
			if (que.peek().getTransactionTime() == 0) 
			{
				custServed.add(que.poll());

			} else 
			{
				que.peek().decrementTransTime();
				continue;
			}
		}
	}
	
	/**
	 * Setter for express boolean.
	 *
	 * @param e sets express to true or false based off user input.
	 */
	public void setExpress(boolean e) {
		express = e;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		//check ArrayList for which queue the customers came from
		for (int i = 0; i<aList.size();i++) {
			//check each cell in the arraylist for the cells with a queue = index.
			sb.append("Customers served by server "+(i+1)+": \n");
			for (int j = 0; j<custServed.size();j++) {
				if (custServed.get(j).getQueue()==i) {
					sb.append(custServed.get(j).toString()+"\n");
					count++;
				}
				continue;
			}
			//i + 1 converts between a 0 based index to a 1 based index.
			sb.append("Total processed by "+(i+1)+": "+count+"\n");
			count = 0;
		}
		return sb.toString();

	}
}
