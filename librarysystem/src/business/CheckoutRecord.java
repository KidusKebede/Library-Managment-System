package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class CheckoutRecord implements Serializable {
	public List<CheckoutRecordEntry> entryRecords = new ArrayList<>();

	public List<CheckoutRecordEntry> getEntryRecords() {
		return entryRecords;
	}

	private static final long serialVersionUID = -2226197306790714013L;

	public CheckoutRecord(List<CheckoutRecordEntry> entryRecords) {
		this.entryRecords = entryRecords;
	}

	public void setEntryRecords(List<CheckoutRecordEntry> entryRecords) {
		this.entryRecords = entryRecords;
	}

}
