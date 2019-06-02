package utility.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import ivan_OOP_DB.Frame;

public class EmployeeActions extends Actions {
	int employeeId = 0;
	String selectedEmployee = null;

	public TouchEmployeeTable getTouchEmployeeTable() {
		return new TouchEmployeeTable();
	}

	public AddEmployeeAction getAddEmployeeAction() {
		return new AddEmployeeAction();
	}

	public EditEmployeeAction getEditEmployeeAction() {
		return new EditEmployeeAction();
	}

	public DeleteEmployeeAction getDeleteEmployeeAction() {
		return new DeleteEmployeeAction();
	}

	/*
	 * What happens when you click twice or more a row in the Employees table -
	 * set the text fields and combo boxes to their respective values in the clicked twice row
	 */
	class TouchEmployeeTable implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				int row = Frame.getEmployeeTable().getSelectedRow();
				for (int i = 0, j = 0, k = 0; i < (Frame.getEmployeeTextFields().length
						+ Frame.getEmployeeComboBoxes().length); i++) {
					if (Frame.getEmployeeTable().getValueAt(row, i) == null) {
						Frame.getEmployeeTextFields()[k].setText("");
						k++;
						continue;
					}
					if (i == 5 || i == 7 || i == 8 || i == 9) {
						Frame.getEmployeeComboBoxes()[j].setSelectedItem(Frame.getEmployeeTable().getValueAt(row, i));
						j++;
						continue;
					}
					Frame.getEmployeeTextFields()[k].setText(Frame.getEmployeeTable().getValueAt(row, i).toString());
					k++;
				}
				Object idObject = Frame.getEmployeeTable().getModel().getValueAt(row, 0);
				employeeId = Integer.parseInt((idObject.toString()));
				selectedEmployee = Frame.getEmployeeTable().getValueAt(row, 0).toString() + " "
						+ Frame.getEmployeeTable().getValueAt(row, 1).toString();
				Frame.getEmployeeStatusLabel().setText("Current employee: " + selectedEmployee);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class EditEmployeeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String firstName = Frame.getEmployeeTextFields()[0].getText();
				String lastName = Frame.getEmployeeTextFields()[1].getText();
				String email = Frame.getEmployeeTextFields()[2].getText();
				String phone = Frame.getEmployeeTextFields()[3].getText();
				String hireDate = Frame.getEmployeeTextFields()[4].getText();
				String jobTitle = Frame.getEmployeeComboBoxes()[0].getSelectedItem().toString();
				float salary = Float.parseFloat(Frame.getEmployeeTextFields()[5].getText());
				String managerEmail = Frame.getEmployeeComboBoxes()[1].getSelectedItem().toString();
				String employeeLocation = Frame.getEmployeeComboBoxes()[2].getSelectedItem().toString();
				String departmentName = Frame.getEmployeeComboBoxes()[3].getSelectedItem().toString();

				conn = dbUtil.getConnection();
				String sql = "update employees set first_name=?, last_name=?, email=?, phone=?, hire_date=?,"
						+ "job_id = (select job_id from jobs where job_title=? and "
						+ "department_id=(select department_id from departments where location_id="
						+ "(select location_id from locations where location_name=?) and department_name=?)), salary=?,"
						+ "manager_id = (select manager_id from employees where email=?) where employee_id=?";

				statement = conn.prepareStatement(sql);
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				statement.setString(3, email);
				statement.setString(4, phone);
				statement.setString(5, hireDate);
				statement.setString(6, jobTitle);
				statement.setString(7, employeeLocation);
				statement.setString(8, departmentName);
				statement.setFloat(9, salary);
				statement.setString(10, managerEmail);
				statement.setInt(11, employeeId);
				statement.execute();
				clearEmployeeFields();
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
				Frame.getEmployeeStatusLabel().setText("Employee is updated!");
				employeeId = 0;
			} catch (SQLException e) {
				Frame.getEmployeeStatusLabel().setText("Invalid value! Current employee: " + selectedEmployee);
				e.printStackTrace();
			} catch (Exception e1) {
				Frame.getEmployeeStatusLabel().setText("Invalid value! Current employee: " + selectedEmployee);
				e1.printStackTrace();
			}

		}

	}

	class DeleteEmployeeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn = dbUtil.getConnection();
			String sql = "delete from employees where employee_id=?";
			try {
				if (employeeId == 0)
					throw new Exception("No row selected");
				statement = conn.prepareStatement(sql);
				statement.setInt(1, employeeId);
				statement.execute();
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
				Frame.getEmployeeStatusLabel().setText("Row with name " + selectedEmployee + " was deleted!");
				employeeId = 0;
				clearEmployeeFields();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getEmployeeStatusLabel().setText("Please select a row to delete!");
				e.printStackTrace();
			} catch (Exception e) {
				Frame.getEmployeeStatusLabel().setText("Please select a row to delete!");
				e.printStackTrace();
			}
		}

	}

	class AddEmployeeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String firstName = Frame.getEmployeeTextFields()[0].getText();
				String lastName = Frame.getEmployeeTextFields()[1].getText();
				String email = Frame.getEmployeeTextFields()[2].getText();
				String phone = Frame.getEmployeeTextFields()[3].getText();
				String hireDate = Frame.getEmployeeTextFields()[4].getText();
				String jobTitle = Frame.getEmployeeComboBoxes()[0].getSelectedItem().toString();
				float salary = Float.parseFloat(Frame.getEmployeeTextFields()[5].getText());
				String managerEmail = Frame.getEmployeeComboBoxes()[1].getSelectedItem().toString();
				String employeeLocation = Frame.getEmployeeComboBoxes()[2].getSelectedItem().toString();
				String departmentName = Frame.getEmployeeComboBoxes()[3].getSelectedItem().toString();

				String sql = "insert into employees values(null,?,?,?,?,?,"
						+ "(select job_id from jobs where job_title=? and "
						+ "department_id=(select department_id from departments where location_id="
						+ "(select location_id from locations where location_name=?) and department_name=?)),?,"
						+ "(select employee_id from employees where email=?))";
				conn = dbUtil.getConnection();
				statement = conn.prepareStatement(sql);
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				statement.setString(3, email);
				statement.setString(4, phone);
				statement.setString(5, hireDate);
				statement.setString(6, jobTitle);
				statement.setString(7, employeeLocation);
				statement.setString(8, departmentName);
				statement.setFloat(9, salary);
				statement.setString(10, managerEmail);

				statement.execute();
				clearEmployeeFields();
				Frame.getEmployeeStatusLabel().setText("New row is added");
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
			} catch (SQLException e1) {
				Frame.getEmployeeStatusLabel().setText("Invalid value! Current employee: " + selectedEmployee);
				e1.printStackTrace();
			} catch (Exception e2) {
				Frame.getEmployeeStatusLabel().setText("Invalid value! Current employee: " + selectedEmployee);
				e2.printStackTrace();
			}
		}
	}

	public void clearEmployeeFields() {
		for (int i = 0; i < Frame.getEmployeeTextFields().length; i++)
			Frame.getEmployeeTextFields()[i].setText("");
		for (int i = 0; i < Frame.getEmployeeComboBoxes().length; i++)
			Frame.getEmployeeComboBoxes()[i].setSelectedIndex(0);
	}
}
