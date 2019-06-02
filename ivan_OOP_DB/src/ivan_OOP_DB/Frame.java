package ivan_OOP_DB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import utility.tables.DepartmentActions;
import utility.tables.EmployeeActions;
import utility.tables.JobActions;
import utility.tables.SearchActions;

/*Enumeration containing the constants for the getComboBoxValues allowed parameters*/
enum Parameters {
	JOB_TITLES, MANAGER_EMAILS, LOCATION_NAMES, DEPARTMENT_NAMES, JOB_DEPARTMENT_NAMES, JOB_LOCATION_NAMES, DEPT_LOCATION_NAMES;
}

public class Frame extends JFrame {
	static DBUtil dbUtil = new DBUtil();

	// main panels/ tabs
	JTabbedPane tabbedPane = new JTabbedPane();
	JPanel employeePanel = new JPanel();
	JPanel jobAndDepartmentPanel = new JPanel();
	JPanel searchPanel = new JPanel();

	// Employee components
	static JTable employeeTable = new JTable();
	JScrollPane employeeTableScroller = new JScrollPane(employeeTable);

	JPanel employeeUpperPanel = new JPanel();
	JPanel employeeMiddlePanel = new JPanel();
	JPanel employeeLowerPanel = new JPanel();
	JPanel employeeButtonsPanel = new JPanel();
	JPanel employeeStatusPanel = new JPanel();

	JButton addEmployeeBtn = new JButton("Add");
	JButton editEmployeeBtn = new JButton("Edit");
	JButton deleteEmployeeBtn = new JButton("Delete");

	JLabel firstNameLabel = new JLabel("First Name:");
	JLabel lastNameLabel = new JLabel("Last Name:");
	JLabel emailLabel = new JLabel("Email:");
	JLabel phoneLabel = new JLabel("Phone:");
	JLabel hireDateLabel = new JLabel("Hire Date:");
	JLabel employeeJobTitleLable = new JLabel("Job title:");
	JLabel salaryLabel = new JLabel("Salary:");
	JLabel managerEmailLabel = new JLabel("Manager email:");
	JLabel employeeLocationLabel = new JLabel("Department location:");
	JLabel employeeDepartmentNameLabel = new JLabel("Department name:");
	static JLabel employeeStatusLabel = new JLabel();

	static JTextField firstNameTextField = new JTextField();
	static JTextField lastNameTextField = new JTextField();
	static JTextField emailTextField = new JTextField();
	static JTextField phoneTextField = new JTextField();
	static JTextField hireDateTextField = new JTextField();
	static JComboBox employeeJobTitleComboBox = new JComboBox(dbUtil.getComboBoxValues(Parameters.JOB_TITLES));
	static JTextField salaryTextField = new JTextField();
	static JComboBox managerEmailComboBox = new JComboBox(dbUtil.getComboBoxValues(Parameters.MANAGER_EMAILS));
	static JComboBox employeeLocationComboBox = new JComboBox(dbUtil.getComboBoxValues(Parameters.LOCATION_NAMES));
	static JComboBox employeeDepartmentNameComboBox = new JComboBox(
			dbUtil.getComboBoxValues(Parameters.DEPARTMENT_NAMES));

	static JTextField[] employeeTextFields = { firstNameTextField, lastNameTextField, emailTextField, phoneTextField,
			hireDateTextField, salaryTextField };

	static JComboBox[] employeeComboBoxes = { employeeJobTitleComboBox, managerEmailComboBox, employeeLocationComboBox,
			employeeDepartmentNameComboBox };

	// Job components
	static JTable jobTable = new JTable();
	JScrollPane jobTableScroller = new JScrollPane(jobTable);

	JPanel jobUpperPanel = new JPanel();
	JPanel jobMiddlePanel = new JPanel();
	JPanel jobLowerPanel = new JPanel();
	JPanel jobButtonsPanel = new JPanel();
	JPanel jobStatusPanel = new JPanel();

