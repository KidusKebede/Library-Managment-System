package appLab;

//import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import business.BookCopy;
import business.Book;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import dataaccess.TestData;

public class testdata {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DataAccessFacade df = new DataAccessFacade();
		HashMap<String, LibraryMember> hm = new HashMap<String, LibraryMember>();
		// HashMap<String, CheckoutRecordEntry> hm2 = new HashMap<String,
		// CheckoutRecordEntry>();
		hm = df.readMemberMap();
		// hm2 = df.readRecordMap();
		System.out.println("<---------------------  Members List  --------------------->");
		int countMembers = 1;
		for (LibraryMember memberValue : hm.values()) {
			System.out.println(countMembers + ": " + memberValue.toString());
			countMembers++;
		}
		System.out.println("\n<---------------------  Books List  --------------------->");
		HashMap<String, Book> book = new HashMap<String, Book>();
		book = df.readBooksMap();
		int countBooks = 1;
		for (Book books : book.values()) {
			System.out.println(countBooks + ": " + books.toString());
			countBooks++;
		}

		HashMap<String, LibraryMember> members = new HashMap<>();
		members = df.readMemberMap();
		LibraryMember amemeber = members.get("1010");
		List<CheckoutRecordEntry> rdentries = amemeber.getCheckoutRecord().getEntryRecords();
		System.out.println(" 1010 number of entries = " + amemeber.getCheckoutRecord().getEntryRecords().size());
		for (CheckoutRecordEntry checkentry : rdentries) {
			System.out.println(checkentry.toString());
		}
	}
}
