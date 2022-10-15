package appLab;

import java.awt.EventQueue;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import business.*;
import dataaccess.DataAccessFacade;
import dataaccess.CustomTableModel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;
import java.awt.event.ActionEvent;

public class AppLab6 extends JFrame {

	JList<ListItem> linkList;
	JPanel cards;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfisbnaddcopy;
	private JTextField tfcopynumber;
	private JTextArea taBottom;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private static final int SCREEN_WIDTH = 700;
	private static final int SCREEN_HEIGHT = 500;

	private final String[] col = { "Checkout Date", "Due Date", "ISBN", "Title", "Num of Copies" };
	private String[][] r = { { "Checkout Date", "Due Date", "ISBN", "Title", "Num of Copies" },
			{ "Checkout Date", "Due Date", "ISBN", "Title", "Num of Copies" } };

	private CustomTableModel model;

	ListItem liLogin = new ListItem("Login", true);
	ListItem liViewTitles = new ListItem("Add new library member", false);
	ListItem liAddBook = new ListItem("Add copy book", false);
	ListItem liCheckoutBook = new ListItem("Checkout Book", false);

	ListItem licheckoutRecord = new ListItem("Checkout Record", false);
	ListItem liaddnewBook = new ListItem("Add new Book", false);
	ListItem limemberCheckoutRecord = new ListItem("Member Checkout Record", false);
	ListItem liavailablebooks = new ListItem("Available Books", false);

	ListItem[] librarianItem = { liLogin, liCheckoutBook, liavailablebooks };
	ListItem[] adminItem = { liLogin, liViewTitles, liAddBook, licheckoutRecord, liaddnewBook, limemberCheckoutRecord,
			liavailablebooks };

	private JLabel lblNewLabel_8;
	private JLabel lbMemberId;
	private JLabel lbFirstName;
	private JLabel lbTellNum;
	private JLabel lblNewLabel_9;
	private JPanel panel2_1;
	private JLabel lbstreet;
	private JTextField tfmemberid;
	private JTextField tffirstname;
	private JTextField tflastname;
	private JTextField tftellnumber;
	private JLabel lbcity;
	private JLabel lbstate;
	private JLabel lbzipcode;
	private JTextField tfstreet;
	private JTextField tfcity;
	private JTextField tfstate;
	private JTextField tfzipcode;
	private JLabel lbisbn;
	private JLabel lbmemeberid;
	private JTextField tfisbn;
	private JTextField tfmemberidcheckout;
	private JButton btnaddmember;
	private JButton btncheckoutbook;

	DataAccessFacade dataAccess = new DataAccessFacade();
	private JPanel panel5;
	private JPanel panel6;
	private JPanel panel7;
	private JPanel panel8;
	JTable table;
	private JButton btnrefresh;
	JScrollPane scrollPane2;
	JScrollPane scrollPane22;
	JScrollPane scrollPane8;
	private JButton btneditmember;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JTextField tfisbnnewbook;
	private JTextField tftitlenewbook;
	private JTextField tfcheckoutlength;
	private JTextField tfauthors;
	private JTextField tfmemberid7;
	private JButton btnsearch7;
	private JLabel lbmemberid7;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppLab6 frame = new AppLab6();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppLab6() {
		initializeWindow();
		createLinkLabels();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		createPanels();

		contentPane.setLayout(null);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);

		splitPane.setBounds(5, 5, 837, 315);
		contentPane.add(splitPane, "name_1212415849105000");
		splitPane.setDividerLocation(160);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(5, 337, 837, 54);
		contentPane.add(panel);
		panel.setLayout(null);

		taBottom = new JTextArea();
		taBottom.setBounds(0, 0, 846, 44);
		panel.add(taBottom);
		taBottom.setText("Welcome to Group 3 app");
		// createMainPanels();

