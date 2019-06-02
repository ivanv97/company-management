package utility.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ivan_OOP_DB.DBUtil;

public abstract class Actions {
	DBUtil dbUtil = new DBUtil();

	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
}
