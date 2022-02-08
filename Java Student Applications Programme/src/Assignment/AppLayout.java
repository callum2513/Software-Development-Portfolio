package Assignment;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.*;

import javax.swing.*;


public class AppLayout extends JFrame implements ActionListener
{
	private OrderedList applicationList = new OrderedList();
	Application appEdit = new Application();
	private Font norm = new Font("Consoloas", Font.BOLD, 18);
	private Font heading = new Font("Consoloas", Font.BOLD, 24);
	
	private int totalPoints = 0, allocationNum = 0;
	private int qualIndex, qualIndexEdit;
	private String temp = " " ;
	private LocalDate currentDate = LocalDate.now();
	private String errorMessage1;
	String displayHeadings = ("   Forename    Surname         Email                      TelNum(+44)        Date            Points       Priority\n"				+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	
	Container cPane;
	private JLabel lblAddHeader,lblForename, lblSurname, lblTelNum, lblEmail, lblDateOfApp, lblQualType, lblGrades, lblDateOfAppShow, lblSearch;
	private JLabel lblEditForename, lblEditSurname, lblEditTelNum, lblEditEmail, lblEditDateOfApp, lblEditQualType, lblEditDateOfAppShow, lblAllocate;
	private JTextField txtfForename, txtfSurname, txtfTelNum, txtfEmail, txtfSearch, txtfEditForename, txtfEditSurname, txtfEditTelNum, txtfEditEmail, txtfAllocatePlaces;
	private JComboBox cmbQual, cmbGrade, cmbEditQual, cmbEditGrade;
	private JPanel pLeft, pRight;
	private JButton btnAddNext, btnClear, btnSave, btnAddGrade, btnResetGrade, btnEmailSearch, btnEditSave, btnEditCancel, btnDisplayExit;
	private JButton btnEditDelete, btnEditAddGrade, btnEditResetGrade, btnSortByPoints, btnAllocatePlaces, btnSurnameAZ, btnSurnameZA, btnsortByEmailAZ, btnsortByEmailZA, btnsortByPriorityDate;
	private JTextArea gradeDisplay, gradeEditDisplay, displayAll, allocatePlaces;
	private DefaultComboBoxModel[] models = new DefaultComboBoxModel[4], modelsEdit = new DefaultComboBoxModel[4];
	
	// Menu Bar //
	private JMenuBar menuBar;
	private JMenu menuAdd, menuEdit, menuDisplay, menuSystem, menuAllocate;
	private JMenuItem menuAddApp, menuEditApp, menuSystemExit, menuDisplayApps, menuAllocatePlaces;
	 
	
	public AppLayout()
	{
		handleExitButton();
		cmbModelsInitilisation();
		fillAppList();
		CreateLayout();
		clearScreen();
		showAddScreen();
	}
	private void CreateLayout() 
	{	
		// Creating pane, setting colour and layout to gridbag //
		cPane = getContentPane();
		cPane.setLocation(1300, 630);
		cPane.setBackground(new Color(80,80,80));
		cPane.setLayout(new GridBagLayout());
		
		// creating new panels
		pLeft = new JPanel();
		pRight = new JPanel();
		
		// Layouts of Panels //
		pLeft.setLayout(new GridBagLayout());
		pLeft.setBackground(new Color(60,60,60));
		pRight.setLayout(new GridBagLayout());
		pRight.setBackground(new Color(60,60,60));
		
		// Menu Bar //
		menuBar = new JMenuBar();
		// Add menu + items //
		menuAdd = new JMenu("Add");
		menuAddApp = new JMenuItem("Add Application");
		// Edit menu + items //
		menuEdit = new JMenu("Edit");
		menuEditApp = new JMenuItem ("Edit Application");
		// Display menu + items //
		menuDisplay = new JMenu("Display");
		menuDisplayApps = new JMenuItem ("Display All Applications");
		//System menu + items //
		menuSystem = new JMenu("System");
		menuSystemExit = new JMenuItem ("Exit");
		menuAllocate = new JMenu("Allocate");
		menuAllocatePlaces = new JMenuItem ("Allocate Places");
		menuAllocatePlaces.setFont(norm);
		
		
		// -------------------------- Add Menu Form Components ------------------------------ //
		// All labels, setting foreground colour and font - I set my fonts as variables and assigned them to components to make it easier to read //
		lblAddHeader = new JLabel("Please Fill in Application Details", JLabel.CENTER);
		lblAddHeader.setForeground(Color.white);
		lblAddHeader.setFont(heading);
		lblForename = new JLabel("Forename: ", JLabel.CENTER);
		lblForename.setForeground(Color.white);
		lblForename.setFont(norm);
		lblSurname = new JLabel("Surname: ", JLabel.CENTER);
		lblSurname.setForeground(Color.white);
		lblSurname.setFont(norm);
		lblTelNum = new JLabel("Tel Num (+44): ", JLabel.CENTER);
		lblTelNum.setForeground(Color.white);
		lblTelNum.setFont(norm);
		lblEmail = new JLabel("Email: ", JLabel.CENTER);
		lblEmail.setForeground(Color.white);
		lblEmail.setFont(norm);
		lblDateOfApp = new JLabel("Date of Application: ", JLabel.CENTER);
		lblDateOfApp.setForeground(Color.white);
		lblDateOfApp.setFont(norm);
		lblQualType = new JLabel("Please Choose your Qualification Type: ", JLabel.CENTER);
		lblQualType.setForeground(Color.white);
		lblQualType.setFont(norm);
		lblDateOfAppShow = new JLabel();
		lblDateOfAppShow.setText(currentDate.toString());
		lblDateOfAppShow.setForeground(Color.white);
		
		
		lblEditForename = new JLabel("Forename: ", JLabel.CENTER);
		lblEditForename.setForeground(Color.white);
		lblEditForename.setFont(norm);
		lblEditSurname = new JLabel("Surname: ", JLabel.CENTER);
		lblEditSurname.setForeground(Color.white);
		lblEditSurname.setFont(norm);
		lblEditTelNum = new JLabel("Tel Num: ", JLabel.CENTER);
		lblEditTelNum.setForeground(Color.white);
		lblEditTelNum.setFont(norm);
		lblEditEmail = new JLabel("Email: ", JLabel.CENTER);
		lblEditEmail.setForeground(Color.white);
		lblEditEmail.setFont(norm);
		lblEditDateOfApp = new JLabel("Date of Application: ", JLabel.CENTER);
		lblEditDateOfApp.setForeground(Color.white);
		lblEditDateOfApp.setFont(norm);
		lblEditQualType = new JLabel("Please Choose your Qualification Type: ", JLabel.CENTER);
		lblEditQualType.setForeground(Color.white);
		lblEditQualType.setFont(norm);
		lblEditDateOfAppShow = new JLabel();
		lblEditDateOfAppShow.setText(currentDate.toString());
		lblEditDateOfAppShow.setForeground(Color.white);
		
		lblSearch = new JLabel("Please Enter an Email Address");
		lblSearch.setForeground(Color.white);
		lblSearch.setFont(norm);
		
		lblAllocate = new JLabel("How many places do you want to allocate?");
		lblAllocate.setForeground(Color.white);
		lblAllocate.setFont(norm);
		txtfSearch = new JTextField("");
		
		// Qualification selecttion screen //

		lblGrades = new JLabel("Please Select your Grades", JLabel.CENTER);
		lblGrades.setForeground(Color.white);
		lblGrades.setFont(heading);
		
		
		// Creating Text Fields + ComboBox //
		txtfForename = new JTextField("");
		txtfSurname = new JTextField("");
		txtfTelNum = new JTextField("");
		txtfEmail = new JTextField("");
		gradeDisplay = new JTextArea("");
		gradeEditDisplay = new JTextArea("");
		cmbQual = new JComboBox();
		cmbGrade = new JComboBox();
		cmbQual.setModel(models[0]);
		cmbGrade.setModel(models[1]);
		
		txtfEditForename = new JTextField("");
		txtfEditSurname = new JTextField("");
		txtfEditTelNum = new JTextField("");
		txtfEditEmail = new JTextField("");
		txtfAllocatePlaces = new JTextField("");
		cmbEditQual = new JComboBox();
		cmbEditGrade = new JComboBox();
		cmbEditQual.setModel(modelsEdit[0]);
		cmbEditGrade.setModel(modelsEdit[1]);
		
		// display area //
		displayAll = new JTextArea();
		displayAll.setFont(new Font("Courier New", Font.BOLD, 13));
		allocatePlaces = new JTextArea("");
		allocatePlaces.setFont(new Font("Courier New", Font.BOLD, 13));
		
		// Creating Buttons //
		btnAddNext = new JButton("Sumbit Application");
		btnClear = new JButton("Reset");
		btnSave = new JButton("Save");
		btnSave.setFont(norm);
		btnAddGrade = new JButton("Add Grade");
		btnResetGrade = new JButton("Reset Grades");
		btnEditAddGrade = new JButton("Add Grade");
		btnSortByPoints = new JButton("Sort By UCAS Points");
		btnSurnameAZ = new JButton("Sort Surname A-Z");
		btnSurnameZA = new JButton("Sort Surname Z-A");
		btnsortByEmailAZ = new JButton("Sort By Email A-Z");
		btnsortByEmailZA = new JButton("Sort By Email Z-A");
		btnsortByPriorityDate = new JButton("Sort By Priority/Date");
		btnAllocatePlaces = new JButton("Allocate");
		btnDisplayExit = new JButton("Add");
		btnEditResetGrade = new JButton("Reset Grades");
		btnEmailSearch = new JButton("Search");
		
		btnEditCancel = new JButton("Cancel");
		btnEditSave = new JButton("Save");
		btnEditDelete = new JButton("Delete");
		
		// Adding menus and MenuItems to MenuBar //
		// Add Menu //
		menuBar.add(menuAdd);
		menuAdd.add(menuAddApp);
		// Edit Menu //
		menuBar.add(menuEdit);
		menuEdit.add(menuEditApp);
		// Display Menu //
		menuBar.add(menuDisplay);
		menuDisplay.add(menuDisplayApps);
		// Allocate Menu //
		menuBar.add(menuAllocate);
		menuAllocate.add(menuAllocatePlaces);
		// System  Menu //
		menuBar.add(menuSystem);
		menuSystem.add(menuSystemExit);
		// Sets menu bar on form //
		setJMenuBar(menuBar);
		
		// Set fonts of Menu Bar //
		menuAdd.setFont(norm);
		menuAddApp.setFont(norm);
		menuEdit.setFont(norm);
		menuEditApp.setFont(norm);
		menuDisplay.setFont(norm);
		menuDisplayApps.setFont(norm);
		menuSystem.setFont(norm);
		menuSystemExit.setFont(norm);
		menuAllocate.setFont(norm);
		
		actionListenerInitilisation();
		addComp();
	
	}
	private void addComp(Container con, Component c, int gridx, int gridy, int width, int height, int weightX,int weightY)
    {
		// Adds components to the gridbag layout// 
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);
        gc.fill=GridBagConstraints.BOTH;
        gc.gridx = gridx;
        gc.gridy = gridy;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = weightX;
        gc.weighty = weightY;

        con.add(c,gc);
        }
	private void addComp(Component c, int x, int y, int w, int h, int wX, int wY)
	{
		// Adds components to the gridbag layout// 
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth=w;
		gc.gridheight=h;
		gc.weightx=wX;
		gc.weighty=wY;
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(5,5,5,5);
		
		getContentPane().add(c, gc);
	}
	public void actionPerformed(ActionEvent e) 
	{
		// When each button is pressed, the code inside the if statement for that button is executed //
		if (e.getSource() == menuAddApp)
		{
			clearScreen();
			showAddScreen();
		}
		if (e.getSource() == btnAddNext)
		{
			if (validateAdd() == true)
			{
				addApplication();
				JOptionPane.showMessageDialog(null, "Application Submitted");
				clearScreen();
				displayAll();
			}
			else
			{
				JOptionPane.showMessageDialog(null, errorMessage1);
			}
		}
		if (e.getSource() == cmbQual)
		{
			if (cmbQual.getSelectedIndex() == 0)
			{
				qualIndex = 0;
			}
			if (cmbQual.getSelectedIndex() == 1)
			{
				cmbGrade.setModel(models[1]);
				qualIndex = 1;
			}
			else if (cmbQual.getSelectedIndex() == 2)
			{
				cmbGrade.setModel(models[1]);
				qualIndex = 2;
			}
			else if (cmbQual.getSelectedIndex() == 3)
			{
				cmbGrade.setModel(models[2]);
				qualIndex = 3;
			}
			else if (cmbQual.getSelectedIndex() == 4)
			{
				cmbGrade.setModel(models[3]);
				qualIndex = 4;
			}
		}
		if (e.getSource() == cmbEditQual)
		{
			if (cmbEditQual.getSelectedIndex() == 0)
			{
				qualIndexEdit = 0;
			}
			if (cmbEditQual.getSelectedIndex() == 1)
			{
				cmbEditGrade.setModel(models[1]);
				qualIndexEdit = 1;
			}
			else if (cmbEditQual.getSelectedIndex() == 2)
			{
				cmbGrade.setModel(models[1]);
				qualIndexEdit = 2;
			}
			else if (cmbEditQual.getSelectedIndex() == 3)
			{
				cmbEditGrade.setModel(models[2]);
				qualIndexEdit = 3;
			}
			else if (cmbEditQual.getSelectedIndex() == 4)
			{
				cmbEditGrade.setModel(models[3]);
				qualIndexEdit = 4;
			}
		}
		if (e.getSource() == btnClear)
		{
			int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to reset the application form?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
            {
            	clearAddForm();
            }
		}
		if (e.getSource() == btnAddGrade)
		{
			if (cmbQual.getSelectedIndex() == 0)
			{
				JOptionPane.showMessageDialog(null, "Error: Please Choose a Qualification Type");
				gradeDisplay.setText("");
				totalPoints = 0;
			}
			switch (qualIndex)
			{
			case 1: 
			{
				switch (cmbGrade.getSelectedIndex())
				{
					case 0 :
					{
						totalPoints += 56;
						temp = "A Level (A*): 56 \n";
						break;
					}
					case 1:
					{
						totalPoints += 48;
						temp = "A Level (A): 48 \n";
						break;
					}
					case 2:
					{
						totalPoints += 40;
						temp = "A Level (B): 40 \n";
						break;
					}
					case 3:
					{
						totalPoints += 32;
						temp = "A Level (C): 32 \n";
						break;
					}
					case 4:
					{
						totalPoints += 24; 
						temp = "A Level (D): 24 \n";
						break;
					}
					case 5:
					{
						totalPoints += 16;
						temp = "A Level (E): 16 \n";
						break;
					}
				}
			}break;
			
			case 2:
			{
				switch (cmbGrade.getSelectedIndex())
				{
					case 0 :
					{
						totalPoints += 24;
						temp = "AS Level (A*): 24 \n";
						break;
					}
					case 1:
					{
						totalPoints += 20;
						temp = "AS Level (A): 20 \n";
						break;
					}
					case 2:
					{
						totalPoints += 16;
						temp = "AS Level (B): 16 \n";
						break;
					}
					case 3:
					{
						totalPoints += 12;
						temp = "AS Level (C): 12 \n";
						break;
					}
					case 4:
					{
						totalPoints += 10;
						temp = "AS Level (D): 10 \n";
						break;
					}
					case 5:
					{
						totalPoints += 6;
						temp = "AS Level (E): 6 \n";
						break;
					}
				}
			}break;
			
			case 3:
			{
				switch (cmbGrade.getSelectedIndex())
				{ 
					case 0 :
					{
						totalPoints += 56;
						temp = "BTEC (D*): 56 \n";
						break;
					}
					case 1:
					{
						totalPoints += 48;
						temp = "BTEC (D): 48 \n";
						break;
					}
					case 2:
					{
						totalPoints += 32;
						temp = "BTEC (M): 32 \n";
						break;
					}
					case 3:
					{
						totalPoints += 16;
						temp = "BTEC (P): 16 \n";
						break;
					}
				}
			}break;
			
			case 4:
			{
				switch (cmbGrade.getSelectedIndex())
				{ 
					case 0 :
					{
						totalPoints += 36;
						temp = "Irish LC (H1): 36 \n";
						break;
					}
					case 1:
					{
						totalPoints += 30;
						temp = "Irish LC (H2): 30 \n";
						break;
					}
					case 2:
					{
						totalPoints += 24;
						temp = "Irish LC (H3): 24 \n";
						break;
					}
					case 3:
					{
						totalPoints += 18;
						temp = "Irish LC (H4): 18 \n";
						break;
					}
					case 4:
					{
						totalPoints += 12;
						temp = "Irish LC (H5): 12 \n";
						break;
					}
					case 5:
					{
						totalPoints += 9;
						temp = "Irish LC (H6): 9 \n";
						break;
					}
				}
			}	
		}
			gradeDisplay.append(temp);
		}
		if (e.getSource() == btnResetGrade)
		{
			gradeDisplay.setText("");
			totalPoints = 0;
		}
		if (e.getSource() == btnEditResetGrade)
		{
			gradeEditDisplay.setText("");
			totalPoints = 0;
		}
		if (e.getSource() == menuDisplayApps)
		{
			clearScreen();
			displayAll();
			sortByPriority();
		}
		if (e.getSource() == btnSortByPoints)
		{
			sortByPoints();
		}
		if (e.getSource() == btnSurnameAZ)
		{
			sortSurnameAZ();
		}
		if (e.getSource() == btnSurnameZA)
		{
			sortSurnameZA();
		}
		if (e.getSource() == btnsortByEmailAZ)
		{
			sortByEmailAZ();
		}
		if (e.getSource() == btnsortByEmailZA)
		{
			sortByEmailZA();
		}
		if (e.getSource() == btnsortByPriorityDate)
		{
			sortByPriority();
		}
		
		
		if (e.getSource() == btnDisplayExit)
		{
			clearScreen();
			showAddScreen();
		}
		
		if (e.getSource() == menuEditApp)
		{
			clearScreen();
			editSearch();
			
		}
		if(e.getSource() == btnEmailSearch)
		{
			
			appSearch();
			totalPoints = 0;
			gradeEditDisplay.setText("");	
			txtfSearch.setText("");
		}
		
		if (e.getSource() == btnEditSave)
		{
			if (validateEdit() == true)
			{
				editSave();
				JOptionPane.showMessageDialog(null, "Application Updated");
				gradeEditDisplay.setText("");
				clearScreen();
				displayAll();
			}
			else
			{
				JOptionPane.showMessageDialog(null, errorMessage1);
			}
			validateEdit();
			editSave();
		}
		
		if (e.getSource() == btnEditDelete)
		{
			int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this application?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
            {
            	editDelete(appEdit);
            	clearScreen();
            	showAddScreen();
            }
		}
		
		if (e.getSource() == btnEditCancel)
		{
			clearScreen();
			showAddScreen();
		}
		
		if (e.getSource() == btnEditAddGrade)
		{
			
			if (cmbEditQual.getSelectedIndex() == 0)
			{
				JOptionPane.showMessageDialog(null, "Error: Please Choose a Qualification Type");
				totalPoints = 0;
			}
			
			switch (qualIndexEdit)
			{
			case 1: 
			{
				switch (cmbEditGrade.getSelectedIndex())
				{
					case 0 :
					{
						totalPoints += 56;
						temp = "A Level (A*): 56 \n";
						break;
					}
					case 1:
					{
						totalPoints += 48;
						temp = "A Level (A): 48 \n";
						break;
					}
					case 2:
					{
						totalPoints += 40;
						temp = "A Level (B): 40 \n";
						break;
					}
					case 3:
					{
						totalPoints += 32;
						temp = "A Level (C): 32 \n";
						break;
					}
					case 4:
					{
						totalPoints += 24; 
						temp = "A Level (D): 24 \n";
						break;
					}
					case 5:
					{
						totalPoints += 16;
						temp = "A Level (E): 16 \n";
						break;
					}
				}
			}break;
			
			case 2:
			{
				switch (cmbEditGrade.getSelectedIndex())
				{
					case 0 :
					{
						totalPoints += 24;
						temp = "AS Level (A*): 24 \n";
						break;
					}
					case 1:
					{
						totalPoints += 20;
						temp = "AS Level (A): 20 \n";
						break;
					}
					case 2:
					{
						totalPoints += 16;
						temp = "AS Level (B): 16 \n";
						break;
					}
					case 3:
					{
						totalPoints += 12;
						temp = "AS Level (C): 12 \n";
						break;
					}
					case 4:
					{
						totalPoints += 10;
						temp = "AS Level (D): 10 \n";
						break;
					}
					case 5:
					{
						totalPoints += 6;
						temp = "AS Level (E): 6 \n";
						break;
					}
				}
			}break;
			case 3:
			{
				switch (cmbEditGrade.getSelectedIndex())
				{ 
					case 0 :
					{
						totalPoints += 56;
						temp = "BTEC (D*): 56 \n";
						break;
					}
					case 1:
					{
						totalPoints += 48;
						temp = "BTEC (D): 48 \n";
						break;
					}
					case 2:
					{
						totalPoints += 32;
						temp = "BTEC (M): 32 \n";
						break;
					}
					case 3:
					{
						totalPoints += 16;
						temp = "BTEC (P): 16 \n";
						break;
					}
				}
			}break;
			case 4:
			{
				switch (cmbEditGrade.getSelectedIndex())
				{ 
					case 0 :
					{
						totalPoints += 36;
						temp = "Irish LC (H1): 36 \n";
						break;
					}
					case 1:
					{
						totalPoints += 30;
						temp = "Irish LC (H2): 30 \n";
						break;
					}
					case 2:
					{
						totalPoints += 24;
						temp = "Irish LC (H3): 24 \n";
						break;
					}
					case 3:
					{
						totalPoints += 18;
						temp = "Irish LC (H4): 18 \n";
						break;
					}
					case 4:
					{
						totalPoints += 12;
						temp = "Irish LC (H5): 12 \n";
						break;
					}
					case 5:
					{
						totalPoints += 9;
						temp = "Irish LC (H6): 9 \n";
						break;
					}
				}
			}	
		}
			gradeDisplay.setVisible(false);
			gradeEditDisplay.setVisible(true);
			gradeEditDisplay.append(temp);
		}
		
		if (e.getSource() == menuAllocatePlaces)
		{
			showAllocateScreen();
		}
		if (e.getSource() == btnAllocatePlaces)
		{
			validateAllocateInput();
			
		}
		
		if (e.getSource() == menuSystemExit)
		{
			System.exit(0);
		}
	}
	private void clearScreen()
	{
		// sets add labels invisible //
		lblAddHeader.setVisible(false);
		lblForename.setVisible(false);
		lblSurname.setVisible(false);
		lblTelNum.setVisible(false);
		lblDateOfApp.setVisible(false);
		lblQualType.setVisible(false);
		lblEmail.setVisible(false);
		
		// sets add text fields invisible //
		txtfForename.setVisible(false);
		txtfSurname.setVisible(false);
		txtfTelNum.setVisible(false);
		lblDateOfAppShow.setVisible(false);
		cmbQual.setVisible(false);
		cmbGrade.setVisible(false);
		txtfEmail.setVisible(false);
		
		// sets add buttons invisible //
		btnAddNext.setVisible(false);
		btnClear.setVisible(false);
		pRight.setVisible(false);
		btnAddGrade.setVisible(false);
		btnResetGrade.setVisible(false);
		
		// Edit grade buttons invisible //
		btnEditAddGrade.setVisible(false);
		btnEditResetGrade.setVisible(false);
		
		//sets A/AS Level Grade screen invisible //
		lblGrades.setVisible(false);
		btnSave.setVisible(false);
		
		//display all set invisible //
		displayAll.setVisible(false);
		
		//edit screen set invisible //
		txtfSearch.setVisible(false);
		btnEmailSearch.setVisible(false);
		lblSearch.setVisible(false);

		gradeDisplay.setVisible(false);
		gradeEditDisplay.setVisible(false);
		
		txtfEditForename.setVisible(false); 	
		txtfEditSurname.setVisible(false); 		
		txtfEditTelNum.setVisible(false); 	 	
		txtfEditEmail.setVisible(false); 		     
		cmbEditQual.setVisible(false); 				
		cmbEditGrade.setVisible(false); 	
		
		lblEditDateOfAppShow.setVisible(false); 	 
		lblEditForename.setVisible(false); 	
		lblEditSurname.setVisible(false); 		
		lblEditTelNum.setVisible(false); 	 	
		lblEditEmail.setVisible(false); 		    
		lblEditDateOfAppShow.setVisible(false); 
		lblEditQualType.setVisible(false);
		lblEditDateOfApp.setVisible(false);
		
		btnEditSave.setVisible(false);
		btnEditCancel.setVisible(false);
		btnEditDelete.setVisible(false);
		btnSortByPoints.setVisible(false);
		btnDisplayExit.setVisible(false);
		btnAllocatePlaces.setVisible(false);
		allocatePlaces.setVisible(false);
		txtfAllocatePlaces.setVisible(false);
		lblAllocate.setVisible(false);
		btnSurnameAZ.setVisible(false);
		btnSurnameZA.setVisible(false);
		btnsortByEmailAZ.setVisible(false);
		btnsortByEmailZA.setVisible(false);
		btnsortByPriorityDate.setVisible(false);
	}
	private void showAddScreen()
	{
		// sets add labels invisible //
		lblAddHeader.setVisible(true);
		lblForename.setVisible(true);
		lblSurname.setVisible(true);
		lblTelNum.setVisible(true);
		lblDateOfApp.setVisible(true);
		lblQualType.setVisible(true);
		lblEmail.setVisible(true);
					
		// sets add text fields invisible //
		txtfForename.setVisible(true);
		txtfSurname.setVisible(true);
		txtfTelNum.setVisible(true);
		lblDateOfAppShow.setVisible(true);
		cmbQual.setVisible(true);
		cmbGrade.setVisible(true);
		txtfEmail.setVisible(true);
					
		// sets add buttons invisible //
		btnAddNext.setVisible(true);
		btnClear.setVisible(true);
		btnAddGrade.setVisible(true);
		btnResetGrade.setVisible(true);
		pRight.setVisible(true);
		gradeDisplay.setVisible(true);
		gradeEditDisplay.setVisible(false);
	}
	private void addApplication()
	{
		
		Application app = new Application();
		// Setting each attribute of application //
		app.setForename(txtfForename.getText());
		app.setSurname(txtfSurname.getText());
		app.setEmail(txtfEmail.getText());
		app.setTelNum(txtfTelNum.getText());
		app.setDateOfApplication(LocalDate.now());
		app.setPlaceConfirmed(false);
		app.setUcasPoints(totalPoints);
		
		applicationList.add(app);
		clearAddForm();

	}
	private void clearAddForm()
	{
		txtfForename.setText("");
		txtfSurname.setText("");
		txtfTelNum.setText("");
		txtfEmail.setText("");
		gradeDisplay.setText("");
		totalPoints = 0;
	}
	private void displayAll()
	{
		// displays the JtextArea that displays all the applications aswell as the sort buttons//
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		// sets the jtextareas text to the headings string and displays all applications using the .diplay function in my orderedList class //
		displayAll.setText(displayHeadings + applicationList.Display());
	}
	private void editSearch()
	{
		// clears screen and shows components for search screen //
		clearScreen();
		lblSearch.setVisible(true);
		txtfSearch.setVisible(true);
		btnEmailSearch.setVisible(true);
		pLeft.setVisible(true);
		pRight.setVisible(false);
	}
	private void appSearch()
	{
		// stores the input into an email varaible//
		String email = txtfSearch.getText().trim();
		// use the search function in the orderedlist class to see if the input matches an email and store it into appEdit//
		appEdit = applicationList.search(email);
		
		// if anything is stored into appEdit, it brings up the details of that applcation on screen //
		if (appEdit != null)
		{
		clearScreen();
		pRight.setVisible(true);
		txtfEditForename.setVisible(true); 	
		txtfEditSurname.setVisible(true); 		
		txtfEditTelNum.setVisible(true); 	 	
		txtfEditEmail.setVisible(true); 	
		
		lblEditDateOfApp.setVisible(true); 	 
		lblEditForename.setVisible(true); 	
		lblEditSurname.setVisible(true); 		
		lblEditTelNum.setVisible(true); 	 	
		lblEditEmail.setVisible(true); 		    
		lblEditDateOfAppShow.setVisible(true); 	 
		cmbEditQual.setVisible(true);
		cmbEditGrade.setVisible(true);
		
		btnEditSave.setVisible(true);
		btnEditCancel.setVisible(true);
		btnEditDelete.setVisible(true);
		gradeDisplay.setVisible(true);

		btnEditResetGrade.setVisible(true);
		btnEditAddGrade.setVisible(true);
		
		txtfEditForename.setText(appEdit.getForename());
		txtfEditSurname.setText(appEdit.getSurname());
		txtfEditTelNum.setText(appEdit.getTelNum());
		txtfEditEmail.setText(appEdit.getEmail());
		
		
		}
	}
	private void editSave()
	{ 
		//Get edit app details - the edit input// 
		
		appEdit.setForename(txtfEditForename.getText());
		appEdit.setSurname(txtfEditSurname.getText());
		appEdit.setEmail(txtfEditEmail.getText());
		appEdit.setTelNum(txtfEditTelNum.getText());
		appEdit.setDateOfApplication(LocalDate.now());
		appEdit.setPlaceConfirmed(false);
		appEdit.setUcasPoints(totalPoints);
		
		//Create temp instance of an application//
		Application appTemp = new Application();
		
		// Store the appEdit details into appTemp as a new application
		appTemp.setForename(txtfEditForename.getText());
		appTemp.setSurname(txtfEditSurname.getText());
		appTemp.setEmail(txtfEditEmail.getText());
		appTemp.setTelNum(txtfEditTelNum.getText());
		appTemp.setDateOfApplication(LocalDate.now());
		appTemp.setPlaceConfirmed(false);
		appTemp.setUcasPoints(totalPoints);
		
		// remove the old version of the application and add in a new application of appTemp which is the edited version of the application //
		applicationList.remove(appEdit);
		applicationList.add(appTemp);
	}
	private void editDelete(Application appFound)
	{
		// uses the .remove function i  the orderedlist class to set an instance of a found application to null, therefore removigng it
		applicationList.remove(appFound);
		JOptionPane.showMessageDialog(null, "Application Deleted");
	}
	private void fillAppList()
	{
		// Added 10 applications to fill out the applciations list a bit and make it easier to test //
		Application app1 = new Application();
		app1.setForename("Kakashi");
		app1.setSurname("Hatake");
		app1.setEmail("chidori@gmail.com");
		app1.setTelNum("7713568667");
		app1.setDateOfApplication(LocalDate.now());
		app1.setPlaceConfirmed(false);
		app1.setUcasPoints(124);
		applicationList.add(app1);
		
		Application app2 = new Application();
		app2.setForename("Jotaro");
		app2.setSurname("Kujo");
		app2.setEmail("starplatinum@gmail.com");
		app2.setTelNum("7713568667");
		app2.setDateOfApplication(LocalDate.now());
		app2.setPlaceConfirmed(false);
		app2.setUcasPoints(132);
		applicationList.add(app2);
		
		Application app3 = new Application();
		app3.setForename("Lewis");
		app3.setSurname("Patterson");
		app3.setEmail("blissful@gmail.com");
		app3.setTelNum("7713567332");
		app3.setDateOfApplication(LocalDate.now());
		app3.setPlaceConfirmed(false);
		app3.setUcasPoints(112);
		applicationList.add(app3);
		
		Application app4 = new Application();
		app4.setForename("Eren");
		app4.setSurname("Jaeger");
		app4.setEmail("jaeger@gmail.com");
		app4.setTelNum("7725337667");
		app4.setDateOfApplication(LocalDate.now());
		app4.setPlaceConfirmed(false);
		app4.setUcasPoints(80);
		applicationList.add(app4);
		
		Application app5 = new Application();
		app5.setForename("Callum");
		app5.setSurname("Maybury");
		app5.setEmail("maybury@gmail.com");
		app5.setTelNum("7712538767");
		app5.setDateOfApplication(LocalDate.now());
		app5.setPlaceConfirmed(false);
		app5.setUcasPoints(112);
		applicationList.add(app5);
		
		Application app6 = new Application();
		app6.setForename("Jack");
		app6.setSurname("O'Kane");
		app6.setEmail("jack65@gmail.com");
		app6.setTelNum("7713658667");
		app6.setDateOfApplication(LocalDate.now());
		app6.setPlaceConfirmed(false);
		app6.setUcasPoints(114);
		applicationList.add(app6);
		
		Application app7 = new Application();
		app7.setForename("Might");
		app7.setSurname("Guy");
		app7.setEmail("youth@gmail.com");
		app7.setTelNum("7713456667");
		app7.setDateOfApplication(LocalDate.now());
		app7.setPlaceConfirmed(false);
		app7.setUcasPoints(90);
		applicationList.add(app7);
		
		Application app8 = new Application();
		app8.setForename("Levi");
		app8.setSurname("Ackerman");
		app8.setEmail("ackerman@gmail.com");
		app8.setTelNum("7745563767");
		app8.setDateOfApplication(LocalDate.now());
		app8.setPlaceConfirmed(false);
		app8.setUcasPoints(125);
		applicationList.add(app8);
		
		Application app9 = new Application();
		app9.setForename("Reiner");
		app9.setSurname("Braun");
		app9.setEmail("braun@gmail.com");
		app9.setTelNum("7713488567");
		app9.setDateOfApplication(LocalDate.now());
		app9.setPlaceConfirmed(false);
		app9.setUcasPoints(100);
		applicationList.add(app9);
		
		Application app10 = new Application();
		// Setting each attribute of application //
		app10.setForename("Callum");
		app10.setSurname("McLaughlin");
		app10.setEmail("callummc1204@gmail.com");
		app10.setTelNum("7713830116");
		app10.setDateOfApplication(LocalDate.now());
		app10.setPlaceConfirmed(false);
		app10.setUcasPoints(122);
		applicationList.add(app10);
	
	}
	private void sortByPriority()
	{
		clearScreen();
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		displayAll.setText(displayHeadings + applicationList.sortByPriority());
	}
	private void sortByPoints()
	{
		clearScreen();
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		displayAll.setText(displayHeadings + applicationList.sortByUcasPoints());
	}
	private void sortSurnameAZ()
	{
		clearScreen();
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		displayAll.setText(displayHeadings + applicationList.sortBySurnameAZ());
	}
	private void sortSurnameZA()
	{
		clearScreen();
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		displayAll.setText(displayHeadings + applicationList.sortBySurnameZA());
	}
	private void sortByEmailAZ()
	{
		clearScreen();
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		displayAll.setText(displayHeadings + applicationList.sortByEmailAZ());
	}
	private void sortByEmailZA()
	{
		clearScreen();
		displayAll.setVisible(true);
		btnSortByPoints.setVisible(true);
		btnsortByEmailAZ.setVisible(true);
		btnsortByEmailZA.setVisible(true);
		btnsortByPriorityDate.setVisible(true);
		btnSurnameAZ.setVisible(true);
		btnSurnameZA.setVisible(true);
		displayAll.setText(displayHeadings + applicationList.sortByEmailZA());
	}
	private boolean validateAdd() 
	{
		// uses one string and appends on any errors that may occur with a relevant message and if the method returns false, displays the message which will have relevant error messages //
		errorMessage1 = "                       ERROR - INVALID INPUT \n"
					  + "--------------------------------------------------------------------------------\n";
		boolean result = true;
		
		if (txtfForename.getText().isBlank() == true || txtfForename.getText().trim().length() < 3 || txtfForename.getText().trim().length() > 12 || txtfForename.getText().matches("^[a-zA-Z]*+$") == false)
		{
			result = false;
			errorMessage1 += "- Please Enter a Valid Forename (Between 3-12 Characters)"+ "\n";
		}
		
		if (txtfSurname.getText().isBlank() == true || txtfSurname.getText().trim().length() < 4 || txtfSurname.getText().trim().length() > 15 || txtfForename.getText().matches("^[a-zA-Z]*+$") == false)
		{
			result = false;
			errorMessage1 += "- Please Enter a Valid Surname (Between 4-15 Characters)" + "\n";
		}
		
		if (txtfTelNum.getText().isBlank() == true || txtfTelNum.getText().trim().length() != 10 || txtfTelNum.getText().matches("^[a-zA-Z]*+$") == true)
		{
			result = false;
			errorMessage1 += "- Please Enter a Valid Phone number (10 Digits)" + "\n";
		}
		
		if (txtfEmail.getText().isBlank() == true || txtfEmail.getText().matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$") == false)
		{
			result = false;
			errorMessage1 += "- Please Enter a Valid Email Address"+ "\n";
			System.out.print("working1");
			
   }		
		if (applicationList.sameEmail(txtfEmail.getText()) == true)
		{
			result = false;
			errorMessage1 += "- Email already in use, Please enter another"+ "\n";
		}
		
		if (totalPoints == 0)
		{
			result = false;
			errorMessage1 += "- Please add at least one grade";
		}	
		
		
		return  result;
	}
	private boolean validateEdit() 
	{
		// uses one string and appends on any errors that may occur with a relevant message and if the method returns false, displays the message which will have relevant error messages //		errorMessage1 = "";
		boolean result = true;
		if (txtfEditForename.getText().isBlank() == true || txtfEditForename.getText().trim().length() < 3 || txtfEditForename.getText().trim().length() > 12)
		{
			result = false;
			errorMessage1 = "ERROR: Please Enter a Valid Forename (Between 3-12 Characters)"+ "\n";
		}
		
		if (txtfEditSurname.getText().isBlank() == true || txtfEditSurname.getText().trim().length() < 4 || txtfEditSurname.getText().trim().length() > 15)
		{
			result = false;
			errorMessage1 += "ERROR: Please Enter a Valid Surname (Between 4-15 Characters)" + "\n";
		}
		
		if (txtfEditTelNum.getText().isBlank() == true || txtfEditTelNum.getText().trim().length() != 10)
		{
			result = false;
			errorMessage1 += "ERROR: Please Enter a Valid Phone number (10 Digits)" + "\n";
		}
		
		if (txtfEditEmail.getText().isBlank() == true || txtfEditEmail.getText().matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$") == false)
		{
			result = false;
			errorMessage1 += "ERROR: Please Enter a Valid Email Address"+ "\n";
		}
		if (totalPoints == 0)
		{
			result = false;
			errorMessage1 += "ERROR: Please add at least one grade";
		}	
		return  result;
	}
	public boolean checkInt(String text)
	{
	try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) 
		{
		 return false;
		} 
	}
	private void allocatePlaces()
	{
		// allocates places based on the number entered by the user //
		allocatePlaces.setVisible(true);
		allocatePlaces.setText(displayHeadings + applicationList.displayAllocated(allocationNum));
	}
	private void validateAllocateInput()
	{
		// Ensures that the user does not attempt to allocate a blank input, a non integer and also makes sure the user doesnt try to allocate more places than there are applicants // 
		if (txtfAllocatePlaces.getText().isBlank() || checkInt(txtfAllocatePlaces.getText().trim()) == false || Integer.parseInt(txtfAllocatePlaces.getText()) > applicationList.appCount())
		{
			int temp = applicationList.appCount();
			JOptionPane.showMessageDialog(null, "ERROR: There are only " + temp + " applications \n Please enter a number between 1-"+temp);
			clearScreen();
			showAllocateScreen();
			txtfAllocatePlaces.setText("");
		}
		else
		{
			allocationNum = Integer.parseInt(txtfAllocatePlaces.getText().trim());
			allocatePlaces();
			btnAllocatePlaces.setVisible(false);
			txtfAllocatePlaces.setVisible(false);
			lblAllocate.setVisible(false);
		}
	}
	private void showAllocateScreen()
	{
		// Shows display screen for allocate places once the user has entered a valid int and clicked the "allocate" button//
		clearScreen();
		btnAllocatePlaces.setVisible(true);
		txtfAllocatePlaces.setVisible(true);
		lblAllocate.setVisible(true);
	}
	private void handleExitButton()
	{
			// Handles the exit button on form //
		// Asks user if they are usre they want to close the programme -  can't get it working properly //
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent ev)
						{
					        int x = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					        if (x == JOptionPane.YES_OPTION)
					        {
					        	ev.getWindow().dispose();	
					        }
						}
					});
	}
	private void cmbModelsInitilisation()
	{
		// Models for add grade combo boxes //
		models[0] = new DefaultComboBoxModel( new String[]{"---Select---","A Level", "AS Level", "BTEC Diploma", "Irish Leaving Certificate"});
		models[1] = new DefaultComboBoxModel( new String[]{"A*","A","B","C","D","E"});
		models[2] = new DefaultComboBoxModel( new String[]{"D*","D","M","P"});
		models[3] = new DefaultComboBoxModel( new String[]{"H1","H2","H3","H4","H5","H6"});
				
		// Models for edit grade combo boxes //
		modelsEdit[0] = new DefaultComboBoxModel( new String[]{"---Select---","A Level", "AS Level", "BTEC Diploma", "Irish Leaving Certificate"});
		modelsEdit[1] = new DefaultComboBoxModel( new String[]{"A*","A","B","C","D","E"});
		modelsEdit[2] = new DefaultComboBoxModel( new String[]{"D*","D","M","P"});
		modelsEdit[3] = new DefaultComboBoxModel( new String[]{"H1","H2","H3","H4","H5","H6"});
	}
	private void actionListenerInitilisation()
	{
		// Adding Action Listeners to each button //
		menuSystemExit.addActionListener(this);
		btnAddNext.addActionListener(this);
		btnAddGrade.addActionListener(this);
		btnResetGrade.addActionListener(this);
		btnClear.addActionListener(this);
		menuAddApp.addActionListener(this);
		cmbGrade.addActionListener(this);
		cmbEditGrade.addActionListener(this);
		cmbQual.addActionListener(this);
		cmbEditQual.addActionListener(this);
		menuDisplayApps.addActionListener(this);
		menuEditApp.addActionListener(this);
		menuAllocate.addActionListener(this);
		btnEmailSearch.addActionListener(this);
		btnEditAddGrade.addActionListener(this);
		btnSortByPoints.addActionListener(this);
		btnSurnameAZ.addActionListener(this);
		btnSurnameZA.addActionListener(this);
		btnsortByEmailAZ.addActionListener(this);
		btnsortByEmailZA.addActionListener(this);
		btnAllocatePlaces.addActionListener(this);
		btnDisplayExit.addActionListener(this);
		btnEditResetGrade.addActionListener(this);
		btnEditSave.addActionListener(this);
		btnEditDelete.addActionListener(this);
		btnEditCancel.addActionListener(this);
		menuAllocatePlaces.addActionListener(this);
		btnAllocatePlaces.addActionListener(this);
		btnsortByPriorityDate.addActionListener(this);
	}
	private void addComp()
	{
		// Adding Panels to Container //
		addComp(pLeft							,0,0,5,1,1,1);
		addComp(pRight							,6,0,3,1,1,1);
		// Adding components to add screen //
		addComp(pLeft, lblAddHeader	 			,2,0,1,1,1,1);
		addComp(pLeft, lblForename	 			,1,1,1,1,1,1);
		addComp(pLeft, lblSurname	 			,1,2,1,1,1,1);
		addComp(pLeft, lblTelNum	 			,1,3,1,1,1,1);
		addComp(pLeft, lblEmail		 			,1,4,1,1,1,1);
		addComp(pLeft, lblDateOfApp	 			,1,5,1,1,1,1);
		addComp(pLeft, lblQualType	 			,1,6,1,1,1,1);
				
		addComp(pLeft, lblEditForename	 		,1,1,1,1,1,1);
		addComp(pLeft, lblEditSurname	 		,1,2,1,1,1,1);
		addComp(pLeft, lblEditTelNum	 		,1,3,1,1,1,1);
		addComp(pLeft, lblEditEmail		 		,1,4,1,1,1,1);
		addComp(pLeft, lblEditDateOfApp	 		,1,5,1,1,1,1);
		addComp(pLeft, lblEditQualType	 		,1,6,1,1,1,1);
				
		addComp(pLeft, txtfForename	 			,2,1,2,1,1,1);
		addComp(pLeft, txtfSurname	 			,2,2,2,1,1,1);
		addComp(pLeft, txtfTelNum	 			,2,3,2,1,1,1);
		addComp(pLeft, txtfEmail	    		,2,4,2,1,1,1);
		addComp(pLeft, lblDateOfAppShow 		,2,5,2,1,1,1);
		addComp(pLeft, cmbQual					,2,6,1,1,1,1);
		addComp(pLeft, cmbGrade		 			,3,6,1,1,1,1);
				
		addComp(pLeft, txtfEditForename	 		,2,1,2,1,1,1);
		addComp(pLeft, txtfEditSurname	 		,2,2,2,1,1,1);
		addComp(pLeft, txtfEditTelNum	 		,2,3,2,1,1,1);
		addComp(pLeft, txtfEditEmail	    	,2,4,2,1,1,1);
		addComp(pLeft, lblEditDateOfAppShow 	,2,5,2,1,1,1);
		addComp(pLeft, cmbEditQual				,2,7,1,1,1,1);
		addComp(pLeft, cmbEditGrade		 		,3,7,1,1,1,1);
				
		addComp(pLeft, btnResetGrade 			,2,7,1,1,0,1);
		addComp(pLeft, btnAddGrade	 			,3,7,1,1,0,1);
				
		addComp(pLeft, btnEditResetGrade 		,2,8,1,1,0,1);
		addComp(pLeft, btnEditAddGrade	 		,3,8,1,1,0,1);
				
		addComp(pLeft, btnsortByPriorityDate	,1,9,1,1,2,2);
		addComp(pLeft, btnSurnameAZ				,2,9,1,1,2,2);
		addComp(pLeft, btnSurnameZA				,3,9,1,1,2,2);
		addComp(pLeft, btnsortByEmailAZ			,4,9,1,1,2,2);
		addComp(pLeft, btnsortByEmailZA			,5,9,1,1,2,2);
		addComp(pLeft, btnSortByPoints			,6,9,1,1,2,2);
		
		
		addComp(pLeft, btnDisplayExit			,8,9,1,1,2,2);
				
				
		addComp(pLeft,  btnClear				,2,9,1,1,0,1);
		addComp(pLeft,  btnAddNext				,3,9,1,1,0,1);
		addComp(pRight, gradeDisplay 			,0,0,1,1,1,1);
		addComp(pRight, gradeEditDisplay 		,0,0,1,1,1,1);
		addComp(pRight, allocatePlaces 			,0,0,1,1,1,1);
			
				
		addComp(pLeft, 	displayAll				,1,1,8,8,8,8);
				
		addComp(pLeft, 	lblSearch				,1,1,1,1,0,0);
		addComp(pLeft, 	txtfSearch	 			,1,2,1,1,0,0);
		addComp(pLeft, 	btnEmailSearch			,1,9,1,1,0,0);
				
		addComp(pLeft, btnEditDelete 			,1,9,1,1,0,1);
		addComp(pLeft, btnEditCancel 			,2,9,1,1,0,1);
		addComp(pLeft, btnEditSave 				,3,9,1,1,0,1);
				
		addComp(pLeft, lblAllocate				,1,1,1,1,0,0);
		addComp(pLeft, txtfAllocatePlaces	 	,1,2,1,1,0,0);
		addComp(pLeft, btnAllocatePlaces 		,1,9,1,1,0,0);
		addComp(pLeft, allocatePlaces			,1,1,8,8,8,8);
	}


}

