package project3;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 *
 * @author wrightwj William Wright
 */
public class Customer {
	
	/** The customer ID counter. */
	//individual ID for customers
	private static double customerIDCounter = 1;
	
	/** The queue num. */
	private int queueNum;
	
	/** The customer ID. */
	private double customerID;
	
	/** The transaction time. */
	//global, static arrival rate for the customers, input by the user
	private double transactionTime;
	
	/** The departure time. */
	//not sure what to do yet, will update later.
	private double departureTime;
	
	/** The express status. */
	boolean expressStatus;
	
	/** The arrival time. */
	private int arrivalTime;

	/**
	 * Instantiates a new customer.
	 *
	 * @param time the "time" that has passed, records arrival time
	 */
	public Customer(int time) {
		arrivalTime = time;
		this.transactionTime = (int)(Math.random() * 9) + 2;
		customerID = customerIDCounter++;
		departureTime = 0;
		expressStatus = transactionTime < 4;
	}
	
	/**
	 * Checks if is express.
	 *
	 * @return true, if is express
	 */
	public boolean isExpress() {
		//return true if transaction time is 3 or less, which qualifies them for express lane.
		return expressStatus;
	}
	
	/**
	 * Sets the departure time.
	 *
	 * @param dt the new departure time
	 */
	public void setDepartureTime(double dt) {
		departureTime = dt;
	}
	
	/**
	 * Gets the transaction time.
	 *
	 * @return the transaction time
	 */
	public double getTransactionTime(){
		return transactionTime;
	}
	
	/**
	 * Sets the queue number per customer.
	 *
	 * @param i the queue number
	 */
	public void setQueue(int i) {
		queueNum = i;
	}
	
	/**
	 * Gets the queue number.
	 *
	 * @return the queue index
	 */
	public int getQueue() {
		return queueNum;
	}
	
	/**
	 * Decrement transaction time.
	 */
	public void decrementTransTime() {
		departureTime++;
		transactionTime--;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CustomerID: "+customerID+" -- "
				+"Express Status: "+expressStatus+" -- "+
				"Arrival time: " + arrivalTime+" -- "+
				"Time in queue: "+departureTime);
		return sb.toString();
	}	
}