	JButton addJobBtn = new JButton("Add");
	JButton editJobBtn = new JButton("Edit");
	JButton deleteJobBtn = new JButton("Delete");

	JLabel jobTitleLabel = new JLabel("Job title:");
	JLabel jobMinSalaryLabel = new JLabel("Min salary:");
	JLabel jobMaxSalaryLabel = new JLabel("Max salary:");
	JLabel jobDepartmentNameLabel = new JLabel("Department name:");
	JLabel jobDepartmentLocationLabel = new JLabel("Department location:");
	static JLabel jobStatusLabel = new JLabel();

	static JTextField jobTitleTextField = new JTextField();
	static JTextField jobMinSalaryTextField = new JTextField();
	static JTextField jobMaxSalaryTextField = new JTextField();
	static JComboBox jobDepartmentNameComboBox = new JComboBox(
			dbUtil.getComboBoxValues(Parameters.JOB_DEPARTMENT_NAMES));
	static JComboBox jobDepartmentLocationComboBox = new JComboBox(
			dbUtil.getComboBoxValues(Parameters.JOB_LOCATION_NAMES));

	static JTextField[] jobTextFields = { jobTitleTextField, jobMinSalaryTextField, jobMaxSalaryTextField };

	static JComboBox[] jobComboBoxes = { jobDepartmentNameComboBox, jobDepartmentLocationComboBox };

	// Department components
	static JTable departmentTable = new JTable();
	JScrollPane departmentTableScroller = new JScrollPane(departmentTable);

	JPanel departmentUpperPanel = new JPanel();
	JPanel departmentMiddlePanel = new JPanel();
	JPanel departmentLowerPanel = new JPanel();
	JPanel departmentButtonsPanel = new JPanel();
	JPanel departmentStatusPanel = new JPanel();

	JButton addDepartmentBtn = new JButton("Add");
	JButton editDepartmentBtn = new JButton("Edit");
	JButton deleteDepartmentBtn = new JButton("Delete");

	JLabel departmentNameLabel = new JLabel("Department name:");
	JLabel departmentDateFoundedLabel = new JLabel("Date founded:");
	JLabel departmentLocationLabel = new JLabel("Location:");
	static JLabel departmentStatusLabel = new JLabel();

	static JTextField departmentNameTextField = new JTextField();
	static JTextField departmentDateFoundedTextField = new JTextField();
	static JComboBox departmentLocationComboBox = new JComboBox(dbUtil.getComboBoxValues(Parameters.DEPT_LOCATION_NAMES));

	static JTextField[] departmentTextFields = { departmentNameTextField, departmentDateFoundedTextField };
	static JComboBox[] departmentComboBoxes = {departmentLocationComboBox};
	
	/* Search components */

	// Employee search
	static JTable employeeSearchTable = new JTable();
	JScrollPane employeeSearchTableScroller = new JScrollPane(employeeSearchTable);

	JPanel employeeSearchUpperPanel = new JPanel();
	JPanel employeeSearchMiddlePanel = new JPanel();
	JPanel employeeSearchLowerPanel = new JPanel();
	JPanel employeeSearchBtnPanel = new JPanel();
	JPanel employeeSearchStatusPanel = new JPanel();

	JButton employeeSearchBtn = new JButton("Search Employees");

	JLabel fromDateLabel = new JLabel("Hire date (from):");
	JLabel toDateLabel = new JLabel("Hire date (to):");
	JLabel fromDepartmentLabel = new JLabel("From department:");
	JLabel firstNameLikeLabel = new JLabel("First name like:");
	JLabel employeeFromLocationLabel = new JLabel("From location:");
	static JLabel employeeSearchStatusLabel = new JLabel();

	static JTextField fromDateTextField = new JTextField();
	static JTextField toDateTextField = new JTextField();
	static JTextField fromDepartmentTextField = new JTextField();
	static JTextField firstNameLikeTextField = new JTextField();
	static JTextField employeeFromLocationTextField = new JTextField();