		linkList.addListSelectionListener(event -> {
			String value = linkList.getSelectedValue().getItemName();
			boolean allowed = linkList.getSelectedValue().highlight();
			CardLayout cl = (CardLayout) (cards.getLayout());
			if (!allowed) {
				value = liLogin.getItemName();
				linkList.setSelectedIndex(0);
			}
			if (value.equals("Add new library member"))
				updateData();
			cl.show(cards, value);
		});

	}

	public void createMainPanels() {
		cards = new JPanel(new CardLayout());
		cards.add(getPanel1(), liLogin.getItemName());
		cards.add(getPanel2(), liViewTitles.getItemName());
		cards.add(getPanel3(), liAddBook.getItemName());
		cards.add(getPanel4(), liCheckoutBook.getItemName());

		cards.add(getPanel5(), licheckoutRecord.getItemName());
		cards.add(getPanel6(), liaddnewBook.getItemName());
		cards.add(getPanel7(), limemberCheckoutRecord.getItemName());
		cards.add(getPanel8(), liavailablebooks.getItemName());

	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void createPanels() {
		panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(10, -2, 96, 26);

		panel1.add(lblNewLabel);
		panel1 = new javax.swing.JPanel() {
			public void paintComponent(Graphics g) {
				ImageIcon im = new ImageIcon("library.jpg");
				Image i = im.getImage();
				g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
			}

		};

		panel2 = new JPanel();
		panel2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Add new library member");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 5, 661, 24);
		panel2.add(lblNewLabel_3);

		panel3 = new JPanel();
		panel3.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Add copy book");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 5, 661, 19);
		panel3.add(lblNewLabel_4);

		panel4 = new JPanel();
		panel4.setLayout(null);

		lblNewLabel_8 = new JLabel("Checkout book");
		lblNewLabel_8.setForeground(Color.BLUE);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(10, 5, 661, 23);
		panel4.add(lblNewLabel_8);

		panel5 = new JPanel();
		panel5.setLayout(null);

		JLabel lbcheckoutRecord5 = new JLabel("Checkout Record");
		lbcheckoutRecord5.setForeground(new Color(0, 0, 255));
		lbcheckoutRecord5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbcheckoutRecord5.setBounds(27, 10, 295, 27);
		panel5.add(lbcheckoutRecord5);

		scrollPane2 = new JScrollPane();
		String[][] rows = prepareRows();
		JTable table = new JTable(rows, col);

		table.setBounds(26, 46, 600, 250);
		scrollPane2.setViewportView(table);
		scrollPane2.setBounds(26, 46, 600, 250);
		panel5.add(scrollPane2);
		panel5.setVisible(true);

		panel6 = new JPanel();
		panel6.setLayout(null);

		JLabel lbaddNewBook6 = new JLabel("Add New Book");
		lbaddNewBook6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbaddNewBook6.setForeground(new Color(0, 0, 255));
		lbaddNewBook6.setBounds(50, 5, 313, 23);
		panel6.add(lbaddNewBook6);

		panel7 = new JPanel();
		panel7.setLayout(null);

		JLabel lbmemberCheckoutRecord7 = new JLabel("Member Checkout Record");
		lbmemberCheckoutRecord7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbmemberCheckoutRecord7.setForeground(new Color(0, 0, 255));
		lbmemberCheckoutRecord7.setBounds(10, 10, 273, 31);
		panel7.add(lbmemberCheckoutRecord7);

		panel8 = new JPanel();
		panel8.setLayout(null);

		JLabel lboverduePossession8 = new JLabel("Available Books");
		lboverduePossession8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lboverduePossession8.setForeground(new Color(0, 0, 255));
		lboverduePossession8.setBounds(195, 0, 260, 28);
		panel8.add(lboverduePossession8);

		scrollPane8 = new JScrollPane();
		String[][] bookrows = prepareRowsForBooks();
		JTable booktable = new JTable(bookrows, new String[] { "ISBN", "Title", "Checkout Length" });

		booktable.setBounds(26, 46, 600, 250);
		scrollPane8.setViewportView(booktable);
		scrollPane8.setBounds(26, 46, 600, 250);
		panel8.add(scrollPane8);
		panel8.setVisible(true);

		cards = new JPanel(new CardLayout());
		cards.add(panel1, liLogin.getItemName());
		panel1.setLayout(null);

		tfUsername = new JTextField();
		tfUsername.setBounds(311, 110, 150, 32);
		panel1.add(tfUsername);
		tfUsername.setColumns(10);

		tfPassword = new JPasswordField(10);
		tfPassword.setBounds(311, 152, 150, 32);
		panel1.add(tfPassword);
		tfPassword.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(187, 110, 114, 32);
		panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(187, 152, 114, 32);
		panel1.add(lblNewLabel_2);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogin.addActionListener(evt -> {

			String user = tfUsername.getText().trim();
			String pwd = tfPassword.getText().trim();
			if (user.length() == 0 || pwd.length() == 0) {
				System.out.println("Id and Password fields must be nonempty");
				taBottom.setForeground(Color.RED);
				taBottom.setText("Id and Password fields must be nonempty");

			} else {
				User login = new User(user, pwd, null);

				List<User> list = Data.logins;
				User u = Util.findUser(list, login);
				if (u == null) {
					System.out.println("Login failed!");
					taBottom.setForeground(Color.RED);
					taBottom.setText("Login failed!");
				} else {
					Data.currentAuth = u.authorization;
					System.out.println("Login successful");
					taBottom.setForeground(Color.GREEN);
					taBottom.setText("Login successful");
					updateLeftPanel(Data.currentAuth);
					repaint();
					tfUsername.setText("");
					tfPassword.setText("");
				}

			}

		});
		btnLogin.setBounds(311, 207, 150, 32);
		panel1.add(btnLogin);
		cards.add(panel2, liViewTitles.getItemName());

		lbMemberId = new JLabel("Member ID");
		lbMemberId.setBounds(10, 39, 96, 13);
		panel2.add(lbMemberId);

		lbFirstName = new JLabel("First Name");
		lbFirstName.setBounds(10, 62, 96, 13);
		panel2.add(lbFirstName);

		JLabel lbLastName = new JLabel("Last Name");
		lbLastName.setBounds(10, 101, 96, 13);
		panel2.add(lbLastName);

		lbTellNum = new JLabel("Tell Number");
		lbTellNum.setBounds(10, 124, 96, 13);
		panel2.add(lbTellNum);

		panel2_1 = new JPanel();
		panel2_1.setBounds(10, 169, 661, 134);
		panel2.add(panel2_1);
		panel2_1.setLayout(null);

		lblNewLabel_9 = new JLabel("Address Line");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(10, 10, 130, 25);
		panel2_1.add(lblNewLabel_9);

		lbstreet = new JLabel("Street");
		lbstreet.setBounds(31, 45, 96, 13);
		panel2_1.add(lbstreet);

		lbcity = new JLabel("City");
		lbcity.setBounds(34, 62, 93, 13);
		panel2_1.add(lbcity);

		lbstate = new JLabel("State");
		lbstate.setBounds(31, 85, 96, 13);
		panel2_1.add(lbstate);

		lbzipcode = new JLabel("Zip Code");
		lbzipcode.setBounds(31, 108, 96, 13);
		panel2_1.add(lbzipcode);

		tfstreet = new JTextField();
		tfstreet.setBounds(146, 42, 96, 19);
		panel2_1.add(tfstreet);
		tfstreet.setColumns(10);

		tfcity = new JTextField();
		tfcity.setText("");
		tfcity.setBounds(146, 62, 96, 19);
		panel2_1.add(tfcity);
		tfcity.setColumns(10);

		tfstate = new JTextField();
		tfstate.setBounds(146, 82, 96, 19);
		panel2_1.add(tfstate);
		tfstate.setColumns(10);

		tfzipcode = new JTextField();
		tfzipcode.setText("");
		tfzipcode.setBounds(146, 105, 96, 19);
		panel2_1.add(tfzipcode);
		tfzipcode.setColumns(10);

		tfmemberid = new JTextField();
		tfmemberid.setBounds(129, 36, 96, 19);
		panel2.add(tfmemberid);
		tfmemberid.setColumns(10);

		tffirstname = new JTextField();
		tffirstname.setBounds(129, 60, 96, 16);
		panel2.add(tffirstname);
		tffirstname.setColumns(10);

		tflastname = new JTextField();
		tflastname.setBounds(129, 98, 96, 19);
		panel2.add(tflastname);
		tflastname.setColumns(10);

		tftellnumber = new JTextField();
		tftellnumber.setBounds(129, 121, 96, 19);
		panel2.add(tftellnumber);
		tftellnumber.setColumns(10);

		btnaddmember = new JButton("Add Member");
		btnaddmember.addActionListener(evt -> {

			String id = tfmemberid.getText().trim();
			String fname = tffirstname.getText().trim();
			String lname = tflastname.getText().trim();
			String tellnumber = tftellnumber.getText().trim();

			String street = tfstreet.getText().trim();
			String city = tfcity.getText().trim();
			String state = tfstate.getText().trim();
			String zipcode = tfzipcode.getText().trim();

			if (id.length() == 0 || fname.length() == 0 || lname.length() == 0 || tellnumber.length() == 0
					|| street.length() == 0 || city.length() == 0 || state.length() == 0 || zipcode.length() == 0) {
				System.out.println("Fields must be nonempty");
				taBottom.setForeground(Color.RED);
				taBottom.setText("Fields must be nonempty");

			} else {
				Address address = new Address(street, city, state, zipcode);
				LibraryMember member = new LibraryMember(id, fname, lname, tellnumber, address);

				dataAccess.saveNewMember(member);
				taBottom.setForeground(Color.GREEN);
				taBottom.setText(" Successefully New Library Member Added. ");

			}

		});
		btnaddmember.setBounds(402, 97, 132, 21);
		panel2.add(btnaddmember);

		btneditmember = new JButton("Edit Member");
		btneditmember.addActionListener(evt -> {

			String id = tfmemberid.getText().trim();
			String fname = tffirstname.getText().trim();
			String lname = tflastname.getText().trim();
			String tellnumber = tftellnumber.getText().trim();

			String street = tfstreet.getText().trim();
			String city = tfcity.getText().trim();
			String state = tfstate.getText().trim();
			String zipcode = tfzipcode.getText().trim();

			if (id.length() == 0 || fname.length() == 0 || lname.length() == 0 || tellnumber.length() == 0
					|| street.length() == 0 || city.length() == 0 || state.length() == 0 || zipcode.length() == 0) {
				System.out.println("Fields must be nonempty");
				taBottom.setForeground(Color.RED);
				taBottom.setText("Fields must be nonempty");

			} else {
				Address address = new Address(street, city, state, zipcode);
				LibraryMember member = new LibraryMember(id, fname, lname, tellnumber, address);

				dataAccess.saveNewMember(member);
				taBottom.setForeground(Color.GREEN);
				taBottom.setText(" Successefully New Library Member Added. ");

			}

		});
		btneditmember.setBounds(402, 138, 132, 21);
		panel2.add(btneditmember);
		updateData();
		cards.add(panel3, liAddBook.getItemName());

		cards.add(panel4, liCheckoutBook.getItemName());

		lbisbn = new JLabel("ISBN");
		lbisbn.setBounds(10, 54, 125, 13);
		panel4.add(lbisbn);

		lbmemeberid = new JLabel("Member ID");
		lbmemeberid.setBounds(10, 91, 125, 13);
		panel4.add(lbmemeberid);

		tfisbn = new JTextField();
		tfisbn.setBounds(145, 51, 96, 19);
		panel4.add(tfisbn);
		tfisbn.setColumns(10);

		tfmemberidcheckout = new JTextField();
		tfmemberidcheckout.setText("");
		tfmemberidcheckout.setBounds(145, 88, 96, 19);
		panel4.add(tfmemberidcheckout);
		tfmemberidcheckout.setColumns(10);

		btncheckoutbook = new JButton("Checkout Book");
		btncheckoutbook.addActionListener(evt -> {

			String isbn = tfisbn.getText().trim();
			String memberid = tfmemberidcheckout.getText().trim();

			if (isbn.length() == 0 || memberid.length() == 0) {
				System.out.println("Fields must be nonempty");
				taBottom.setForeground(Color.RED);
				taBottom.setText("Fields must be nonempty");

			} else {
				LibraryMember member = SearchLibraryMember(memberid);
				Book b = SearchBook(isbn);
				if (member != null) {
					if (b != null) {
						checkout(b.getNextAvailableCopy(), member);
						System.out.println("Check out Success! ");
						taBottom.setForeground(Color.GREEN);
						taBottom.setText("Check out Success! ");

					} else {
						System.out.println("The book is not found! ");
						taBottom.setForeground(Color.RED);
						taBottom.setText("The book is not found! ");
					}

				} else {
					System.out.println("Library member not registered! ");
					taBottom.setForeground(Color.RED);
					taBottom.setText("Library member not registered!");
				}

			}

		});
		btncheckoutbook.setBounds(156, 166, 139, 21);
		panel4.add(btncheckoutbook);

		JLabel lblNewLabel_5 = new JLabel("ISBN");
		lblNewLabel_5.setBounds(27, 53, 110, 13);
		panel3.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Copy Number");
		lblNewLabel_6.setBounds(27, 89, 110, 13);
		panel3.add(lblNewLabel_6);

		tfisbnaddcopy = new JTextField();
		tfisbnaddcopy.setBounds(153, 50, 226, 19);
		panel3.add(tfisbnaddcopy);
		tfisbnaddcopy.setColumns(10);

		tfcopynumber = new JTextField();
		tfcopynumber.setEditable(false);
		tfcopynumber.setBounds(153, 86, 226, 19);
		panel3.add(tfcopynumber);
		tfcopynumber.setColumns(10);

		JButton btnAddcopyBook = new JButton("Add copy book");
		btnAddcopyBook.addActionListener(evt -> {
			String isbn = tfisbnaddcopy.getText().trim();
			Book b = null;

			HashMap<String, Book> books = dataAccess.readBooksMap();
			if (isbn.length() == 0) {
				System.out.println("Fields must be nonempty");
				taBottom.setForeground(Color.RED);
				taBottom.setText("Fields must be nonempty");

			} else {
				for (String bk : books.keySet()) {
					System.out.println("isbn: " + isbn);
					System.out.println("bk: " + bk + "\n");
					if (isbn.equals(bk)) {
						b = books.get(bk);
						b.addCopy();
						dataAccess.addBookCopy(b);

					}
				}

				if (b == null) {
					System.out.println("Book doesn't exist");
					taBottom.setForeground(Color.RED);
					taBottom.setText("Book doesn't exist");
				} else {
					Integer x = b.getNumCopies();
					tfcopynumber.setText(x.toString() + " Copies of " + b.getTitle() + " available.");
				}
			}
		});
		btnAddcopyBook.setBounds(108, 138, 127, 35);
		panel3.add(btnAddcopyBook);

		cards.add(panel5, licheckoutRecord.getItemName());

		btnrefresh = new JButton("Refresh");
		btnrefresh.addActionListener(evt -> {

			populateTable();
		});
		btnrefresh.setBounds(541, 15, 85, 21);
		panel5.add(btnrefresh);

		System.out.println(col);
		System.out.println(r);

		cards.add(panel6, liaddnewBook.getItemName());

		lblNewLabel_7 = new JLabel("ISBN");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(133, 48, 45, 13);
		panel6.add(lblNewLabel_7);

		lblNewLabel_10 = new JLabel("Title");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(133, 83, 45, 13);
		panel6.add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("Max checkout lengnth");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(10, 117, 168, 13);
		panel6.add(lblNewLabel_11);

		lblNewLabel_12 = new JLabel("Authors");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_12.setBounds(93, 156, 85, 13);
		panel6.add(lblNewLabel_12);

		tfisbnnewbook = new JTextField();
		tfisbnnewbook.setBounds(233, 45, 96, 19);
		panel6.add(tfisbnnewbook);
		tfisbnnewbook.setColumns(10);

		tftitlenewbook = new JTextField();
		tftitlenewbook.setBounds(233, 80, 96, 19);
		panel6.add(tftitlenewbook);
		tftitlenewbook.setColumns(10);

		tfcheckoutlength = new JTextField();
		tfcheckoutlength.setBounds(233, 114, 96, 19);
		panel6.add(tfcheckoutlength);
		tfcheckoutlength.setColumns(10);

		tfauthors = new JTextField();
		tfauthors.setBounds(233, 153, 96, 19);
		panel6.add(tfauthors);
		tfauthors.setColumns(10);

		JButton btnAddnewBook = new JButton("Add book");
		btnAddnewBook.addActionListener(evt -> {
			Author authors;
			String isbn = tfisbnnewbook.getText().trim();
			String title = tftitlenewbook.getText().trim();
			String checkoutLength = tfcheckoutlength.getText().trim();
			String firstName = tfauthors.getText().trim();
			String lastName = tfauthors.getText().trim();

			if (isbn.length() == 0 || title.length() == 0 || checkoutLength.length() == 0 || firstName.length() == 0) {
				System.out.println("all fields must be nonempty");
				taBottom.setText("all fields must be nonempty");
			} else {
				authors = new Author(firstName, lastName, "Title",
						new Address("101 S. Main", "Fairfield", "IA", "52556"), "Bio");
				List<Author> auth = new ArrayList<>();
				auth.add(authors);
				Book book = new Book(isbn, title, Integer.parseInt(checkoutLength), auth);
				dataAccess.saveNewBook(book);
				System.out.println("New Book added Successfully!");
				taBottom.setForeground(Color.GREEN);
				taBottom.setText("New Book added Successfully!");
			}
		});
		btnAddnewBook.setBounds(144, 215, 141, 21);
		panel6.add(btnAddnewBook);
		cards.add(panel7, limemberCheckoutRecord.getItemName());

		tfmemberid7 = new JTextField();
		tfmemberid7.setBounds(450, 18, 96, 19);
		panel7.add(tfmemberid7);
		tfmemberid7.setColumns(10);

		btnsearch7 = new JButton("Search");
		btnsearch7.addActionListener(evt -> {

			scrollPane22 = new JScrollPane();
			String[][] rows7 = prepareRowsBook(tfmemberid7.getText());
			if (rows7 != null) {
				JTable table7 = new JTable(rows7, new String[] { "Checkout Date", "Due Date", "Book Title" });

				table.setBounds(26, 46, 600, 250);
				scrollPane22.setViewportView(table7);
				scrollPane22.setBounds(26, 46, 600, 250);
				panel7.add(scrollPane22);
				panel7.setVisible(true);
			} else
				taBottom.setText("No checkout records found");
		});

		btnsearch7.setBounds(556, 17, 85, 21);
		panel7.add(btnsearch7);

		lbmemberid7 = new JLabel("Member ID");
		lbmemberid7.setBounds(369, 21, 78, 13);
		panel7.add(lbmemberid7);
		cards.add(panel8, liavailablebooks.getItemName());

		// connect JList elements to CardLayout panels
		linkList.addListSelectionListener(event -> {
			String value = linkList.getSelectedValue().toString();
			CardLayout cl = (CardLayout) (cards.getLayout());
			cl.show(cards, value);
		});

	}

	private String[][] prepareRowsForBooks() {
		// TODO Auto-generated method stub
		HashMap<String, Book> book = new HashMap<String, Book>();
		book = dataAccess.readBooksMap();
		String[][] rowsss = new String[book.size()][3];
		int countBooks = 0;
		for (Book books : book.values()) {
			// System.out.println(countBooks +": "+books.toString());

			Integer temp;
			rowsss[countBooks][0] = books.getIsbn();
			temp = books.getMaxCheckoutLength();
			rowsss[countBooks][1] = books.getTitle();
			rowsss[countBooks][2] = temp.toString();
			// rowsss[countBooks][3] = books.getAuthors().get(1).getFirstName()+"
			// "+books.getAuthors().get(1).getLastName();
			// rowsss[countBooks][3] = ;

			countBooks++;
		}

//		HashMap<String, CheckoutRecordEntry> ce = new HashMap<>();
//		ce = dataAccess.readRecordMap();
//		String[][] rowsss = new String[ce.size()][5];
//		int count = 0;
//		for (CheckoutRecordEntry entry : ce.values()) {
//			System.out.println(entry.toString());
//
//			Integer temp;
//			rowsss[count][0] = entry.getCheckoutDate();
//			temp = entry.getDuedate();
//			rowsss[count][1] = temp.toString();
//			rowsss[count][2] = entry.getBookcopies().getBook().getIsbn();
//			rowsss[count][3] = entry.getBookcopies().getBook().getTitle();
//			temp = entry.getBookcopies().getCopyNum();
//			rowsss[count][4] = temp.toString();
//
//			// data.add(rows);
//			System.out.println(Arrays.toString(rowsss[count]));
//			// data.set(count, rows);
//			// data.add(rowsss);
//			count++;
//		}

		return rowsss;
	}

	private void updateLeftPanel(Auth auth) {
		if (auth == Auth.LIBRARIAN)
			updateList(librarianItem);
		else if (auth == Auth.ADMIN)
			updateList(adminItem);
		else if (auth == Auth.BOTH)
			updateList(null);

	}

	private void updateList(ListItem[] items) {
		// JList<ListItem> linkList = linkList;
		DefaultListModel<ListItem> model = (DefaultListModel) linkList.getModel();
		int size = model.getSize();
		if (items != null) {
			java.util.List<Integer> indices = new ArrayList<>();
			for (ListItem item : items) {
				int index = model.indexOf(item);
				indices.add(index);
				ListItem next = (ListItem) model.get(index);
				next.setHighlight(true);

			}
			for (int i = 0; i < size; ++i) {
				if (!indices.contains(i)) {
					ListItem next = (ListItem) model.get(i);
					next.setHighlight(false);
				}
			}
		} else {
			for (int i = 0; i < size; ++i) {
				ListItem next = (ListItem) model.get(i);
				next.setHighlight(true);
			}

		}
	}

	public void createLinkLabels() {
		DefaultListModel<ListItem> model = new DefaultListModel<>();

		model.addElement(liLogin);
		model.addElement(liViewTitles);
		model.addElement(liAddBook);
		model.addElement(liCheckoutBook);

		model.addElement(licheckoutRecord);
		model.addElement(liaddnewBook);
		model.addElement(limemberCheckoutRecord);
		model.addElement(liavailablebooks);

		linkList = new JList<ListItem>(model);
		linkList.setCellRenderer(new DefaultListCellRenderer() {

			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof ListItem) {
					ListItem nextItem = (ListItem) value;
					setText(nextItem.getItemName());
					if (nextItem.highlight()) {
						setForeground(Util.LINK_AVAILABLE);
					} else {
						setForeground(Util.LINK_NOT_AVAILABLE);
					}
					if (isSelected) {
						setForeground(Color.BLACK);
						setBackground(new Color(236, 243, 245));

					}
				}

				return c;
			}

		});
	}

	public JList<ListItem> getLinkList() {
		return linkList;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public JPanel getPanel7() {
		return panel7;
	}

	public JPanel getPanel8() {
		return panel8;
	}

	public void updateData() {
		List<String> titles = Data.bookTitles;
		Collections.sort(titles);
		StringBuilder sb = new StringBuilder();
		for (String s : titles) {
			sb.append(s + "\n");
		}

	}

	private void initializeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Group Three Library System");
		centerFrameOnDesktop(this);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = SCREEN_WIDTH;
		int frameWidth = SCREEN_HEIGHT;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 2);
	}

	public LibraryMember SearchLibraryMember(String id) {
		LibraryMember m = null;
		HashMap<String, LibraryMember> members = dataAccess.readMemberMap();

		for (String mk : members.keySet()) {
			if (id.equals(mk)) {
				m = members.get(mk);
			}
		}
		return m;
	}

	public Book SearchBook(String isbn) {
		Book b = null;
		HashMap<String, Book> books = dataAccess.readBooksMap();

		for (String bk : books.keySet()) {
			if (isbn.equals(bk)) {
				b = books.get(bk);
			}
		}

		return b;
	}

	public void checkout(BookCopy copy, LibraryMember member) {
		Integer checkoutLength = copy.getBook().getMaxCheckoutLength();
		CheckoutRecordEntry entry = new CheckoutRecordEntry(LocalDateTime.now().toString(), checkoutLength, copy);
		// add the checkout info to record entry and record file
		dataAccess.saveNewRecordEntry(entry);

		List<CheckoutRecordEntry> entryList = new ArrayList<>();
		entryList.add(entry);
		// CheckoutRecord cr = new CheckoutRecord(entryList);
		// CheckoutRecord cr = member.getCheckoutRecord();
		List<CheckoutRecordEntry> entryList2 = member.getCheckoutRecord().getEntryRecords();
		entryList.addAll(entryList2);
		// add the checkout info to the Library member and member file
		member.getCheckoutRecord().setEntryRecords(entryList);
		// member.setCheckoutRecord(member.checkoutRecord);
		dataAccess.saveNewMember(member);

		BookCopy[] bk = copy.getBook().getCopies();

		// add the checkout info to book and Book file
		for (int i = 0; i < bk.length; i++) {
			if (bk[i].getCopyNum() == copy.getCopyNum())
				copy.getBook().changeBookCopyAvailablility(i);
			dataAccess.addBookCopy(copy.getBook());
		}

	}

	private String[][] prepareRows() { // List<String[]>
		// String[] rowsss = new String[5];
		// List<String[]> data = new ArrayList<>();
		HashMap<String, CheckoutRecordEntry> ce = new HashMap<>();
		ce = dataAccess.readRecordMap();
		String[][] rowsss = new String[ce.size()][5];
		int count = 0;
		for (CheckoutRecordEntry entry : ce.values()) {
			System.out.println(entry.toString());

			Integer temp;
			rowsss[count][0] = entry.getCheckoutDate();
			temp = entry.getDuedate();
			rowsss[count][1] = temp.toString();
			rowsss[count][2] = entry.getBookcopies().getBook().getIsbn();
			rowsss[count][3] = entry.getBookcopies().getBook().getTitle();
			temp = entry.getBookcopies().getCopyNum();
			rowsss[count][4] = temp.toString();

			// data.add(rows);
			System.out.println(Arrays.toString(rowsss[count]));
			// data.set(count, rows);
			// data.add(rowsss);
			count++;
		}

		// model.setTableValues(data);
		return rowsss;

	}

	public String[][] prepareRowsBook(String memberid) { // List<String[]>
		// String[] rowsss = new String[5];
		// List<String[]> data = new ArrayList<>();
		String[][] rowsss = null;
		HashMap<String, LibraryMember> members = new HashMap<>();
		members = dataAccess.readMemberMap();
		LibraryMember amemeber = members.get(memberid);

		if (amemeber != null) {
			rowsss = new String[amemeber.getCheckoutRecord().getEntryRecords().size()][3];
			int count = 0;
			Integer temp;
			List<CheckoutRecordEntry> rdentries = amemeber.getCheckoutRecord().getEntryRecords();
			if (rdentries == null)
				return null;
			for (CheckoutRecordEntry checkentry : rdentries) {
				System.out.println(checkentry.toString());
				rowsss[count][0] = checkentry.getCheckoutDate();
				temp = checkentry.getDuedate();
				rowsss[count][1] = temp.toString();
				rowsss[count][2] = checkentry.getBookcopies().getBook().getTitle();
				count++;
			}

		}
		return rowsss;

	}

	public void updateModel(List<String[]> list) {
		if (model == null) {
			model = new CustomTableModel();
		}
		model.setTableValues(list);
	}

	private void populateTable() {
		scrollPane2 = new JScrollPane();
		String[][] rows = prepareRows();
		// Object[][] rows=(Object[][])listRow.toArray();
		JTable table = new JTable(rows, col);

		table.setBounds(26, 46, 600, 250);
		scrollPane2.setViewportView(table);
		scrollPane2.setBounds(26, 46, 600, 250);
		panel5.add(scrollPane2);
		panel5.setVisible(true);
	}
}
