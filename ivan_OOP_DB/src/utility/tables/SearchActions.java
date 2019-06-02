package utility.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import ivan_OOP_DB.Frame;
import ivan_OOP_DB.MyModel;

public class SearchActions extends Actions {

	public SearchEmployeeAction getSearchEmployeeAction() {
		return new SearchEmployeeAction();
	}

	public SearchJobAction getSearchJobAction() {
		return new SearchJobAction();
	}

	public SearchDepartmentAction getSearchDepartmentAction() {
		return new SearchDepartmentAction();
	}

	class SearchEmployeeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String fromDate = Frame.getEmployeeSearchTextFields()[0].getText();
				String toDate = Frame.getEmployeeSearchTextFields()[1].getText();
				String fromDepartment = Frame.getEmployeeSearchTextFields()[2].getText();
				String firstNameLike = Frame.getEmployeeSearchTextFields()[3].getText();
				String fromLocation = Frame.getEmployeeSearchTextFields()[4].getText();

				String sql = "select e.first_name, e.last_name, e.email, e.phone_number, e.hire_date,"
						+ "j.job_title, d.department_name, e.salary, em.email as manager_email "
						+ "from employees e inner join jobs j on e.job_id=j.job_id "
						+ "inner join departments d on j.department_id=d.department_id "
						+ "left outer join employees em on e.manager_id=em.employee_id "
						+ "where (d.department_name=? and d.location_id="
						+ "(select location_id from locations where location_name=?)) and (e.hire_date between ? and ?) and (e.first_name like ?)";

				conn = dbUtil.getConnection();

				statement = conn.prepareStatement(sql);
				statement.setString(1, fromDepartment);
				statement.setString(2, fromLocation);
				statement.setString(3, fromDate);
				statement.setString(4, toDate);
				statement.setString(5, firstNameLike + '%');

				resultSet = statement.executeQuery();
				Frame.getEmployeeSearchTable().setModel((new MyModel(resultSet)));
				String message = Frame.getEmployeeSearchTable().getRowCount() == 1 ? "One result found!"
						: Frame.getEmployeeSearchTable().getRowCount() + " results found!";
				Frame.getSearchStatusLabels()[0].setText(message);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getSearchStatusLabels()[0].setText("Invalid values");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Frame.getSearchStatusLabels()[0].setText("Invalid values");
				e.printStackTrace();
			}

		}
	}

	class SearchDepartmentAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String departmentName = Frame.getDepartmentSearchTextFields()[0].getText();
				String dateFounded = Frame.getDepartmentSearchTextFields()[1].getText();

				String sql = "select d.department_name, d.date_founded, l.location_name from departments d "
						+ "inner join locations l on d.location_id=l.location_id "
						+ "where department_name like ? and date_founded >=?";

				conn = dbUtil.getConnection();

				statement = conn.prepareStatement(sql);
				statement.setString(1, departmentName + '%');
				statement.setString(2, dateFounded);

				resultSet = statement.executeQuery();
				Frame.getJobAndDepartmentSearchTable().setModel((new MyModel(resultSet)));
				String message = Frame.getJobAndDepartmentSearchTable().getRowCount() == 1 ? "One result found!"
						: Frame.getJobAndDepartmentSearchTable().getRowCount() + " results found!";
				Frame.getSearchStatusLabels()[1].setText(message);
				Frame.getSearchStatusLabels()[2].setText("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getSearchStatusLabels()[1].setText("Invalid values");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Frame.getSearchStatusLabels()[1].setText("Invalid values");
				e.printStackTrace();
			}
		}

	}

	class SearchJobAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String jobTitle = Frame.getJobSearchTextFields()[0].getText();
				float minSalary = Float.parseFloat(Frame.getJobSearchTextFields()[1].getText());
				float maxSalary = Float.parseFloat(Frame.getJobSearchTextFields()[2].getText());

				String sql = "select j.job_title, j.min_salary, j.max_salary, d.department_name from jobs j "
						+ "inner join departments d on j.department_id=d.department_id "
						+ "where (job_title like ?) and (min_salary>=? and max_salary<=?)";

				conn = dbUtil.getConnection();

				statement = conn.prepareStatement(sql);
				statement.setString(1, jobTitle + '%');
				statement.setFloat(2, minSalary);
				statement.setFloat(3, maxSalary);

				resultSet = statement.executeQuery();
				Frame.getJobAndDepartmentSearchTable().setModel((new MyModel(resultSet)));
				String message = Frame.getJobAndDepartmentSearchTable().getRowCount() == 1 ? "One result found!"
						: Frame.getJobAndDepartmentSearchTable().getRowCount() + " results found!";
				Frame.getSearchStatusLabels()[2].setText(message);
				Frame.getSearchStatusLabels()[1].setText("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Frame.getSearchStatusLabels()[2].setText("Invalid values");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Frame.getSearchStatusLabels()[2].setText("Invalid values");
				e.printStackTrace();
			}
		}

	}
}
