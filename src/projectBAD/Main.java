package projectBAD;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;




public class Main extends JFrame implements ActionListener{
	JMenuBar bar;
	JMenu menu;
	JMenuItem login, register;
	JDesktopPane dp;
	Connect con = new Connect();
	
	JLabel label;
	
	boolean login1 = false;

	String cekusername;
	String cekpassword;
	String cekrolename;
	
	Vector<Vector>data;
	Vector detail, header;
	
	public Main() {
		
		init();
		setSize(1000,700);
		setLocationRelativeTo(null);
		setTitle("La Torta Shop");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void init(){
		ImageIcon background = new ImageIcon("img\\home.jpg");
		Image img = background.getImage();
		Image temp = img.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
		background = new ImageIcon (temp);
		JLabel back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 1920, 1080);
		
		bar = new JMenuBar();
		menu = new JMenu("Menu");
		login = new JMenuItem("Login");
		register = new JMenuItem("Register");
		dp = new JDesktopPane();
		label = new JLabel("");
		
		bar.add(menu);
		menu.add(login);
		menu.add(register);
		setJMenuBar(bar);
		setContentPane(dp);
		add(back);

		
		
		login.addActionListener(this);
		register.addActionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	  
	}	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login){
			LoginForm loginmenu = new LoginForm();
			add(loginmenu);
			Dimension dpSize = dp.getSize();
			Dimension loginmenuSize = loginmenu.getSize();
			loginmenu.setLocation((dp.getWidth() - loginmenu.getWidth())/2, (dp.getHeight()- loginmenu.getHeight())/2);
			try {
				loginmenu.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==register){
			Registration rf = new Registration();
			add(rf);
			Dimension dpSize = dp.getSize();
			Dimension rfSize = rf.getSize();
			rf.setLocation((dp.getWidth() - rf.getWidth())/2, (dp.getHeight()- rf.getHeight())/2);
			try {
				rf.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	class Registration extends JInternalFrame implements ActionListener{
		
		JLabel title, email, password, phone, dateOfBirth, gender, address;
		JTextField emailText, phoneNumber;
		JTextArea addressText;
		JPasswordField passwordField;
		JRadioButton genderRadioButtonMale, genderRadioButtonFemale;
		JButton registerButton;
		JPanel panel1, panel2, panel3, panel4, panel5;
		ButtonGroup buttonGroup;
		Connect con = new Connect();
		SpinnerDateModel Date = new SpinnerDateModel();
		JSpinner DOB = new JSpinner(Date);
		
		
		
		
		public Registration() {
			// TODO Auto-generated constructor stub
			init();
			setSize(400,600);
			setTitle("Registration");
			setResizable(false);
			setClosable(true);
			setVisible(true);
		
			
			add(panel1);
			panel1.setLayout(new BorderLayout());
			panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel2.add(title);
			panel1.add(panel2, BorderLayout.NORTH);
			
			panel3.setLayout(new GridLayout(6,2));
			panel1.add(panel3, BorderLayout.CENTER);
			panel3.add(email);
			panel3.add(emailText);
			panel3.add(password);
			panel3.add(passwordField);
			panel3.add(phone);
			panel3.add(phoneNumber);
			panel3.add(dateOfBirth);
			panel3.add(DOB);
			panel3.add(gender);
			
			panel4.setLayout(new GridLayout(1,2));
			panel3.add(panel4);
			buttonGroup.add(genderRadioButtonMale);
			buttonGroup.add(genderRadioButtonFemale);
			panel4.add(genderRadioButtonMale);
			panel4.add(genderRadioButtonFemale);
			
			panel3.add(address);
			panel3.add(addressText);
			
			panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel5.add(registerButton);
			panel1.add(panel5, BorderLayout.SOUTH);
			
			registerButton.addActionListener(this);
		}
		
		 public String getSelectedGender() {
		        Enumeration<AbstractButton> radioButtons = buttonGroup.getElements();
		        while (radioButtons.hasMoreElements()) {
		            AbstractButton currentRadioButton = radioButtons.nextElement();
		            if (currentRadioButton.isSelected()) {
		                return currentRadioButton.getText();
		            }
		        }
		        return null;
		    }
		
		public void init(){

			title = new JLabel("Register");
			email = new JLabel("Email");
			password = new JLabel("Password");
			phone = new JLabel("Phone");
			dateOfBirth = new JLabel("Date of Birth");
			gender = new JLabel("Gender");
			address = new JLabel("Address");
			
			emailText = new JTextField();
			phoneNumber = new JTextField();
			
			addressText = new JTextArea();
			
			passwordField = new JPasswordField();
			
			DOB.setEditor(new JSpinner.DateEditor(DOB,"dd-MMM-yyyy"));
			
			
			genderRadioButtonMale = new JRadioButton("Male");
			genderRadioButtonFemale = new JRadioButton("Female");
			
			buttonGroup = new ButtonGroup();
			
			registerButton = new JButton("Register");
			
			panel1 = new JPanel();
			panel2 = new JPanel();
			panel3 = new JPanel();
			panel4 = new JPanel();
			panel5 = new JPanel();
			
		}

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == registerButton){
				
				SimpleDateFormat SDF = new SimpleDateFormat("dd-MMM-yyyy");
				String FormattedDate = SDF.format(DOB.getValue());
				
				String email = emailText.getText();
				
				String password = passwordField.getText();
				
				String phoneField = phoneNumber.getText();
				
				String cekGender = getSelectedGender();
				
				String roleName = "Member";
				String Genders = null;
				
				String address = addressText.getText();
				
				int count = email.length() - email.replace("@", "").length();
				
				if(genderRadioButtonMale.isSelected()){
					Genders = "Male";
				}else if(genderRadioButtonFemale.isSelected()){
					Genders = "Female";
				}
			
					if(email.isEmpty()){
						JOptionPane.showMessageDialog(null, "Email must not be empty");
					}else if((!email.contains("@") || !email.contains("."))){
						JOptionPane.showMessageDialog(null, "Invalid email format");
					}else if(email.startsWith("@") || email.startsWith(".")){
						JOptionPane.showMessageDialog(null, "Invalid email format");
					}else if(email.endsWith("@") || email.endsWith(".")){
						JOptionPane.showMessageDialog(null, "Invalid email format");
					}else if (count>1){
						JOptionPane.showMessageDialog(null, "Email can only have 1 '@'");
					}else if(password.isEmpty()){
						JOptionPane.showMessageDialog(null, "Password must not be empty");
					}else if(password.length()<6 || password.length()>12){
						JOptionPane.showMessageDialog(null, "Password must be between 6 and 12 characters");
					}else if(password.matches("[a-zA-Z0-9]+")) {
						JOptionPane.showMessageDialog(null, "Password must be alphanumeric");
					}else if(phoneField.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Phone must not be empty");
					}else if(address.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Address must not be empty");
					}else if(!address.endsWith("Street")||!address.endsWith("street")) {
						JOptionPane.showMessageDialog(null, "Address must not be empty");
					}else if(phoneField.length()!=11 || phoneField.length()!=12) {
						JOptionPane.showMessageDialog(null, "Phone must be exactly 11 or 12");
					}else if(cekGender == null){
						JOptionPane.showMessageDialog(null, "Gender must be selected");
						return;
						
					}else{
														
								String newEmail = emailText.getText();
								String newPassword = passwordField.getText();
								String newPhone = phoneNumber.getText();
								String newAddress = addressText.getText();
								
								String query = "INSERT INTO member VALUES(NULL,'" + 
								newEmail + "','" + newPassword+ "','" + newPhone+ "','" + FormattedDate+"','"+Genders+"','"+newAddress+"','"+roleName+"')";
								con.executeUpdate(query);
						
						
					JOptionPane.showMessageDialog(null, "Data Added Successfully");
					
				}
					
			}
		}

	}
	
	void ngecekLogin(ResultSet rs){
		try{
			while(rs.next()){
				cekusername = rs.getString("Email");
				cekpassword = rs.getString("Password");
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void ngecekRolename(ResultSet rs){
		try{
			while(rs.next()){
				cekrolename = rs.getString("RoleName");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	class LoginForm extends JInternalFrame implements ActionListener{
		JLabel title, email, password;
		JTextField inputEmail;
		JPasswordField passwordField;
		JButton loginButton;
		JCheckBox agreement;
		boolean checkEmail;
		
		
		public LoginForm() {
			
			initialize();
			setSize(700,250);
			setTitle("Login");
			setResizable(false);
			setClosable(true);
			setVisible(true);
			
		}
		
		public void initialize(){	
			title = new JLabel("Login");
			email = new JLabel("Email : ");
			password = new JLabel("Password : ");
			agreement = new JCheckBox("I agree with terms and conditions");
			loginButton = new JButton("Login");
			inputEmail = new JTextField ();
			passwordField = new JPasswordField();
			email.setFont(new Font(email.getName(),Font.PLAIN,14));
			password.setFont(new Font(password.getName(), Font.PLAIN,14));
			title.setFont(new Font(title.getName(), Font.BOLD,30));
			
		
			email.setSize(200,100);
			password.setSize(200,100);
			loginButton.setSize(400,50);
			
			title.setBounds(300,-10,80,80);
			email.setBounds(0,20,100,100);
			inputEmail.setBounds(300, 60, 360,30);
			password.setBounds(0,80, 100,50);
			passwordField.setBounds(300,90,360,30);
			loginButton.setBounds(300,170,100,30);
			agreement.setBounds(450, 100,300,70);
			
			add(title);
			add(email);
			add(inputEmail);
			add(password);
			add(passwordField);
			add(agreement);
			add(loginButton);
			setLayout(null);		
			
			loginButton.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==loginButton){
				String queryLogin = ("SELECT Email,Password FROM member");
				ngecekLogin(con.executeQuery(queryLogin));
				String queryRolename = ("SELECT RoleName from member");
				ngecekRolename(con.executeQuery(queryRolename));
				
				String email = inputEmail.getText();
				String password1 = passwordField.getText();
				
				int count = email.length() - email.replace("@", "").length();
				
				if(email.isEmpty()){
					JOptionPane.showMessageDialog(null, "Email must not be empty");	
				}else if((!email.contains("@") || !email.contains("."))){
					JOptionPane.showMessageDialog(null, "Email must be in correct format");
				}else if(email.startsWith("@") || email.startsWith(".")){
					JOptionPane.showMessageDialog(null, "Email must be in correct format");
				}else if(email.endsWith("@") || email.endsWith(".")){
					JOptionPane.showMessageDialog(null, "Email must be in correct format");
				}else if (count>1){
					JOptionPane.showMessageDialog(null, "Email can only have 1 '@'");
				}else if(password1.isEmpty()){
					JOptionPane.showMessageDialog(null, "Password must not be empty");
				}else if(password1.length()<6 || password1.length()>12){
					JOptionPane.showMessageDialog(null, "Password must be between 6 and 12 characters");
				}else if(password1.matches("[a-zA-Z0-9]+")) {
					JOptionPane.showMessageDialog(null, "Password must be alphanumeric");
				}else if(!agreement.isSelected()){
					JOptionPane.showMessageDialog(null, "Please agree to the terms and conditions");
					return;
				}else if(cekusername.equals(email) && cekpassword.equals(password1) && cekrolename.equalsIgnoreCase("Admin")) {
					login1 = true;
					JOptionPane.showMessageDialog(null, "You are logged in");
					dispose();
					HomeAdmin ha = new HomeAdmin();
					ha.setVisible(true);
				}else if(cekusername.equals(email) && cekpassword.equals(password1) && cekrolename.equalsIgnoreCase("Member")) {
					login1 = true;
					JOptionPane.showMessageDialog(null, "You are logged in");
					dispose();
					Member m = new Member();
					m.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Incorrect username / password");
				}	
			}
		}
		
		 class Member extends JFrame implements ActionListener {

				JMenuBar menubar;
				JMenu transactions, others;
				JMenuItem buyitem,viewtrans,logout;
				JDesktopPane dp ;
				
				public Member() {
					init();
					setSize(1200,800);
					setTitle("Welcome, Member!");
					setExtendedState(MAXIMIZED_BOTH);
					setLocationRelativeTo(null);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					setVisible(true);
					
				}
				
				public void init() {
					
					ImageIcon background = new ImageIcon("img\\member.jpg");
					Image img = background.getImage();
					Image temp = img.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
					background = new ImageIcon (temp);
					JLabel back = new JLabel(background);
					back.setLayout(null);
					back.setBounds(0, 0, 1920, 1080);
					
					menubar = new JMenuBar();
					transactions = new JMenu("Transactions");
					others = new JMenu("Others");
					buyitem = new JMenuItem("Buy Item");
					viewtrans = new JMenuItem("View Transactions");
					logout = new JMenuItem("Logout");
					dp = new JDesktopPane();
					add(back);
					
					
					menubar.add(transactions);
					menubar.add(others);
					
					transactions.add(buyitem);
					transactions.add(viewtrans);
					others.add(logout);
					setJMenuBar(menubar);
					setContentPane(dp);
					
					logout.addActionListener(this);
					buyitem.addActionListener(this);
					viewtrans.addActionListener(this);
				}
				


				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == buyitem) {
						BuyItemForm bif = new BuyItemForm();
						add(bif);
					}else if(e.getSource() == viewtrans){
						ViewTransactionForm vtf = new ViewTransactionForm();
						add(vtf);
					}else if(e.getSource()==logout){
						int pilih = JOptionPane.showConfirmDialog(null,	"Do you want to logout?");
						if (pilih==JOptionPane.YES_OPTION){
						dispose();
						}
					}
						
					
				}

			}
		 
		class ViewTransactionForm extends JInternalFrame{
				
				JPanel tablePanel, paneltitle;
				JTable transTable;
				JLabel title;
				JScrollPane sp;
				Vector <Vector> data;
				Vector header,detail;

				public ViewTransactionForm() {
					
					init();
					setTitle("My Transaction");
					setLocation(150,150);
					setSize(900,400);	
					setClosable(true);
					setVisible(true);
				}

				private void init() {
					title = new JLabel("My Transaction");
					title.setFont(new Font(title.getName(), Font.BOLD, 20));
					
					transTable = new JTable();
					sp = new JScrollPane(transTable);
					
					tablePanel = new JPanel();
					paneltitle = new JPanel();
					add(tablePanel);
					tablePanel.setLayout(new BorderLayout());
					paneltitle.setLayout(new FlowLayout(FlowLayout.CENTER));
					paneltitle.add(title);
					tablePanel.add(paneltitle, BorderLayout.NORTH);
					tablePanel.add(sp, BorderLayout.CENTER);
					
					loadViewTransaction(con.executeQuery("SELECT c.cakeName,b.brandName, t.TransactionDate, dt.Quantity, c.Price FROM transaction t JOIN detailtransaction dt ON t.TransactionID = dt.TransactionID JOIN cake c ON c.cakeID = dt.cakeId JOIN brand b ON b.brandID = c.brandID"));
					
					
				}

				private void loadViewTransaction(ResultSet rs) {
					header = new Vector<>();
					
					header.add("Cake Name");
					header.add("Brand");
					header.add("TransactionDate");
					header.add("Quantity");
					header.add("Price");
					header.add("Total Transaction");
					
					data = new Vector<>();
						
				try{
					
					while(rs.next()){
						String cakeName = rs.getString("CakeName");
						String BrandName = rs.getString("BrandName");
						Date transDate = rs.getDate("TransactionDate");
						int quantity = rs.getInt("Quantity");
						int price = rs.getInt("Price");
						int totalTransaction = (quantity*price);
						
						detail = new Vector<>();
						detail.add(cakeName);
						detail.add(BrandName);
						detail.add(transDate);
						detail.add(quantity);
						detail.add(price);
						detail.add(totalTransaction);
						
						data.add(detail);
						}
					} catch (SQLException e){
						e.printStackTrace();
					}
					
					DefaultTableModel dtm = new DefaultTableModel(data,header){
					public boolean isCellEditable(int row, int collumn){
						return false;
					}
				};
					transTable.setModel(dtm);
				}
				
			}
		 
		 class BuyItemForm extends JInternalFrame implements 	ActionListener, MouseListener, KeyListener{
				
				JPanel top, mid, bottom, tablePanel, fieldPanel;
				JTable buyTable;
				JScrollPane sp;
				JLabel CakeID, brand, cakeName, stock, price, quantity, title;
				JTextField cakeidField, brandField, cakenameField, stockField, priceField;
				JSpinner quantitySpinner;
				JButton buy, cancel;
				Vector <Vector> data;
				Vector header, detail;
				int Stock;
				String memberID, cakeID, transactionID, transactionID2;
				
				public BuyItemForm() {
					init();
					setTitle("Buy Form");
					setLocation(150,150);
					setSize(900,400);	
					setClosable(true);
					setResizable(true);
					setVisible(true);
				
				}
				public void init(){
					buyTable = new JTable();
					sp = new JScrollPane(buyTable);
					
				
					top = new JPanel();
					mid = new JPanel();
					bottom = new JPanel();
					tablePanel = new JPanel();
					fieldPanel = new JPanel();
				
					title = new JLabel("Buy Product");
					title.setFont(new Font(title.getName(), Font.BOLD, 20));
					CakeID = new JLabel("Product ID");
					brand = new JLabel("Brand");
					cakeName = new JLabel("Product Name");
					stock = new JLabel("Stock");
					price = new JLabel("Price");
					quantity = new JLabel("Quantity");
					quantitySpinner = new JSpinner();
					priceField = new JTextField();
					cakeidField = new JTextField();
					brandField = new JTextField();
					cakenameField = new JTextField();
					stockField = new JTextField();
					buy = new JButton("Buy");
					cancel = new JButton("Cancel");
					load(con.executeQuery("SELECT c.CakeId, c.cakeName,b.brandName, Stock,Price FROM cake c JOIN brand b ON c.BrandID = b.BrandID "));
					
					top.add(title);
					
					tablePanel.setLayout(new GridLayout(1,1));
					tablePanel.add(sp);
				
					fieldPanel.setLayout(new GridLayout(3,4));
					fieldPanel.add(CakeID);
					fieldPanel.add(cakeidField);
					fieldPanel.add(brand);
					fieldPanel.add(brandField);
					fieldPanel.add(cakeName);
					fieldPanel.add(cakenameField);
					fieldPanel.add(stock);
					fieldPanel.add(stockField);
					fieldPanel.add(price);
					fieldPanel.add(priceField);
					fieldPanel.add(quantity);
					fieldPanel.add(quantitySpinner);
					
					mid.setLayout(new GridLayout(2,1));
					
					mid.add(tablePanel);
					mid.add(fieldPanel);
					
					bottom.add(buy);
					bottom.add(cancel);
					
					
					add(top, (new BorderLayout().NORTH));
					add(mid, (new BorderLayout().CENTER));
					add(bottom, (new BorderLayout().SOUTH));
					
					
					
					cakeidField.setEnabled(false);
					brandField.setEnabled(false);
					cakenameField.setEnabled(false);
					stockField.setEnabled(false);
					priceField.setEnabled(false);
					cancel.setEnabled(false);
					
					buyTable.addMouseListener(this);
					quantitySpinner.addKeyListener(this);
					buy.addActionListener(this);
					cancel.addActionListener(this);
					
				}

				private void load(ResultSet rs) {
					// TODO Auto-generated method stub
					header = new Vector<>();
					
					header.add("Product ID");
					header.add("Product Name");
					header.add("Brand");
					header.add("Stock");
					header.add("Price");
					
					data = new Vector<>();
						
				try{
					
					while(rs.next()){
						int CakeID = rs.getInt("CakeId");
						String cakeName = rs.getString("CakeName");
						String BrandName = rs.getString("BrandName");
						int stock = rs.getInt("Stock");
						int price = rs.getInt("Price");
						
						detail = new Vector<>();
						detail.add(CakeID);
						detail.add(cakeName);
						detail.add(BrandName);
						detail.add(stock);
						detail.add(price);
						
						data.add(detail);
						}
					} catch (SQLException e){
						e.printStackTrace();
					}
					
					DefaultTableModel dtm = new DefaultTableModel(data,header){
					public boolean isCellEditable(int row, int collumn){
						return false;
					}
				};
					buyTable.setModel(dtm);
				}
					
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getSource() == buyTable){
						
						int indexData = buyTable.getSelectedRow();
						TableModel buytableModel = buyTable.getModel();
						
						String productidVal = buytableModel.getValueAt(indexData, 0).toString();
						String cakebrandVal = buytableModel.getValueAt(indexData, 1).toString();
						String cakenameVal = buytableModel.getValueAt(indexData, 2).toString();
						String cakestockVal = buytableModel.getValueAt(indexData, 3).toString();
						String cakequantityVal = buytableModel.getValueAt(indexData, 4).toString();
						
						cakeidField.setText(productidVal);
						brandField.setText(cakenameVal);
						cakenameField.setText(cakebrandVal);
						stockField.setText(cakestockVal);
						priceField.setText(cakequantityVal);
						
						cancel.setEnabled(true);
						
					}
					
				}
				
				public void checkStockQuantity(ResultSet rs){
					try {
						while(rs.next()){
						Stock = Integer.parseInt(rs.getString("Stock"));
						}
					} catch (NumberFormatException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
				
				void loadIDMember(ResultSet rs){
					try {
						while(rs.next()){
							memberID = rs.getString("MemberId");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				void loadCakeID(ResultSet rs){
					try {
						while(rs.next()){
							cakeID = rs.getString("CakeId");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				void loadTransactionID1(ResultSet rs){
					try {
						while(rs.next()){
							transactionID = rs.getString("TransactionID");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				void loadTransactionID2(ResultSet rs){
					try {
						while(rs.next()){
							transactionID2 = rs.getString("TransactionID");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					if(e.getSource() == buy){
						
						String transDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
						int quantity = Integer.valueOf(quantitySpinner.getValue().toString());
						
						checkStockQuantity(con.executeQuery("SELECT Stock FROM Cake"));
						
						if(cakenameField.getText().isEmpty()){
							JOptionPane.showMessageDialog(getContentPane(),"You must select a cake first!", "Error!",JOptionPane.ERROR_MESSAGE);
						}else if(quantity < 0 || quantity == 0){
							JOptionPane.showMessageDialog(getContentPane(),"Quantity must be more than 0", "Error!",JOptionPane.ERROR_MESSAGE);
						}else if(quantity > Stock){
							JOptionPane.showMessageDialog(getContentPane(),"Quantity must be lessthan " + Stock, "Error!", JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(getContentPane(), "Purchase Successfull!","Message", JOptionPane.PLAIN_MESSAGE);
							
							String ambilMemberID = "SELECT MemberId From member";
							loadIDMember(con.executeQuery(ambilMemberID));
							String ambilCakeID = "SELECT CakeId FROM cake";
							loadCakeID(con.executeQuery(ambilCakeID));
							String ambilTransID = "SELECT TransactionID from transaction";
							loadTransactionID1(con.executeQuery(ambilTransID));
							String ambilTransID2 = "SELECT TransactionID from detailtransaction";
							loadTransactionID2(con.executeQuery(ambilTransID2));
							String insertTransaction = "INSERT INTO transaction VALUES(NULL,'" + memberID + "','" +transDate+ "')";
							con.executeUpdate(insertTransaction);
							String insertDetailTransaction = "INSERT INTO detailtransaction VALUES('" + transactionID + "','" + cakeID + "','" +quantity+"')";
							con.executeUpdate(insertDetailTransaction);
							
							int newstock = (Stock-quantity); 
							String updateStock = "UPDATE cake SET Stock = "+newstock+" WHERE CakeId = "+cakeID;
							con.executeUpdate(updateStock);
							
							dispose();
						}
						
					
						load(con.executeQuery("SELECT c.CakeId, c.cakeName,b.brandName, Stock,Price FROM cake c JOIN brand b ON c.BrandID = b.BrandID"));
						
					}
					
					if(e.getSource() == cancel){
						cakeidField.setText("");
						brandField.setText("");
						cakenameField.setText("");
						stockField.setText("");
						priceField.setText("");
						quantitySpinner.setValue(0);
						
					}
					
				}//ActionListener
				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void keyTyped(KeyEvent e) {
					
					JFormattedTextField tf = ((JSpinner.NumberEditor) quantitySpinner.getEditor()).getTextField();
					 if (e.getSource() == quantitySpinner){
							if(!tf.getText().matches("[0-9]*")){
								JOptionPane.showMessageDialog(getContentPane(),"Quantity can only be numeric!", "Error!",JOptionPane.ERROR_MESSAGE);
							}
					 	}
				}
					
					
				
			}

	
	
	
	class HomeAdmin extends JFrame implements ActionListener{
		JMenuBar bar;
		JMenu manages, others;
		JMenuItem members, cakes, transaction, logout;
		JDesktopPane dp;
		JLabel label;
		
		public HomeAdmin() {
			
			init();
			setSize(1000,700);
			setLocationRelativeTo(null);
			setTitle("Welcome, Admin");
			setExtendedState(MAXIMIZED_BOTH);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
		}
		
		public void init(){
			ImageIcon background = new ImageIcon("img\\admin.jpg");
			Image img = background.getImage();
			Image temp = img.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
			background = new ImageIcon (temp);
			JLabel back = new JLabel(background);
			back.setLayout(null);
			back.setBounds(0, 0, 1920, 1080);
			
			bar = new JMenuBar();
			dp = new JDesktopPane();
			label = new JLabel("");
			
			manages = new JMenu("Manages");
			others = new JMenu("Others");
			
			members = new JMenuItem("Members");
			cakes = new JMenuItem("Cakes");
			transaction = new JMenuItem("Transactions");
			logout = new JMenuItem("Logout");
			
			bar.add(manages);
			bar.add(others);
			manages.add(members);
			manages.add(cakes);
			manages.add(transaction);
			others.add(logout);
			setJMenuBar(bar);
			setContentPane(dp);
			add(back);
			
			members.addActionListener(this);
			cakes.addActionListener(this);
			transaction.addActionListener(this);
			logout.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==members){
				manageMember manageM = new manageMember();
				add(manageM);
				try {
					manageM.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==cakes){
				manageCake manageC = new manageCake();
				add(manageC);
				try {
					manageC.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==transaction){
				manageTransaction manageT = new manageTransaction();
				add(manageT);
				try {
					manageT.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource()==logout){
				int pilih = JOptionPane.showConfirmDialog(null,	"Do you want to logout?");
				if (pilih==JOptionPane.YES_OPTION){
				dispose();
				}
			}
		}

	}

		class manageMember extends JInternalFrame implements ActionListener, MouseListener, ItemListener{
			JLabel judul, emailLabel, passwordLabel, genderLabel, phoneLabel, DOBLabel, addressLabel;
			JTextField emailTextField, passwordTextField, phoneTextField, memberIDTextField;
			JRadioButton rbMale, rbFemale;
			JTextArea addressTextArea;
			JButton insertButton, updateButton, deleteButton, cancelButton, update2Button;
			JTable tabelMember;
			Vector<Vector>data;
			Vector detail, header;
			JScrollPane spMember;
			JPanel panel1, panel2, panel3,panel4, panel5, panel6;
			ButtonGroup bg;
			
			SpinnerDateModel Date = new SpinnerDateModel();
			JSpinner DOB = new JSpinner(Date);
			Component mySpinnerEditor = DOB.getEditor();
			JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
			Calendar calendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
			
			public manageMember(){
				initMember();
				setSize(600,400);
				setTitle("Manage Member");
				setResizable(false);
				setClosable(true);
				setVisible(true);
			}
			
			public void initMember(){
				judul = new JLabel("Manage Member");
				emailLabel = new JLabel("Email");
				passwordLabel = new JLabel("Password");
				genderLabel = new JLabel("Gender");
				phoneLabel = new JLabel("Phone");
				DOBLabel = new JLabel("Date of Birth");
				addressLabel = new JLabel("Address");
				
				emailTextField = new JTextField();
				passwordTextField = new JTextField();
				phoneTextField = new JTextField();
				memberIDTextField = new JTextField();
				
				rbMale = new JRadioButton("Male");
				rbFemale = new JRadioButton("Female");
				
				addressTextArea = new JTextArea();
				
				insertButton = new JButton("Insert");
				updateButton = new JButton("Update");
				deleteButton = new JButton("Delete");
				cancelButton = new JButton("Cancel");
				update2Button = new JButton("Save Update");
				
				tabelMember = new JTable();
				spMember = new JScrollPane(tabelMember);
				
				
				bg = new ButtonGroup();
				bg.add(rbMale);
				bg.add(rbFemale);
				
				//layout
				panel1 = new JPanel();
				panel2 = new JPanel();
				panel3 = new JPanel();
				panel4 = new JPanel();
				panel5 = new JPanel();
				panel6 = new JPanel();
				
				add(panel1);
				
				panel1.setLayout(new GridLayout(4,1));
				panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
				panel1.add(panel2);
				panel2.add(judul);
				
				panel1.add(spMember);
				loadDataMember(con.executeQuery("SELECT Email,Password,Phone,DOB,Gender,Address FROM member"));
				
				panel3.setLayout(new GridLayout(3,4));
				panel1.add(panel3);
				panel3.add(emailLabel);
				panel3.add(emailTextField);
				panel3.add(phoneLabel);
				panel3.add(phoneTextField);
				panel3.add(passwordLabel);
				panel3.add(passwordTextField);
				panel3.add(DOBLabel);
				panel3.add(DOB);
				panel3.add(genderLabel);
				panel4.setLayout(new GridLayout(1,2));
				panel3.add(panel4);
				panel4.add(rbMale);
				panel4.add(rbFemale);
				panel3.add(addressLabel);
				panel3.add(addressTextArea);
				
				panel6.setLayout(new BorderLayout());
				panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
				panel1.add(panel6);
				panel6.add(panel5, BorderLayout.SOUTH);
				panel5.add(insertButton);
				panel5.add(updateButton);
				panel5.add(deleteButton);
				panel5.add(cancelButton);
				
				emailTextField.setEnabled(false);
				phoneTextField.setEnabled(false);
				passwordTextField.setEnabled(false);
				DOB.setEnabled(false);
				rbMale.setEnabled(false);
				rbFemale.setEnabled(false);
				addressTextArea.setEnabled(false);
				cancelButton.setEnabled(false);
				
				tabelMember.addMouseListener(this);
				insertButton.addActionListener(this);
				updateButton.addActionListener(this);
				cancelButton.addActionListener(this);
				deleteButton.addActionListener(this);
				update2Button.addActionListener(this);
			}
			public void loadDataMember(ResultSet rs){
				header = new Vector<>();
				header.add("Email");
				header.add("Password");
				header.add("Phone");
				header.add("DOB");
				header.add("Gender");
				header.add("Address");
				data = new Vector<>();
				
				try{
					
					while(rs.next()){
						
						String email = rs.getString("Email");
						String password = rs.getString("Password");
						String phone = rs.getString("Phone");
						String dob = rs.getString("DOB");
						String gender = rs.getString("Gender");
						String address = rs.getString("Address");
						
						detail = new Vector<>();
						detail.add(email);
						detail.add(password);
						detail.add(phone);
						detail.add(dob);
						detail.add(gender);
						detail.add(address);
						
						data.add(detail);
					
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				DefaultTableModel dtm = new DefaultTableModel(data, header){
					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				tabelMember.setModel(dtm);
			}
			
			public void cancel(){
				bg.clearSelection();
				emailTextField.setText("");
				passwordTextField.setText("");
				phoneTextField.setText("");
				addressTextArea.setText("");
				String null1 = "0";
				DOB.setValue(null1);
			}
			
			 public String getSelectedOption() {
			        Enumeration<AbstractButton> radioButtons = bg.getElements();
			        while (radioButtons.hasMoreElements()) {
			            AbstractButton currentRadioButton = radioButtons.nextElement();
			            if (currentRadioButton.isSelected()) {
			                return currentRadioButton.getText();
			            }
			        }
			        return null;
			    }

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==insertButton){
					cancelButton.setEnabled(true);
					updateButton.setEnabled(false);
					deleteButton.setEnabled(false);
					insertButton.setText("Save Insert");
					
					emailTextField.setEnabled(true);
					phoneTextField.setEnabled(true);
					passwordTextField.setEnabled(true);
					DOB.setEnabled(true);
					rbMale.setEnabled(true);
					rbFemale.setEnabled(true);
					addressTextArea.setEnabled(true);
					
					bg.clearSelection();
					emailTextField.setText(null);
					passwordTextField.setText(null);
					phoneTextField.setText(null);
					addressTextArea.setText(null); 
					
					if(insertButton.getText().equals("Save Insert")){
						con.executeUpdate("INSERT INTO member VALUES(NULL,'"+emailTextField.getText()+"','"+passwordTextField.getText()+"','"+phoneTextField.getText()+"','"+DOB.getValue().toString()+"','"+bg.getSelection().toString()+"','"+addressTextArea.getText()+"',NULL)");
						if(emailTextField.getText().equals("")){
							JOptionPane.showMessageDialog(getContentPane(),"Email Must be Filled!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(!emailTextField.getText().contains("@")&&!emailTextField.getText().contains(".")){
							JOptionPane.showMessageDialog(getContentPane(), "Email Format is Incorrect!","Error!",JOptionPane.ERROR_MESSAGE);
						}else if(emailTextField.getText().indexOf('@')>1){
							JOptionPane.showMessageDialog(getContentPane(), "Email Format is Incorrect!","Error!",JOptionPane.ERROR_MESSAGE);
						}else if(emailTextField.getText().startsWith("")&&emailTextField.getText().startsWith("@")&&emailTextField.getText().endsWith("")&&emailTextField.getText().endsWith("@")){
							JOptionPane.showMessageDialog(getContentPane(), "Email Format is Incorrect!","Error!",JOptionPane.ERROR_MESSAGE);
						}else if(passwordTextField.getText().equals("")){
							JOptionPane.showMessageDialog(getContentPane(),"Password Must be Filled!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(!(passwordTextField.getText().length()>=6)&&!(passwordTextField.getText().length()<=12)){
							JOptionPane.showMessageDialog(getContentPane(),"Password Must be 6-12 Characters!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(phoneTextField.getText().equals("")){
							JOptionPane.showMessageDialog(getContentPane(),"Phone Number Must be Filled!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(!phoneTextField.getText().startsWith("08")){
							JOptionPane.showMessageDialog(getContentPane(),"Phone Number Must Start with '08'!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(DOB.getValue().equals(0)){
							JOptionPane.showMessageDialog(getContentPane(),"Date of Birth Must be Filled!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(!rbMale.isSelected()&&!rbFemale.isSelected()){
							JOptionPane.showMessageDialog(getContentPane(),"Gender Must be Selected!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else if(addressTextArea.getText().equals("")&&!addressTextArea.getText().endsWith("street")){
							JOptionPane.showMessageDialog(getContentPane(),"Address Must be Filled and End with 'Street'!", "Error!", JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "Data Added Successfully!");
						}
					}
				}else if(e.getSource()==updateButton){
					
					
					if(tabelMember.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(getContentPane(),"No Row Selected!", "Error!", JOptionPane.ERROR_MESSAGE);
					}else{
						emailTextField.setEnabled(true);
						phoneTextField.setEnabled(true);
						passwordTextField.setEnabled(true);
						DOB.setEnabled(true);
						rbMale.setEnabled(true);
						rbFemale.setEnabled(true);
						addressTextArea.setEnabled(true);
						
						insertButton.setEnabled(false);
						deleteButton.setEnabled(false);
						cancelButton.setEnabled(true);
						
						panel5.removeAll();
					    panel5.revalidate();
					    panel5.repaint();
					    panel5.add(insertButton);
					    panel5.add(update2Button);
					    panel5.add(deleteButton);
					    panel5.add(cancelButton);
						
					}
						
				
				}else if(e.getSource()==update2Button){
					String cekGender = getSelectedOption();	
					
					String valueGender = getSelectedOption();
					String valueEmail = emailTextField.getText();
					String valuePassword = password.getText();
					String valuePhone1 = phoneTextField.getText();
					String valueDOB1 = DOB.getValue().toString();
					String valueAddress = addressTextArea.getText();
					    
					String rolename = "Member";
					    
					   
					     if(valueEmail.isEmpty()){
					      JOptionPane.showMessageDialog(null, "Email must not be empty");
					     }else if((!valueEmail.contains("@") || !valueEmail.contains("."))){
					      JOptionPane.showMessageDialog(null, "Email must be in correct format"); 
					     }else if(valueEmail.startsWith("@") || valueEmail.startsWith(".")){
					      JOptionPane.showMessageDialog(null, "Email must be in correct format");
					     }else if(valueEmail.endsWith("@") || valueEmail.endsWith(".")){
					      JOptionPane.showMessageDialog(null, "Email must be in correct format");
					     }else if (valueEmail.contains("@@")){
					      JOptionPane.showMessageDialog(null, "Email must be in correct format");
					     }else if(valuePassword.isEmpty()){
					      JOptionPane.showMessageDialog(null, "Password must not be empty");
					     }else if(valuePassword.length()<6 || valuePassword.length()>12){
					      JOptionPane.showMessageDialog(null, "Password must be between 6 and 12 characters");
					     }else if(valuePassword.matches("[a-zA-Z0-9]+")) {
					      JOptionPane.showMessageDialog(null, "Password must be alphanumeric");
					     }else if(valueGender == null){
					      JOptionPane.showMessageDialog(null, "Gender must be selected");
					      return; 
					     }else{
					      if(rbMale.isSelected()){
					       cekGender = "Male";
					      }else if (rbFemale.isSelected()){
					       cekGender = "Female";
					       
					      }
					      
					      String querySelect = "SELECT Email,Password,Phone,DOB,Gender,Address FROM Member";
					      loadDataMember(con.executeQuery(querySelect));

					        String dobvalue = DOB.getValue().toString();       
					        String newemail = emailTextField.getText();
					        String newpassword = passwordTextField.getText();
					        String newphone = phoneTextField.getText();
					        String newaddress = addressTextArea.getText();
					       
					        
					        String query =  ("UPDATE member SET Email='"+newemail+"' , Password='"+newpassword+"' ,Phone='"+newphone+"',DOB='"+dobvalue+"' ,Gender='"+cekGender+"' ,Address='"+newaddress+"'WHERE Email='"+newemail+"'");
					        con.executeUpdate(query);
					      
					     JOptionPane.showMessageDialog(null, "Data Updated Successfully");
					     loadDataMember(con.executeQuery("SELECT Email,Password,Phone,DOB,Gender,Address FROM Member"));
					     
					     emailTextField.setText("");
					     passwordTextField.setText("");
					     bg.clearSelection();
					     phoneTextField.setText("");
					     addressTextArea.setText("");
					     
					     emailTextField.setEditable(false);
					     passwordTextField.setEditable(false);
					     phoneTextField.setEditable(false);
					     rbMale.setEnabled(false);
					     rbFemale.setEnabled(false);
					     addressTextArea.setEditable(false);
					     DOB.setEnabled(false);
					     
					     insertButton.setEnabled(true);
					     deleteButton.setEnabled(true);
					     cancelButton.setEnabled(false);
					     updateButton.setEnabled(true);
					     
					     panel5.removeAll();
					     panel5.revalidate();
					     panel5.repaint();
					     panel5.add(insertButton);
					     panel5.add(updateButton);
					     panel5.add(deleteButton);
					     panel5.add(cancelButton);
					  
					    }
					  	
				}else if(e.getSource()==deleteButton){
					if(tabelMember.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(getContentPane(),"No Row Selected!", "Error!", JOptionPane.ERROR_MESSAGE);
					}else{
						int pilih = JOptionPane.showConfirmDialog(null, "Are you sure?");
						if(pilih==JOptionPane.YES_OPTION){
							String sqlDelete = "DELETE FROM member WHERE Email='"+emailTextField.getText()+"'";
							con.executeUpdate(sqlDelete);
						}
					}
					loadDataMember(con.executeQuery("SELECT Email,Password,Phone,DOB,Gender,Address FROM member"));
				}else if(e.getSource()==cancelButton){
					int replyoption = JOptionPane.showConfirmDialog(null, "Are you sure to reset this form?","Confirm",JOptionPane.YES_NO_OPTION);
					if(replyoption == JOptionPane.YES_OPTION){
						cancel();
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int index = tabelMember.getSelectedRow();
				TableModel model = tabelMember.getModel();
			
				String valueEmail = model.getValueAt(index, 0).toString();
				String valuePhone = model.getValueAt(index, 2).toString();
				String valuePassword = model.getValueAt(index, 1).toString();
				String valueDOB = model.getValueAt(index, 3).toString();
				String valueBG = model.getValueAt(index, 4).toString();
				String valueAddress = model.getValueAt(index, 5).toString();
				
				emailTextField.setText(valueEmail);
				phoneTextField.setText(valuePhone);
				passwordTextField.setText(valuePassword);
				addressTextArea.setText(valueAddress);
				
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			    java.util.Date d = null;
			    
				try {
					d = (java.util.Date)format.parse(valueDOB);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    java.sql.Time time = new java.sql.Time(d.getTime());
			    DOB.setValue(time);
				
				if(valueBG.equals("Male")){
					rbMale.setSelected(true);
					rbFemale.setSelected(false);
					
				}else if(valueBG.equals("Female")){
					rbMale.setSelected(false);
					rbFemale.setSelected(true);
					
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}
		
		class manageCake extends JInternalFrame implements ActionListener, MouseListener{
			JLabel judulCake, cakeID, cakeName, brand, stock, price, null1, null2;
			JTextField cakeIDText, cakeNameText, priceText;
			JSpinner stockSpinner;
			JComboBox brandComboBox;
			JButton insert, update, delete, cancel, update2,insert2;
			JTable tabelCake;
			JScrollPane spCake;
			Vector<Vector> data;
			Vector detail,header;
			JPanel panel1, panel2, panel3, panel4,panel5;
			String choose = "--Choose--";
			String cekBrandID;
			

			public manageCake(){
				initCake();
				setSize(600,400);
				setTitle("Manage Cake");
				setResizable(false);
				setClosable(true);
				setVisible(true);
			}
			
			public void initCake(){
				judulCake = new JLabel("Manage Cake");
				cakeID = new JLabel("Product ID");
				cakeName = new JLabel("Cake Name");
				brand = new JLabel("Brand");
				stock = new JLabel("Stock");
				price = new JLabel("Price");
				null1 = new JLabel("");
				null2 = new JLabel("");
				
				cakeIDText = new JTextField();
				cakeNameText = new JTextField();
				priceText = new JTextField();
				
				stockSpinner = new JSpinner();
				
				brandComboBox = new JComboBox();
				brandComboBox.addItem(choose);
				brandComboBox.setSelectedItem(choose);
				
				insert = new JButton("Insert");
				insert2 = new JButton("Save Insert");
				update2 = new JButton("Save Update");
				update = new JButton("Update");
				delete = new JButton("Delete");
				cancel = new JButton("Cancel");
				
				tabelCake = new JTable();
				spCake = new JScrollPane(tabelCake);
				
				
				//layout
				panel1= new JPanel();
				panel2 = new JPanel();
				panel3 = new JPanel();
				panel4 = new JPanel();
				panel5 = new JPanel();
				
				add(panel1);
				
				panel1.setLayout(new GridLayout(4,1));
				panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
				panel1.add(panel2);
				panel2.add(judulCake);
				
				panel1.add(spCake);
				loadDataCake(con.executeQuery("SELECT CakeId,CakeName,BrandName,Stock,Price FROM cake INNER JOIN brand ON cake.BrandID = brand.BrandID"));
				
				panel3.setLayout(new GridLayout(3,4));
				panel1.add(panel3);
				panel3.add(cakeID);
				panel3.add(cakeIDText);
				panel3.add(null1);
				panel3.add(null2);
				panel3.add(cakeName);
				panel3.add(cakeNameText);
				panel3.add(brand);
				panel3.add(brandComboBox);
				panel3.add(stock);
				panel3.add(stockSpinner);
				panel3.add(price);
				panel3.add(priceText);
				
				panel5.setLayout(new BorderLayout());
				panel4.setLayout(new FlowLayout(FlowLayout.CENTER));
				panel1.add(panel5);
				panel5.add(panel4, BorderLayout.SOUTH);
				panel4.add(insert);
				panel4.add(update);
				panel4.add(delete);
				panel4.add(cancel);
				
				cakeIDText.setEnabled(false);
				cakeNameText.setEnabled(false);
				stockSpinner.setEnabled(false);
				brandComboBox.setEnabled(false);
				priceText.setEnabled(false);
				cancel.setEnabled(false);
				
				tabelCake.addMouseListener(this);
				insert.addActionListener(this);
				update.addActionListener(this);
				delete.addActionListener(this);
				update2.addActionListener(this);
				insert2.addActionListener(this);
				cancel.addActionListener(this);
			}
			
			void cekBrand(ResultSet rs){
				try{
					while(rs.next()){
						cekBrandID = rs.getString(2);
					
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public void cancel(){
				cakeIDText.setText("");
				cakeNameText.setText("");
				String ilangin = "0";
				stockSpinner.setValue(Integer.valueOf(ilangin));
				brandComboBox.setSelectedItem(choose);
				priceText.setText("");
			
			}
			
			
			public void loadDataCake(ResultSet rs){
				header = new Vector<>();
				header.add("Product ID");
				header.add("Cake Name");
				header.add("Brand");
				header.add("Stock");
				header.add("Price");

				data = new Vector<>();
				
				try{
					
					while(rs.next()){
						int cakeid = rs.getInt("CakeId");
						String cakename = rs.getString("CakeName");
						String brand = rs.getString("BrandName");
						int stock = rs.getInt("Stock");
						int price = rs.getInt("Price");
						
						brandComboBox.addItem(rs.getString("BrandName"));
						
						detail = new Vector<>();
						detail.add(cakeid);
						detail.add(cakename);
						detail.add(brand);
						detail.add(stock);
						detail.add(price);
						
						data.add(detail);
					
					}
				}catch (Exception e){
					e.printStackTrace();
					}
				DefaultTableModel dtmCake = new DefaultTableModel(data, header){
					public boolean isCellEditable(int row, int column){
						return false;
						}
					};
					tabelCake.setModel(dtmCake);
				}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = tabelCake.getSelectedRow();
				TableModel model = tabelCake.getModel();
				
				if(e.getSource()==insert){
					
					cakeIDText.setEnabled(true);
					cakeNameText.setEnabled(true);
					priceText.setEnabled(true);
					stockSpinner.setEnabled(true);
					brandComboBox.setEnabled(true);
					cancel.setEnabled(true);
					update.setEnabled(false);
					delete.setEnabled(false);
					
					cakeNameText.setText("");
					priceText.setText("");
					
					panel4.removeAll();
				    panel4.revalidate();
				    panel4.repaint();
				    panel4.add(insert2);
				    panel4.add(update);
				    panel4.add(delete);
				    panel4.add(cancel);
					
				
				

				}else if(e.getSource()==insert2){
					String valueCakeName = cakeNameText.getText();
					String valuePrice = priceText.getText();
					String valueStock = stockSpinner.getValue().toString();
					int valueBrand = brandComboBox.getSelectedIndex();
					String valueID = cakeIDText.getText();
					
					con.executeUpdate("INSERT INTO cake VALUES('"+valueID+"','"+valueBrand+"','"+valueCakeName+"','"+valuePrice+"','"+valueStock+"')");
//					con.executeUpdate("INSERT INTO brand (BrandName) VALUES('"+valueBrand+"')");
				
						
					
					loadDataCake(con.executeQuery("SELECT CakeId,CakeName,BrandName,Stock,Price FROM cake INNER JOIN brand ON cake.BrandID = brand.BrandID"));
					
				

				}else if(e.getSource()==update){
					if(tabelCake.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(getContentPane(),"No Row Selected!", "Error!", JOptionPane.ERROR_MESSAGE);
					}else{
						stockSpinner.setEnabled(true);
						brandComboBox.setEnabled(true);
						cakeNameText.setEnabled(true);
						cakeIDText.setEnabled(true);
						priceText.setEnabled(true);
						
						insert.setEnabled(false);
						delete.setEnabled(false);
						cancel.setEnabled(true);
						
						panel4.removeAll();
					    panel4.revalidate();
					    panel4.repaint();
					    panel4.add(insert);
					    panel4.add(update2);
					    panel4.add(delete);
					    panel4.add(cancel);
						
					}
				
				
				}else if(e.getSource()==update2){
					
					 String IDField = cakeIDText.getText();
					    
					    String nameField = cakeNameText.getText(); 
					    
					    int stockField = Integer.parseInt(stockSpinner.getValue().toString());
					     
					    int priceField = Integer.parseInt(priceText.getText().toString());
					     
					    int brandField = brandComboBox.getSelectedIndex();
					    
					    String brandbrand = brandComboBox.getSelectedItem().toString();
					    
					    if(nameField.length() < 5 || nameField.length()> 20){
					     JOptionPane.showMessageDialog(null,"Cake name must be between 5 - 20 characters");
					      
					    }else if(brandComboBox.getSelectedItem() == null){
					     JOptionPane.showMessageDialog(null, "Brand must be chosen");
					      
					    }else if (stockField < 1 ){
					     JOptionPane.showMessageDialog(null, "Stock must be more than 0");
					      
					    }else if (priceField < 1000){
					     JOptionPane.showMessageDialog(null, "Price must be more than 1000");
					      
					    }else if (brandComboBox.getSelectedItem() == choose){
					     JOptionPane.showMessageDialog(null, "Please choose a brand");
					     
					    }else {
					     JOptionPane.showMessageDialog(null, "Update Success");
					     con.executeUpdate("UPDATE cake, brand SET cake.CakeName='"+nameField+"' , brand.BrandName='"+brandbrand+"' ,cake.Price='"+priceField+"',cake.Stock='"+stockField+"'WHERE cake.CakeId='"+IDField+"'AND brand.BrandId='"+brandField+"'");
					    
					     loadDataCake(con.executeQuery("SELECT CakeId,CakeName,BrandName,Stock,Price FROM cake INNER JOIN brand ON cake.BrandID = brand.BrandID"));
					      
					     cakeIDText.setText("");
					     cakeNameText.setText("");

					     priceText.setText("");
					      
					     priceText.setEnabled(false);
					     cakeIDText.setEnabled(false);
					     cakeNameText.setEnabled(false);
					     stockSpinner.setEnabled(false);
					     brandComboBox.setEnabled(false);
					     cancel.setEnabled(false);
					     update.setEnabled(true);
					     delete.setEnabled(true);
					      
					     panel4.removeAll();
					     panel4.revalidate();
					     panel4.repaint();
					     panel4.add(insert);
					     panel4.add(update);
					     panel4.add(delete);
					     panel4.add(cancel);
					      
					    }
					   
				
				
				}else if(e.getSource()==delete){
					if(tabelCake.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(getContentPane(),"No Row Selected!", "Error!", JOptionPane.ERROR_MESSAGE);
						
					}else{
							int pilih = JOptionPane.showConfirmDialog(null, "Are you sure?");
							if(pilih==JOptionPane.YES_OPTION){
								String sqlDelete = "DELETE FROM cake WHERE CakeId='"+cakeIDText.getText()+"'";
								con.executeUpdate(sqlDelete);
							}
						}
					loadDataCake(con.executeQuery("SELECT CakeId,CakeName,BrandName,Stock,Price FROM cake INNER JOIN brand ON cake.BrandID = brand.BrandID"));
					
				}else if(e.getSource()==cancel){
					int replyoption = JOptionPane.showConfirmDialog(null, "Are you sure to reset this form?","Confirm",JOptionPane.YES_NO_OPTION);
					if(replyoption == JOptionPane.YES_OPTION){
						cancel();
					}
				}
			}
		

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int index = tabelCake.getSelectedRow();
				TableModel model = tabelCake.getModel();
			
				String valueID = model.getValueAt(index, 0).toString();
				String valueCakeName = model.getValueAt(index, 1).toString();
				Object valueBrand = model.getValueAt(index, 2).toString();
				String valueStock= model.getValueAt(index, 3).toString();
				String valuePrice = model.getValueAt(index, 4).toString();
				
				cakeIDText.setText(valueID);
				cakeNameText.setText(valueCakeName);
				brandComboBox.setSelectedItem(valueBrand);;
				priceText.setText(valuePrice);
				stockSpinner.setValue(Integer.valueOf(valueStock));
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		class manageTransaction extends JInternalFrame implements MouseListener, ActionListener{
			JLabel judulTransaction, transactionID, cakeName, date, email, quantity, null1, null2;
			JTextField transactionIDText;
			JSpinner dateSpinner, quantitySpinner;
			JComboBox cakeNameComboBox, emailComboBox;
			JButton delete;
			JTable tabelTransaction;
			JScrollPane spTransaction;
			Vector <Vector> data;
			Vector detail,header;
			JPanel panel1, panel2, panel3, panel4, panel5;
			Object cekDate;
			public manageTransaction(){
				initTransaction();
				setSize(1000,400);
				setTitle("Manage Transaction");
				setResizable(false);
				setClosable(true);
				setVisible(true);
			}
			
			public void initTransaction(){
				judulTransaction = new JLabel("Manage Transaction");
				transactionID = new JLabel("Transaction ID");
				cakeName = new JLabel("Cake Name");
				date = new JLabel("Date");
				email = new JLabel("Email");
				quantity = new JLabel("Quantity");
				null1 = new JLabel("");
				null2 = new JLabel("");
				
				transactionIDText = new JTextField();
				
				dateSpinner = new JSpinner();
				
				
				quantitySpinner = new JSpinner();
				
				String choose = "--Choose--";
				cakeNameComboBox = new JComboBox();
				emailComboBox = new JComboBox();
				cakeNameComboBox.addItem(choose);
				emailComboBox.addItem(choose);
				cakeNameComboBox.setSelectedItem(choose);
				emailComboBox.setSelectedItem(choose);
			
				delete = new JButton("Delete");
				
				tabelTransaction = new JTable();
				spTransaction = new JScrollPane(tabelTransaction);
				
				
				//layout
				panel1 = new JPanel();
				panel2 = new JPanel();
				panel3 = new JPanel();
				panel4 = new JPanel();
				panel5 = new JPanel();
				
				add(panel1);
				
				panel1.setLayout(new GridLayout(4,1));
				panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
				panel1.add(panel2);
				panel2.add(judulTransaction);
				
				panel1.add(spTransaction);
				loadDataTransaction(con.executeQuery("SELECT t.TransactionID,Email,CakeName,Quantity,TransactionDate FROM transaction t JOIN detailtransaction dt ON t.TransactionID = dt.TransactionID JOIN member m ON t.MemberId = m.MemberId JOIN cake c ON c.CakeId = dt.CakeId"));
				
				panel3.setLayout(new GridLayout(3,4));
				panel1.add(panel3);
				panel3.add(transactionID);
				panel3.add(transactionIDText);
				panel3.add(null1);
				panel3.add(null2);
				panel3.add(cakeName);
				panel3.add(cakeNameComboBox);
				panel3.add(email);
				panel3.add(emailComboBox);
				panel3.add(date);
				panel3.add(dateSpinner);
				panel3.add(quantity);
				panel3.add(quantitySpinner);
			
				panel5.setLayout(new BorderLayout());
				panel4.setLayout(new FlowLayout(FlowLayout.CENTER));
				panel1.add(panel5);
				panel5.add(panel4, BorderLayout.SOUTH);
				panel4.add(delete);
				
				
				transactionIDText.setEnabled(false);
				cakeNameComboBox.setEnabled(false);
				emailComboBox.setEnabled(false);
				dateSpinner.setEnabled(false);
				quantitySpinner.setEnabled(false);
				
				tabelTransaction.addMouseListener(this);
				delete.addActionListener(this);

			}
			public void loadDataTransaction(ResultSet rs){
				header = new Vector<>();
				header.add("Transaction ID");
				header.add("Email");
				header.add("Cake Name");
				header.add("Quantity");
				header.add("Date");

				data = new Vector<>();
				
				try{
					
					while(rs.next()){
						int transactionid = rs.getInt("TransactionID");
						String email = rs.getString("Email");
						String cakename = rs.getString("CakeName");
						int quantity = rs.getInt("Quantity");
						String date = rs.getString("TransactionDate");
						
						cakeNameComboBox.addItem(rs.getString("CakeName"));
						emailComboBox.addItem(rs.getString("Email"));
						
						detail = new Vector<>();
						detail.add(transactionid);
						detail.add(email);
						detail.add(cakename);
						detail.add(quantity);
						detail.add(date);
						
						data.add(detail);
					
					}
				}catch (Exception e){
					e.printStackTrace();
					}
				DefaultTableModel dtmTransaction = new DefaultTableModel(data, header){
					public boolean isCellEditable(int row, int column){
						return false;
						}
					};
					tabelTransaction.setModel(dtmTransaction);
				}
			
			void cekDate(ResultSet rs){
				try{
					while(rs.next()){
						cekDate = rs.getString("TransactionDate");
					
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int index = tabelTransaction.getSelectedRow();
				TableModel model = tabelTransaction.getModel();
			
				String valueID = model.getValueAt(index, 0).toString();
				String valueEmail = model.getValueAt(index, 1).toString();
				Object valueCakeName = model.getValueAt(index, 2);
				String valueQuantity= model.getValueAt(index, 3).toString();
				Object valueDate= model.getValueAt(index, 4);
				
				transactionIDText.setText(valueID);
				cakeNameComboBox.setSelectedItem(valueCakeName);
				emailComboBox.setSelectedItem(valueEmail);;
				quantitySpinner.setValue(Integer.valueOf(valueQuantity));
				
				
				SpinnerDateModel model1 = new SpinnerDateModel();
			    model1.setCalendarField(Calendar.DAY_OF_YEAR);

			    
			    dateSpinner.setModel(model1);

			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    java.util.Date d = null;
				try {
					d = (java.util.Date)format.parse((String) valueDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    java.sql.Time time = new java.sql.Time(d.getTime());
			    dateSpinner.setValue(time );
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==delete){
					if(tabelTransaction.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(getContentPane(),"No Row Selected!", "Error!", JOptionPane.ERROR_MESSAGE);
						
					}else{
							int pilih = JOptionPane.showConfirmDialog(null, "Are you sure?");
							if(pilih==JOptionPane.YES_OPTION){
								String sqlDelete = "DELETE FROM transaction WHERE TransactionID='"+transactionIDText.getText()+"'";
								con.executeUpdate(sqlDelete);
							}
						}
					loadDataTransaction(con.executeQuery("SELECT t.TransactionID,Email,CakeName,Quantity,TransactionDate FROM transaction t JOIN detailtransaction dt ON t.TransactionID = dt.TransactionID JOIN member m ON t.MemberId = m.MemberId JOIN cake c ON c.CakeId = dt.CakeId"));
					
				}
			}
		}
	}
}




