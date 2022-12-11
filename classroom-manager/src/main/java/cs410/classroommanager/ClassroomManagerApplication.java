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

@Shell Component
public class ClassManagement{

	@ShellMethod
	public static void NewClass(Connection connection, String className, String term, int section, String description) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call NewClass(?,?,?,?)}");
		statement.setString(1,className);
		statement.setString(2,term);
		statement.setInt(3,section);
		statement.setString(4,description);
		statement.execute();
		statement.close();
	}

	@ShellMethod
	public static resultSet ListCLasses(Connection connection) throws SQLException {

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

	@ShellMethod
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

	@ShellMethod
	public static resultSet SelectClass(Connection connection, String classname, String term) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClass(?,?)}");
		statement.setString(1, classname);
		statement.setString(2, term);
		statement.set
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
	public static resultSet SelectClass(Connection connection, String classname, String term, int section) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClass(?,?,?)}");
		statement.setString(1, classname);
		statement.setString(2, term);
		statement.setInt(3, section);
		statement.set
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

@ShellComponent
public class CategoryandAssignmentManagment{

	@ShellMethod
	public static resultSet ShowCategories(Connection connection) throws SQLException {

		@ShellMethod
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

	@ShellMethod
	public static void AddCategory(Connection connection, String name, double weight) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddCategory(?,?)}");
		statement.setString(1,name);
		statement.setDouble(2,weight);
		statement.execute();
		statement.close();
	}

	@ShellMethod
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

	@ShellMethod
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
//END STUDENT MANAGEMENT

@ShellComponent
public class GradeReporting{

	@ShellMethod
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

	@ShellMethod
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