package project3;

import org.junit.jupiter.api.Test;

class LinesTest {

	@Test
	void test() {
		int numlines = 3;
		boolean numServers = true;
		int arrivalRate = 3;

		Lines li = new Lines(numlines,numServers);
		
		
		Lines li2 = new Lines(numlines+1,!numServers);
		
		int runTime = 100;

		for (int time =0; time<runTime;time++) {
			if (time%arrivalRate==0) 
			{
				li.distributeCustomer(new Customer(time));
				li2.distributeCustomer(new Customer(time));
			}
			//pass a random number
			li.distributeCustomer(new Customer(15));
			li2.distributeCustomer(new Customer(3));
			li.processCustomers();
			li2.processCustomers();
		}
		System.out.println(li.toString());
	}
}
