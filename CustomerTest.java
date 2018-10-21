package project3;

import org.junit.jupiter.api.Test;

class CustomerTest {
	@Test
	void test() {
		Customer cus = new Customer(1);
		cus.isExpress();
		cus.setDepartureTime(100);
		cus.getTransactionTime();
		cus.setQueue(1);
		cus.getQueue();
		cus.decrementTransTime();
		System.out.println(cus.toString());
	}
}