	// Department and jobs search
	static JTable jobAndDepartmentSearchTable = new JTable();
	JScrollPane jobAndDepartmentSearchTableScroller = new JScrollPane(jobAndDepartmentSearchTable);

	JPanel jobAndDepartmentSearchUpperPanel = new JPanel();
	JPanel jobAndDepartmentSearchMiddlePanel = new JPanel();
	JPanel jobAndDepartmentSearchLowerPanel = new JPanel();
	JPanel jobSearchBtnPanel = new JPanel();
	JPanel departmentSearchBtnPanel = new JPanel();
	JPanel jobSearchStatusPanel = new JPanel();
	JPanel departmentSearchStatusPanel = new JPanel();

	JButton jobSearchBtn = new JButton("Search Jobs");
	JButton departmentSearchBtn = new JButton("Search Departments");

	JLabel departmentSearchName = new JLabel("Department name:");
	JLabel dateFoundedAfter = new JLabel("Founded after:");
	JLabel jobSearchTitle = new JLabel("Job title:");
	JLabel jobSearchMinSalary = new JLabel("Min salary:");
	JLabel jobSearchMaxSalary = new JLabel("Max salary:");
	static JLabel jobSearchStatusLabel = new JLabel();
	static JLabel departmentSearchStatusLabel = new JLabel();

	static JTextField departmentSearchNameTextField = new JTextField();
	static JTextField dateFoundedAfterTextField = new JTextField();
	static JTextField jobSearchTitleTextField = new JTextField();
	static JTextField jobSearchMinSalaryTextField = new JTextField();
	static JTextField jobSearchMaxSalaryTextField = new JTextField();

	static JTextField[] employeeSearchTextFields = { fromDateTextField, toDateTextField, fromDepartmentTextField,
			firstNameLikeTextField, employeeFromLocationTextField };

	static JTextField[] departmentSearchTextFields = { departmentSearchNameTextField, dateFoundedAfterTextField };

	static JTextField[] jobSearchTextFields = { jobSearchTitleTextField, jobSearchMinSalaryTextField,
			jobSearchMaxSalaryTextField };

	static JLabel[] searchStatusLabels = { employeeSearchStatusLabel, departmentSearchStatusLabel,
			jobSearchStatusLabel };

	// Every combo box ever
	static JComboBox[][] comboBoxes = { employeeComboBoxes, jobComboBoxes, departmentComboBoxes };

	// Utility classes
	EmployeeActions employeeActions = new EmployeeActions();
	JobActions jobActions = new JobActions();
	DepartmentActions departmentActions = new DepartmentActions();
	SearchActions searchActions = new SearchActions();

