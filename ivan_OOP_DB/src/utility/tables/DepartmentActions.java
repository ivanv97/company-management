package utility.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import ivan_OOP_DB.Frame;

public class DepartmentActions extends Actions {
	int departmentId = 0;
	String selectedDepartment = null;

	public TouchDepartmentTable getTouchDepartmentTable() {
		return new TouchDepartmentTable();
	}

	public AddDepartmentAction getAddDepartmentAction() {
		return new AddDepartmentAction();
	}

	public EditDepartmentAction getEditDepartmentAction() {
		return new EditDepartmentAction();
	}

	public DeleteDepartmentAction getDeleteDepartmentAction() {
		return new DeleteDepartmentAction();
	}

	/*
	 * What happens when you click twice or more a row in the Departments table
	 */
	class TouchDepartmentTable implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				int row = Frame.getDepartmentTable().getSelectedRow();
				for (int i = 0, j = 0, k = 0; i < (Frame.getDepartmentTextFields().length
						+ Frame.getDepartmentComboBoxes().length); i++) {
					if (i == 2) {
						Frame.getDepartmentComboBoxes()[j]
								.setSelectedItem(Frame.getDepartmentTable().getValueAt(row, i));
						j++;
						continue;
					}
					Frame.getDepartmentTextFields()[k]
							.setText(Frame.getDepartmentTable().getValueAt(row, i).toString());
					k++;
				}
				Object idObject = Frame.getDepartmentTable().getModel().getValueAt(row, 0);
				departmentId = Integer.parseInt((idObject.toString()));
				selectedDepartment = Frame.getDepartmentTable().getValueAt(row, 0).toString() + " from "
						+ Frame.getDepartmentTable().getValueAt(row, 2);
				Frame.getDepartmentStatusLabel().setText("Current department: " + selectedDepartment);
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

	}

	public class AddDepartmentAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String departmentName = Frame.getDepartmentTextFields()[0].getText();
				String departmentDateFounded = Frame.getDepartmentTextFields()[1].getText();
				String departmentLocation = Frame.getDepartmentComboBoxes()[0].getSelectedItem().toString();
				String sql = "insert into departments values(null,?,?,(select location_id from locations where location_name=?))";
				conn = dbUtil.getConnection();

				statement = conn.prepareStatement(sql);
				statement.setString(1, departmentName);
				statement.setString(2, departmentDateFounded);
				statement.setString(3, departmentLocation);

				statement.execute();
				clearDepartmentFields();
				Frame.getDepartmentStatusLabel().setText("New row is added");
				Frame.getDepartmentTable().setModel(dbUtil.getDbContent("departments"));
				Frame.getDepartmentTable().removeColumn(Frame.getDepartmentTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				Frame.getDepartmentStatusLabel().setText("Invalid value! Current department: " + selectedDepartment);
				e1.printStackTrace();
			} catch (Exception e2) {
				Frame.getDepartmentStatusLabel().setText("Invalid value! Current department: " + selectedDepartment);
				e2.printStackTrace();
			}

		}

	}

	public class DeleteDepartmentAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn = dbUtil.getConnection();
			String sql = "delete from departments where department_id=?";
			try {
				if (departmentId == 0)
					throw new Exception("No row selected");
				statement = conn.prepareStatement(sql);
				statement.setInt(1, departmentId);
				statement.execute();
				Frame.getDepartmentTable().setModel(dbUtil.getDbContent("departments"));
				Frame.getDepartmentTable().removeColumn(Frame.getDepartmentTable().getColumnModel().getColumn(0));
				Frame.getJobTable().setModel(dbUtil.getDbContent("jobs"));
				Frame.getJobTable().removeColumn(Frame.getJobTable().getColumnModel().getColumn(0));
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
				Frame.getDepartmentStatusLabel().setText("Row with department " + selectedDepartment + " was deleted!");
				departmentId = 0;
				clearDepartmentFields();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getDepartmentStatusLabel().setText("Please select a row to delete!");
				e.printStackTrace();
			} catch (Exception e) {
				Frame.getDepartmentStatusLabel().setText("Please select a row to delete!");
				e.printStackTrace();
			}
		}
	}

	public class EditDepartmentAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if (departmentId == 0)
					throw new Exception("No row selected");
				String departmentName = Frame.getDepartmentTextFields()[0].getText();
				String departmentDateFounded = Frame.getDepartmentTextFields()[1].getText();
				String departmentLocation = Frame.getDepartmentComboBoxes()[0].getSelectedItem().toString();
				conn = dbUtil.getConnection();
				String sql = "update departments set department_name=?, date_founded=?, "
						+ "location_id=(select location_id from locations where location_name=?) where department_id=?";

				statement = conn.prepareStatement(sql);
				statement.setString(1, departmentName);
				statement.setString(2, departmentDateFounded);
				statement.setString(3, departmentLocation);
				statement.setInt(4, departmentId);
				statement.execute();
				clearDepartmentFields();
				Frame.getDepartmentTable().setModel(dbUtil.getDbContent("departments"));
				Frame.getDepartmentTable().removeColumn(Frame.getDepartmentTable().getColumnModel().getColumn(0));
				Frame.getJobTable().setModel(dbUtil.getDbContent("jobs"));
				Frame.getJobTable().removeColumn(Frame.getJobTable().getColumnModel().getColumn(0));
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
				Frame.getDepartmentStatusLabel().setText("Department is updated!");
				departmentId = 0;
			} catch (SQLException e) {
				Frame.getDepartmentStatusLabel().setText("Invalid value! Current department: " + selectedDepartment);
				e.printStackTrace();
			} catch (Exception e1) {
				Frame.getDepartmentStatusLabel().setText("Invalid value! Current department: " + selectedDepartment);
				e1.printStackTrace();
			}
		}
	}

	public void clearDepartmentFields() {
		for (int i = 0; i < Frame.getDepartmentTextFields().length; i++)
			Frame.getDepartmentTextFields()[i].setText("");
	}
}
