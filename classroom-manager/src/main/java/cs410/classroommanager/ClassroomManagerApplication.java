package cs410.classroommanager;
import java.security.DrbgParameters;
import java.sql.*;
import java.io.*;
import java.sql.Date;
import java.util.*;

public class ClassroomManagerApplication{

	private static int num;
	private static double dub;
	private int c;
	private Statement statement;
	private Connection connection;
	private static ResultSet resultSet;

	public static void main (String args[]) throws ClassNotFoundException, SQLException {

		Connection con = null;
		Statement stmt = null;
		Statement stmt2 = null;

		try {
			int nRemotePort = 57283;// remote port number of your database
			String strDbPassword = "";// database login password
			String dbName = "CS410";

			/*
			 * STEP 1 and 2
			 * LOAD the Database DRIVER and obtain a CONNECTION
			 * */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("jdbc:mysql://localhost:" + nRemotePort + "/CS410?verifyServerCertificate=false&useSSL=true");
			con = DriverManager.getConnection("jdbc:mysql://localhost:" + nRemotePort + "/CS410?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC", "msandbox",
					strDbPassword);
			// Do something with the Connection

			//jdbc:mysql://localhost:" + nRemotePort + "/FinalProject?verifyServerCertificate=false&useSSL=true?serverTimezone=UTC

			con.setAutoCommit(false);

			if (args[0] == "/?") {
				System.out.println(Usage);
			} else {
				if (args[0].equals("NewClass")) {
					dub = Integer.parseInteger(args[2]);
					NewClass(con, args[1], dub, args[3], args[4]);
				} else if (args[0].equals("ListClasses")) {
					ListClasses(con);
				} else if (args[0].equals("SelectClass")) {
					if(args.length == 4){
						dub = Integer.parseInteger(args[3]);
						SelectClass(con, args[1], args[2], dub);
					} else if (args.length == 3){
						SelectClass(con, args[1], args[2]);
					} else {
						SelectClass(con, args[1]);
					}
				} else if (args[0].equals("ShowClass")) {
					ShowClass(con);
				} else if (args[0].equals("ShowCategories")) {
					ShowCategories(con);
				} else if (args[0].equals("GetPurchases")) {
					GetPurchases(con, args[1]);
				} else if (args[0].equals("ShowAssignment")) {
					ShowAssignment(con);
				} else if (args[0].equals("AddCategory")) {
					dub = Double.parseDouble(arg[2]);
					AddCategory(con, args[1], dub);
				} else if (args[0].equals("AddAssignment")) {
					num = Integer.parseInteger(args[4]);
					AddAssignment(con, args[1], args[2], args[3], num);
				} else if (args[0].equals("AddStudent")) {
					if(args.length == 5){
						num = Integer.parseInteger(args[2]);
						AddStudent(con, args[1], num, args[3], args[4]);
					} else {
						AddStudent(con, args[1]);
					}
				} else if (args[0].equals("ShowStudents")) {
					if(args.length == 2){
						ShowStudents(con, args[1]);
					} else {
						ShowStudents(con);
					}
				} else if (args[0].equals("Grade")) {
					num = Integer.parseInteger(args[3]);
					Grade(con, args[1], args[2], num);
				} else if (args[0].equals("StudentGrades")) {
					StudentGrades(con, args[1]);
				} else if (args[0].equals("GradeBook")) {
					GradeBook(con);
				}
			}
			con.commit();
		} catch( SQLException e ) {
			System.out.println(e.getMessage());
			con.rollback(); // In case of any exception, we roll back to the database state we had before starting this transaction
		} finally {

			if(stmt!=null)
				stmt.close();

			if(stmt2!=null)
				stmt2.close();
			if(con != null) {
				con.setAutoCommit(true); // restore dafault mode
				con.close();
			}
		}
	}

}

public class ClassManagement{

	public static void NewClass(Connection connection, String className, String term, int section, String description) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call NewClass(?,?,?,?)}");
		statement.setString(1,className);
		statement.setString(2,term);
		statement.setInt(3,section);
		statement.setString(4,description);
		statement.execute();
		statement.close();
	}

	public static resultSet ListClasses(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ListClasses()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static resultSet SelectClass(Connection connection, String classname) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClass(?)}");
		statement.setString(1, classname);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static resultSet SelectClass(Connection connection, String classname, String term) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClass(?,?)}");
		statement.setString(1, classname);
		statement.setString(2, term);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static resultSet SelectClass(Connection connection, String classname, String term, int section) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClass(?,?,?)}");
		statement.setString(1, classname);
		statement.setString(2, term);
		statement.setInt(3, section);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static resultSet ShowClass(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowClass()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

}
//END of CLASSMANAGEMENT

public class CategoryandAssignmentManagment{

	public static resultSet ShowCategories(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowCategories()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static void AddCategory(Connection connection, String name, double weight) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddCategory(?,?)}");
		statement.setString(1,name);
		statement.setDouble(2,weight);
		statement.execute();
		statement.close();
	}

	public static resultSet ShowAssignment(Connection connection) throws SQLException {

		@ShellMethod
		CallableStatement statement = connection.prepareCall("{call ShowAssignment()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static void AddAssignment(Connection connection, String name, String category, String description, int points) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddAssignment(?,?,?,?)}");
		statement.setString(1,name);
		statement.setString(2,category);
		statement.setString(3,description);
		statement.setInt(4,points);
		statement.execute();
		statement.close();
	}
}
//END CATEGORY AND ASSIGNMENT MANAGEMENT

public class StudentManagement {

	public static void AddStudent(Connection connection, String username, int studentID, String last, String first) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddStudent(?,?,?,?)}");
		statement.setString(1,code);
		statement.setString(2,studentID);
		statement.setDouble(3,last);
		statement.setString(4,first);
		statement.execute();
		statement.close();
	}

	public static void AddStudent(Connection connection, String username) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddStudent(?)}");
		statement.setString(1,username);
		statement.execute();
		statement.close();
	}

	public static resultSet ShowStudents(Connection connection, String string) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowStudents(?)}");
		statement.setString(1, string);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static resultSet ShowStudents(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowStudents()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static void Grade(Connection connection, String assignmentName, String username, int grade) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call Grade(?,?,?)}");
		statement.setString(1,assignmentName);
		statement.setString(2,username);
		statement.setInt(3,grade);
		statement.execute();
		statement.close();
	}
}
//END STUDENT MANAGEMENT

public class GradeReporting{

	public static resultSet StudentGrades(Connection connection, String username) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call StudentGrades(?)}");
		statement.setString(1, username);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static resultSet GradeBook(Connection connection) throws SQLException {

		@ShellMethod
		CallableStatement statement = connection.prepareCall("{call GradeBook()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}
}
//END GradeReporting