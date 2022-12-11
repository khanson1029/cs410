package cs410.classroommanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@SpringBootApplication
public class ClassroomManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassroomManagerApplication.class, args);
	}



}

@ShellComponent
public class StudentManagement {

	@ShellMethod
	public static void AddStudent(Connection connection, String username, int studentID, String last, String first) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddStudent(?,?,?,?)}");
		statement.setString(1,code);
		statement.setString(2,studentID);
		statement.setDouble(3,last);
		statement.setString(4,first);
		statement.execute();
		statement.close();
	}

	@ShellMethod
	public static void AddStudent(Connection connection, String username) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddStudent(?)}");
		statement.setString(1,username);
		statement.execute();
		statement.close();
	}

	@ShellMethod
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

	@ShellMethod
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

	@ShellMethod
	public static void Grade(Connection connection, String assignmentName, String username, int grade) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call Grade(?,?,?)}");
		statement.setString(1,assignmentName);
		statement.setString(2,username);
		statement.setInt(3,grade);
		statement.execute();
		statement.close();
	}
}