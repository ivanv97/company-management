package ivan_OOP_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*Handles the connection to embedded H2 database, 
 * gets table content and allowed combo box items
 */
public class DBUtil {
	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	public Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public MyModel getDbContent(String table) {
		conn = getConnection();
		String sql = null;
		if (table.equals("employees")) {
			sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date,"
					+ "j.job_title, e.salary, em.email as manager_email, l.location_name, d.department_name "
					+ "from employees e inner join jobs j on e.job_id=j.job_id "
					+ "inner join departments d on j.department_id=d.department_id "
					+ "inner join locations l on d.location_id=l.location_id "
					+ "left outer join employees em on e.manager_id=em.employee_id";
		} else if (table.equals("jobs")) {
			sql = "select j.job_id, j.job_title, j.min_salary, j.max_salary, d.department_name, l.location_name "
					+ "from jobs j inner join departments d on j.department_id=d.department_id "
					+ "inner join locations l on d.location_id=l.location_id";
		} else if (table.equals("departments")) {
			sql = "select d.department_id, d.department_name, d.date_founded, l.location_name "
					+ "from departments d inner join locations l on d.location_id=l.location_id";
		}

		try {
			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			return (new MyModel(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String[] getComboBoxValues(Parameters valuesToGet) {
		conn = getConnection();
		String sql = null;
		ArrayList<String> arrayList = new ArrayList<String>();
		if (valuesToGet.equals(Parameters.JOB_TITLES)) {
			sql = "select distinct job_title from jobs";
		} else if (valuesToGet.equals(Parameters.DEPARTMENT_NAMES)
				|| valuesToGet.equals(Parameters.JOB_DEPARTMENT_NAMES)) {
			sql = "select distinct department_name from departments";
		} else if (valuesToGet.equals(Parameters.MANAGER_EMAILS)) {
			sql = "select distinct email from employees";
		} else if (valuesToGet.equals(Parameters.LOCATION_NAMES) || valuesToGet.equals(Parameters.JOB_LOCATION_NAMES)
				|| valuesToGet.equals(Parameters.DEPT_LOCATION_NAMES)) {
			sql = "select distinct location_name from locations";
		}

		try {
			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			arrayList.add("");
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(1));
			}
			return arrayList.toArray(new String[arrayList.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
