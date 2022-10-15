package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
	private String checkoutDate; // LocalDate.now().toString()
	private int duedate;
	private BookCopy bookcopies;

	private static final long serialVersionUID = -2226197306790714013L;

	public CheckoutRecordEntry(String checkoutDate, int duedate, BookCopy bookcopies) {
		this.checkoutDate = checkoutDate;
		this.duedate = duedate;
		this.bookcopies = bookcopies;
	}

	public BookCopy getBookcopies() {
		return bookcopies;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public int getDuedate() {
		return duedate;
	}

	@Override
	public String toString() {
		return "CheckoutDate: " + checkoutDate + ", duedate: " + duedate + ", BookCopy: " + getBookcopies().toString();
	}
}
