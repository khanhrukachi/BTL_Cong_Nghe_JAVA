package library.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public interface DatabaseInfo{
	String url = "jdbc:mysql://localhost:3306/qltruyen";
	String user = "root";
	String password = "732003";
}
