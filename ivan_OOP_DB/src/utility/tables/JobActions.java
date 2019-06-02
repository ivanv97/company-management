package utility.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import ivan_OOP_DB.Frame;

public class JobActions extends Actions {
	int jobId = 0;
	String selectedJob = null;

	public TouchJobTable getTouchJobTable() {
		return new TouchJobTable();
	}

	public AddJobAction getAddJobAction() {
		return new AddJobAction();
	}

	public EditJobAction getEditJobAction() {
		return new EditJobAction();
	}

	public DeleteJobAction getDeleteJobAction() {
		return new DeleteJobAction();
	}

	/*
	 * What happens when you click twice or more a row in the Jobs table
	 */
	class TouchJobTable implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				int row = Frame.getJobTable().getSelectedRow();
				for (int i = 0, j = 0, k = 0; i < (Frame.getJobTextFields().length
						+ Frame.getJobComboBoxes().length); i++) {
					if (i == 3 || i == 4) {
						Frame.getJobComboBoxes()[j].setSelectedItem(Frame.getJobTable().getValueAt(row, i));
						j++;
						continue;
					}
					Frame.getJobTextFields()[k].setText(Frame.getJobTable().getValueAt(row, i).toString());
					k++;
				}
				Object idObject = Frame.getJobTable().getModel().getValueAt(row, 0);
				jobId = Integer.parseInt((idObject.toString()));
				selectedJob = Frame.getJobTable().getValueAt(row, 0).toString() + " from "
						+ Frame.getJobTable().getValueAt(row, 3).toString() + ", "
						+ Frame.getJobTable().getValueAt(row, 4).toString();
				Frame.getJobStatusLabel().setText("Current job: " + selectedJob);
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

	class AddJobAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String jobTitle = Frame.getJobTextFields()[0].getText();
				float minSalary = Float.parseFloat(Frame.getJobTextFields()[1].getText());
				float maxSalary = Float.parseFloat(Frame.getJobTextFields()[2].getText());
				String jobDepartmentName = Frame.getJobComboBoxes()[0].getSelectedItem().toString();
				String jobDepartmentLocation = Frame.getJobComboBoxes()[1].getSelectedItem().toString();

				String sql = "insert into jobs values(null,?,?,?,"
						+ "(select department_id from departments where department_name=? "
						+ "and location_id=(select location_id from locations where location_name=?)))";
				conn = dbUtil.getConnection();
				statement = conn.prepareStatement(sql);
				statement.setString(1, jobTitle);
				statement.setFloat(2, minSalary);
				statement.setFloat(3, maxSalary);
				statement.setString(4, jobDepartmentName);
				statement.setString(5, jobDepartmentLocation);

				statement.execute();
				clearJobFields();
				Frame.getJobStatusLabel().setText("New row is added");
				Frame.getJobTable().setModel(dbUtil.getDbContent("jobs"));
				Frame.getJobTable().removeColumn(Frame.getJobTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getJobStatusLabel().setText("Invalid value! Current job: " + selectedJob);
				e.printStackTrace();
			} catch (Exception e) {
				Frame.getJobStatusLabel().setText("Invalid value! Current job: " + selectedJob);
				e.printStackTrace();
			}

		}
	}

	class DeleteJobAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn = dbUtil.getConnection();
			String sql = "delete from jobs where job_id=?";
			try {
				if (jobId == 0)
					throw new Exception("No row selected");
				statement = conn.prepareStatement(sql);
				statement.setInt(1, jobId);
				statement.execute();
				Frame.getJobTable().setModel(dbUtil.getDbContent("jobs"));
				Frame.getJobTable().removeColumn(Frame.getJobTable().getColumnModel().getColumn(0));
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
				Frame.getJobStatusLabel().setText("Row with job " + selectedJob + " was deleted!");
				jobId = 0;
				clearJobFields();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getJobStatusLabel().setText("Please select a row to delete!");
				e.printStackTrace();
			} catch (Exception e) {
				Frame.getJobStatusLabel().setText("Please select a row to delete!");
				e.printStackTrace();
			}
		}
	}

	class EditJobAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				String jobTitle = Frame.getJobTextFields()[0].getText();
				float minSalary = Float.parseFloat(Frame.getJobTextFields()[1].getText());
				float maxSalary = Float.parseFloat(Frame.getJobTextFields()[2].getText());
				String jobDepartmentName = Frame.getJobComboBoxes()[0].getSelectedItem().toString();
				String jobDepartmentLocation = Frame.getJobComboBoxes()[1].getSelectedItem().toString();
				conn = dbUtil.getConnection();
				String sql = "update jobs set job_title=?, min_salary=?, max_salary=?, department_id="
						+ "(select department_id from departments where department_name=? and "
						+ "location_id=(select location_id from locations where location_name=?)) where job_id=?";

				statement = conn.prepareStatement(sql);
				statement.setString(1, jobTitle);
				statement.setFloat(2, minSalary);
				statement.setFloat(3, maxSalary);
				statement.setString(4, jobDepartmentName);
				statement.setString(5, jobDepartmentLocation);
				statement.setInt(6, jobId);
				statement.execute();
				clearJobFields();
				Frame.getJobTable().setModel(dbUtil.getDbContent("jobs"));
				Frame.getJobTable().removeColumn(Frame.getJobTable().getColumnModel().getColumn(0));
				Frame.getEmployeeTable().setModel(dbUtil.getDbContent("employees"));
				Frame.getEmployeeTable().removeColumn(Frame.getEmployeeTable().getColumnModel().getColumn(0));
				Frame.setComboBoxValues();
				Frame.getJobStatusLabel().setText("Job is updated!");
				jobId = 0;
			} catch (SQLException e) {
				Frame.getJobStatusLabel().setText("Invalid value! Current job: " + selectedJob);
				e.printStackTrace();
			} catch (Exception e) {
				Frame.getJobStatusLabel().setText("Invalid value! Current job: " + selectedJob);
				e.printStackTrace();
			}
		}
	}

	public void clearJobFields() {
		for (int i = 0; i < Frame.getJobTextFields().length; i++)
			Frame.getJobTextFields()[i].setText("");
		for (int i = 0; i < Frame.getJobComboBoxes().length; i++)
			Frame.getJobComboBoxes()[i].setSelectedIndex(0);
	}
}