	// Constructor
	public Frame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);

		employeePanel.setLayout(new GridLayout(3, 1));
		employeePanel.add(employeeUpperPanel);
		employeePanel.add(employeeMiddlePanel);
		employeePanel.add(employeeLowerPanel);

		jobAndDepartmentPanel.setLayout(new GridLayout(6, 1));
		jobAndDepartmentPanel.add(jobUpperPanel);
		jobAndDepartmentPanel.add(jobMiddlePanel);
		jobAndDepartmentPanel.add(jobLowerPanel);
		jobAndDepartmentPanel.add(departmentUpperPanel);
		jobAndDepartmentPanel.add(departmentMiddlePanel);
		jobAndDepartmentPanel.add(departmentLowerPanel);

		searchPanel.setLayout(new GridLayout(6, 1));
		searchPanel.add(employeeSearchUpperPanel);
		searchPanel.add(employeeSearchMiddlePanel);
		searchPanel.add(employeeSearchLowerPanel);
		searchPanel.add(jobAndDepartmentSearchUpperPanel);
		searchPanel.add(jobAndDepartmentSearchMiddlePanel);
		searchPanel.add(jobAndDepartmentSearchLowerPanel);

		tabbedPane.addTab("Employees", employeePanel);
		tabbedPane.addTab("Jobs and Departments", jobAndDepartmentPanel);
		tabbedPane.addTab("Search", searchPanel);
		this.add(tabbedPane);
		this.setResizable(false);

		/* Employee containers */

		// upperPanel contents
		employeeUpperPanel.setLayout(new GridLayout(10, 2));
		employeeUpperPanel.setBackground(Color.LIGHT_GRAY);
		firstNameTextField.setBackground(Color.LIGHT_GRAY);
		lastNameTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBackground(Color.LIGHT_GRAY);
		phoneTextField.setBackground(Color.LIGHT_GRAY);
		hireDateTextField.setBackground(Color.LIGHT_GRAY);
		employeeJobTitleComboBox.setBackground(Color.LIGHT_GRAY);
		salaryTextField.setBackground(Color.LIGHT_GRAY);
		managerEmailComboBox.setBackground(Color.LIGHT_GRAY);
		employeeLocationComboBox.setBackground(Color.LIGHT_GRAY);
		employeeDepartmentNameComboBox.setBackground(Color.LIGHT_GRAY);

		employeeUpperPanel.add(firstNameLabel);
		employeeUpperPanel.add(firstNameTextField);
		employeeUpperPanel.add(lastNameLabel);
		employeeUpperPanel.add(lastNameTextField);
		employeeUpperPanel.add(emailLabel);
		employeeUpperPanel.add(emailTextField);
		employeeUpperPanel.add(phoneLabel);
		employeeUpperPanel.add(phoneTextField);
		employeeUpperPanel.add(hireDateLabel);
		employeeUpperPanel.add(hireDateTextField);
		employeeUpperPanel.add(employeeJobTitleLable);
		employeeUpperPanel.add(employeeJobTitleComboBox);
		employeeUpperPanel.add(salaryLabel);
		employeeUpperPanel.add(salaryTextField);
		employeeUpperPanel.add(managerEmailLabel);
		employeeUpperPanel.add(managerEmailComboBox);
		employeeUpperPanel.add(employeeLocationLabel);
		employeeUpperPanel.add(employeeLocationComboBox);
		employeeUpperPanel.add(employeeDepartmentNameLabel);
		employeeUpperPanel.add(employeeDepartmentNameComboBox);

		// middlePanel contents
		employeeMiddlePanel.setLayout(new GridLayout(2, 1));
		addEmployeeBtn.setBackground(Color.WHITE);
		deleteEmployeeBtn.setBackground(Color.WHITE);
		editEmployeeBtn.setBackground(Color.WHITE);
		employeeButtonsPanel.setBackground(Color.LIGHT_GRAY);
		employeeStatusPanel.setBackground(Color.LIGHT_GRAY);

		employeeMiddlePanel.add(employeeButtonsPanel);
		employeeMiddlePanel.add(employeeStatusPanel);
		employeeButtonsPanel.add(addEmployeeBtn);
		employeeButtonsPanel.add(deleteEmployeeBtn);
		employeeButtonsPanel.add(editEmployeeBtn);
		addEmployeeBtn.addActionListener(employeeActions.getAddEmployeeAction());
		deleteEmployeeBtn.addActionListener(employeeActions.getDeleteEmployeeAction());
		editEmployeeBtn.addActionListener(employeeActions.getEditEmployeeAction());
		employeeStatusLabel.setForeground(Color.WHITE);
		employeeStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		employeeStatusPanel.add(employeeStatusLabel);

		// lowerPanel contents
		employeeLowerPanel.setBackground(Color.LIGHT_GRAY);
		employeeTable.setBackground(Color.LIGHT_GRAY);
		employeeTableScroller.setPreferredSize(new Dimension(650, 200));
		employeeLowerPanel.add(employeeTableScroller);
		employeeTable.setModel(dbUtil.getDbContent("employees"));
		employeeTable.removeColumn(employeeTable.getColumnModel().getColumn(0));
		employeeTable.addMouseListener(employeeActions.getTouchEmployeeTable());

		/* Job containers */

		// upperPanel contents
		jobUpperPanel.setLayout(new GridLayout(5, 2));
		jobUpperPanel.setBackground(Color.ORANGE);
		jobTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		jobTitleTextField.setFont(new Font("SansSerif", Font.BOLD, 10));
		jobTitleTextField.setBackground(Color.YELLOW);
		jobMinSalaryLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		jobMinSalaryTextField.setFont(new Font("SansSerif", Font.BOLD, 10));
		jobMinSalaryTextField.setBackground(Color.YELLOW);
		jobMaxSalaryLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		jobMaxSalaryTextField.setFont(new Font("SansSerif", Font.BOLD, 10));
		jobMaxSalaryTextField.setBackground(Color.YELLOW);
		jobDepartmentNameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		jobDepartmentNameComboBox.setFont(new Font("SansSerif", Font.BOLD, 10));
		jobDepartmentNameComboBox.setBackground(Color.YELLOW);
		jobDepartmentLocationLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		jobDepartmentLocationComboBox.setFont(new Font("SansSerif", Font.BOLD, 10));
		jobDepartmentLocationComboBox.setBackground(Color.YELLOW);
		jobUpperPanel.add(jobTitleLabel);
		jobUpperPanel.add(jobTitleTextField);
		jobUpperPanel.add(jobMinSalaryLabel);
		jobUpperPanel.add(jobMinSalaryTextField);
		jobUpperPanel.add(jobMaxSalaryLabel);
		jobUpperPanel.add(jobMaxSalaryTextField);
		jobUpperPanel.add(jobDepartmentNameLabel);
		jobUpperPanel.add(jobDepartmentNameComboBox);
		jobUpperPanel.add(jobDepartmentLocationLabel);
		jobUpperPanel.add(jobDepartmentLocationComboBox);

		// middlePanel contents
		jobMiddlePanel.setLayout(new GridLayout(2, 1));
		jobButtonsPanel.setBackground(Color.ORANGE);
		jobStatusPanel.setBackground(Color.ORANGE);
		addJobBtn.setBackground(Color.YELLOW);
		deleteJobBtn.setBackground(Color.YELLOW);
		editJobBtn.setBackground(Color.YELLOW);
		jobMiddlePanel.add(jobButtonsPanel);
		jobMiddlePanel.add(jobStatusPanel);
		jobButtonsPanel.add(addJobBtn);
		jobButtonsPanel.add(deleteJobBtn);
		jobButtonsPanel.add(editJobBtn);
		addJobBtn.addActionListener(jobActions.getAddJobAction());
		deleteJobBtn.addActionListener(jobActions.getDeleteJobAction());
		editJobBtn.addActionListener(jobActions.getEditJobAction());
		jobStatusLabel.setForeground(Color.RED);
		jobStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		jobStatusPanel.add(jobStatusLabel);

		// lowerPanel contents
		jobLowerPanel.setBackground(Color.ORANGE);
		jobTable.setBackground(Color.ORANGE);
		jobTableScroller.setPreferredSize(new Dimension(650, 100));
		jobLowerPanel.add(jobTableScroller);
		jobTable.setModel(dbUtil.getDbContent("jobs"));
		jobTable.removeColumn(jobTable.getColumnModel().getColumn(0));
		jobTable.addMouseListener(jobActions.getTouchJobTable());

		/* Department Containers */

		// upperPanel contents
		departmentUpperPanel.setLayout(new GridLayout(3, 2));
		departmentUpperPanel.setBackground(Color.GREEN);
		departmentNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentNameTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentDateFoundedLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentDateFoundedTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentLocationLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentLocationComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentNameTextField.setBackground(Color.CYAN);
		departmentDateFoundedTextField.setBackground(Color.CYAN);
		departmentLocationComboBox.setBackground(Color.CYAN);
		departmentUpperPanel.add(departmentNameLabel);
		departmentUpperPanel.add(departmentNameTextField);
		departmentUpperPanel.add(departmentDateFoundedLabel);
		departmentUpperPanel.add(departmentDateFoundedTextField);
		departmentUpperPanel.add(departmentLocationLabel);
		departmentUpperPanel.add(departmentLocationComboBox);

		// middlePanel contents
		departmentMiddlePanel.setLayout(new GridLayout(2, 1));
		departmentMiddlePanel.add(departmentButtonsPanel);
		departmentMiddlePanel.add(departmentStatusPanel);
		departmentButtonsPanel.setBackground(Color.GREEN);
		departmentStatusPanel.setBackground(Color.GREEN);
		addDepartmentBtn.setBackground(Color.CYAN);
		deleteDepartmentBtn.setBackground(Color.CYAN);
		editDepartmentBtn.setBackground(Color.CYAN);
		departmentButtonsPanel.add(addDepartmentBtn);
		departmentButtonsPanel.add(deleteDepartmentBtn);
		departmentButtonsPanel.add(editDepartmentBtn);
		addDepartmentBtn.addActionListener(departmentActions.getAddDepartmentAction());
		deleteDepartmentBtn.addActionListener(departmentActions.getDeleteDepartmentAction());
		editDepartmentBtn.addActionListener(departmentActions.getEditDepartmentAction());
		departmentStatusLabel.setForeground(Color.RED);
		departmentStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		departmentStatusPanel.add(departmentStatusLabel);

		// lowerPanel contents
		departmentLowerPanel.setBackground(Color.GREEN);
		departmentTable.setBackground(Color.GREEN);
		departmentTableScroller.setPreferredSize(new Dimension(650, 100));
		departmentLowerPanel.add(departmentTableScroller);
		departmentTable.setModel(dbUtil.getDbContent("departments"));
		departmentTable.removeColumn(departmentTable.getColumnModel().getColumn(0));
		departmentTable.addMouseListener(departmentActions.getTouchDepartmentTable());

		/* Search containers */

		// Employee search criteria
		employeeSearchUpperPanel.setLayout(new GridLayout(5, 2));
		employeeSearchUpperPanel.add(fromDateLabel);
		employeeSearchUpperPanel.add(fromDateTextField);
		employeeSearchUpperPanel.add(toDateLabel);
		employeeSearchUpperPanel.add(toDateTextField);
		employeeSearchUpperPanel.add(fromDepartmentLabel);
		employeeSearchUpperPanel.add(fromDepartmentTextField);
		employeeSearchUpperPanel.add(firstNameLikeLabel);
		employeeSearchUpperPanel.add(firstNameLikeTextField);
		employeeSearchUpperPanel.add(employeeFromLocationLabel);
		employeeSearchUpperPanel.add(employeeFromLocationTextField);

		// Employee search status
		employeeSearchMiddlePanel.setLayout(new GridLayout(2, 1));
		employeeSearchMiddlePanel.add(employeeSearchBtnPanel);
		employeeSearchBtnPanel.add(employeeSearchBtn);
		employeeSearchBtn.addActionListener(searchActions.getSearchEmployeeAction());
		employeeSearchMiddlePanel.add(employeeSearchStatusPanel);
		employeeSearchStatusLabel.setForeground(Color.RED);
		employeeSearchStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		employeeSearchStatusPanel.add(employeeSearchStatusLabel);

		// Employee search result
		employeeSearchTableScroller.setPreferredSize(new Dimension(650, 100));
		employeeSearchLowerPanel.add(employeeSearchTableScroller);

		// Department search criteria
		jobAndDepartmentSearchUpperPanel.setLayout(new GridLayout(3, 2));
		jobAndDepartmentSearchUpperPanel.add(departmentSearchName);
		jobAndDepartmentSearchUpperPanel.add(departmentSearchNameTextField);
		jobAndDepartmentSearchUpperPanel.add(dateFoundedAfter);
		jobAndDepartmentSearchUpperPanel.add(dateFoundedAfterTextField);
		jobAndDepartmentSearchUpperPanel.add(departmentSearchBtnPanel);
		departmentSearchBtnPanel.add(departmentSearchBtn);
		departmentSearchBtn.addActionListener(searchActions.getSearchDepartmentAction());
		jobAndDepartmentSearchUpperPanel.add(departmentSearchStatusPanel);
		departmentSearchStatusPanel.add(departmentSearchStatusLabel);
		departmentSearchStatusLabel.setForeground(Color.RED);
		departmentSearchStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Job search criteria
		jobAndDepartmentSearchMiddlePanel.setLayout(new GridLayout(4, 2));
		jobAndDepartmentSearchMiddlePanel.add(jobSearchTitle);
		jobAndDepartmentSearchMiddlePanel.add(jobSearchTitleTextField);
		jobAndDepartmentSearchMiddlePanel.add(jobSearchMinSalary);
		jobAndDepartmentSearchMiddlePanel.add(jobSearchMinSalaryTextField);
		jobAndDepartmentSearchMiddlePanel.add(jobSearchMaxSalary);
		jobAndDepartmentSearchMiddlePanel.add(jobSearchMaxSalaryTextField);
		jobAndDepartmentSearchMiddlePanel.add(jobSearchBtnPanel);
		jobSearchBtnPanel.add(jobSearchBtn);
		jobSearchBtn.setPreferredSize(new Dimension(150, 20));
		jobSearchBtn.addActionListener(searchActions.getSearchJobAction());
		jobAndDepartmentSearchMiddlePanel.add(jobSearchStatusPanel);
		jobSearchStatusPanel.add(jobSearchStatusLabel);
		jobSearchStatusLabel.setForeground(Color.RED);
		jobSearchStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Job and department search result table
		jobAndDepartmentSearchTableScroller.setPreferredSize(new Dimension(650, 100));
		jobAndDepartmentSearchLowerPanel.add(jobAndDepartmentSearchTableScroller);
	}

	/*
	 * Method to assign new values to all of the combo boxes after there have been
	 * changes to the table in the database, which the combo box depends on
	 */
	public static void setComboBoxValues() {
		for (int i = 0, e = 0; i < comboBoxes.length; i++) {
			for (int j = 0; j < comboBoxes[i].length; j++) {
				comboBoxes[i][j].setModel(new DefaultComboBoxModel(dbUtil.getComboBoxValues(Parameters.values()[e])));
				e++;
			}
		}
	}

	public static JTable getEmployeeTable() {
		return employeeTable;
	}

	public static JTable getJobTable() {
		return jobTable;
	}

	public static JTable getDepartmentTable() {
		return departmentTable;
	}

	public static JLabel getDepartmentStatusLabel() {
		return departmentStatusLabel;
	}

	public static JTextField[] getDepartmentTextFields() {
		return departmentTextFields;
	}

	public static JComboBox[] getDepartmentComboBoxes() {
		return departmentComboBoxes;
	}

	public static JLabel getJobStatusLabel() {
		return jobStatusLabel;
	}

	public static JTextField[] getJobTextFields() {
		return jobTextFields;
	}

	public static JComboBox[] getJobComboBoxes() {
		return jobComboBoxes;
	}

	public static JLabel getEmployeeStatusLabel() {
		return employeeStatusLabel;
	}

	public static JTextField[] getEmployeeTextFields() {
		return employeeTextFields;
	}

	public static JComboBox[] getEmployeeComboBoxes() {
		return employeeComboBoxes;
	}

	public static JTable getEmployeeSearchTable() {
		return employeeSearchTable;
	}

	public static JTable getJobAndDepartmentSearchTable() {
		return jobAndDepartmentSearchTable;
	}

	public static JTextField[] getEmployeeSearchTextFields() {
		return employeeSearchTextFields;
	}

	public static JTextField[] getDepartmentSearchTextFields() {
		return departmentSearchTextFields;
	}

	public static JTextField[] getJobSearchTextFields() {
		return jobSearchTextFields;
	}

	public static JLabel[] getSearchStatusLabels() {
		return searchStatusLabels;
	}

}
