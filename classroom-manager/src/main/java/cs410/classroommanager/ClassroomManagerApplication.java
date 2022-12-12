package cs410.classroommanager;
import java.security.DrbgParameters;
import java.sql.*;
import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassroomManagerApplication {

	private static int num;
	private static double dub;
	private Statement statement;
	private Connection connection;
	private static ResultSet resultSet;
/***
     * Splits a string up by spaces. Spaces are ignored when wrapped in quotes.
     *
     * @param command - School Management System cli command
     * @return splits a string by spaces.
     */
    public static List<String> parseArguments(String command) {
        List<String> commandArguments = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(command);
        while (m.find()) commandArguments.add(m.group(1).replace("\"", ""));
        return commandArguments;
    }
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {

		Connection con = null;
		Statement stmt = null;
		Statement stmt2 = null;

		try {
			int nRemotePort = 57283;// remote port number of your database
			String strDbPassword = "ignit89";// database login password
			String dbName = "CS410";
			String Usage = "Usage  : java Project <Method> <ItemCode> <ItemNumber> <ItemValue>, date should be entered without /";
			/*
			 * STEP 1 and 2
			 * LOAD the Database DRIVER and obtain a CONNECTION
			 * */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("jdbc:mysql://localhost:" + nRemotePort + "/cs410?verifyServerCertificate=false&useSSL=true");
			con = DriverManager.getConnection("jdbc:mysql://localhost:" + nRemotePort + "/cs410?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC", "msandbox",
					strDbPassword);
			// Do something with the Connection

			//jdbc:mysql://localhost:" + nRemotePort + "/FinalProject?verifyServerCertificate=false&useSSL=true?serverTimezone=UTC

			con.setAutoCommit(false);
			System.out.println("Welcome to the School Management System");
			System.out.println("-".repeat(80));
	
			Scanner scan = new Scanner(System.in);
			String command = "";
	
			do {
				System.out.print("Command: ");
				command = scan.nextLine();
				;
				List<String> commandArguments = parseArguments(command);
				command = commandArguments.get(0);
				commandArguments.remove(0);
	
				if (command.equals("help")) {
					System.out.println("-".repeat(38) + "Help" + "-".repeat(38));
					System.out.println("test connection \n\tTests the database connection");
	
					System.out.println("list students \n\tlists all the students");
					System.out.println("list classes \n\tlists all the classes");
					System.out.println("list class_sections \n\tlists all the class_sections");
					System.out.println("list class_registrations \n\tlists all the class_registrations");
					System.out.println("list instructor <first_name> <last_name>\n\tlists all the classes taught by that instructor");
	
	
					System.out.println("delete student <studentId> \n\tdeletes the student");
					System.out.println("create student <first_name> <last_name> <birthdate> \n\tcreates a student");
					System.out.println("register student <student_id> <class_section_id>\n\tregisters the student to the class section");
	
					System.out.println("submit grade <studentId> <class_section_id> <letter_grade> \n\tcreates a student");
					System.out.println("help \n\tlists help information");
					System.out.println("quit \n\tExits the program");
				} else if (command.equals("test") && commandArguments.get(0).equals("connection")) {
				// 	Database.testConnection();
				// } else if (command.equals("list")) {
				// 	if (commandArguments.get(0).equals("students")) attemptToListStudents();
				// 	if (commandArguments.get(0).equals("classes")) listAllClasses();
				// 	if (commandArguments.get(0).equals("class_sections")) listAllClassSections();
				// 	if (commandArguments.get(0).equals("class_registrations")) listAllClassRegistrations();
				// 	if (commandArguments.get(0).equals("instructor")) {
				// 		getAllClassesByInstructor(commandArguments.get(1), commandArguments.get(2));
				// 	}
				// } else if (command.equals("create")) {
				// 	if (commandArguments.get(0).equals("student")) {
				// 		createNewStudent(commandArguments.get(1), commandArguments.get(2), commandArguments.get(3));
				// 	}
				// } else if (command.equals("register")) {
				// 	if (commandArguments.get(0).equals("student")) {
				// 		registerStudent(commandArguments.get(1), commandArguments.get(2));
				// 	}
				// } else if (command.equals("submit")) {
				// 	if (commandArguments.get(0).equals("grade")) {
				// 		submitGrade(commandArguments.get(1), commandArguments.get(2), commandArguments.get(3));
				// 	}
				// } else if (command.equals("delete")) {
				// 	if (commandArguments.get(0).equals("student")) {
				// 		deleteStudent(commandArguments.get(1));
				// 	}
				// } else if (!(command.equals("quit") || command.equals("exit"))) {
					System.out.println(command);
					System.out.println("Command not found. Enter 'help' for list of commands");
				}
				System.out.println("-".repeat(80));
			} while (!(command.equals("quit") || command.equals("exit")));
			System.out.println("Bye!");
			// if (args[0] == "/?") {
			// 	System.out.println(Usage);
			// } else {
			// 	if (args[0].equals("NewClass")) {
			// 		num = Integer.parseInt(args[3]);
			// 		NewClass(con, args[1], args[2], num, args[4]);
			// 	} else if (args[0].equals("ListClasses")) {
			// 		ListClasses(con);
			// 	} else if (args[0].equals("SelectClass")) {
			// 		if (args.length == 4) {
			// 			num = Integer.parseInt(args[3]);
			// 			SelectClass(con, args[1], args[2], num);
			// 		} else if (args.length == 3) {
			// 			SelectClass(con, args[1], args[2]);
			// 		} else {
			// 			SelectClass(con, args[1]);
			// 		}
			// 	} else if (args[0].equals("ShowClass")) {
			// 		ShowClass(con);
			// 	} else if (args[0].equals("ShowCategories")) {
			// 		ShowCategories(con);
			// 	} else if (args[0].equals("ShowAssignment")) {
			// 		ShowAssignment(con);
			// 	} else if (args[0].equals("AddCategory")) {
			// 		dub = Double.parseDouble(args[2]);
			// 		AddCategory(con, args[1], dub);
			// 	} else if (args[0].equals("AddAssignment")) {
			// 		num = Integer.parseInt(args[4]);
			// 		AddAssignment(con, args[1], args[2], args[3], num);
			// 	} else if (args[0].equals("AddStudent")) {
			// 		if (args.length == 5) {
			// 			num = Integer.parseInt(args[2]);
			// 			AddStudent(con, args[1], num, args[3], args[4]);
			// 		} else {
			// 			AddStudent(con, args[1]);
			// 		}
			// 	} else if (args[0].equals("ShowStudents")) {
			// 		if (args.length == 2) {
			// 			ShowStudents(con, args[1]);
			// 		} else {
			// 			ShowStudents(con);
			// 		}
			// 	} else if (args[0].equals("Grade")) {
			// 		num = Integer.parseInt(args[3]);
			// 		Grade(con, args[1], args[2], num);
			// 	} else if (args[0].equals("StudentGrades")) {
			// 		StudentGrades(con, args[1]);
			// 	} else if (args[0].equals("GradeBook")) {
			// 		GradeBook(con);
			// 	}
			con.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			con.rollback(); // In case of any exception, we roll back to the database state we had before starting this transaction
		} finally {

			if (stmt != null)
				stmt.close();

			if (stmt2 != null)
				stmt2.close();
			if (con != null) {
				con.setAutoCommit(true); // restore dafault mode
				con.close();
			}
		}
	}

	public static void NewClass(Connection connection, String className, String term, int section, String description) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call NewClass(?,?,?,?)}");
		statement.setString(1, className);
		statement.setString(2, term);
		statement.setInt(3, section);
		statement.setString(4, description);
		statement.execute();
		statement.close();
	}

	public static ResultSet ListClasses(Connection connection) throws SQLException {

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

	public static ResultSet SelectClass(Connection connection, String classname) throws SQLException {

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

	public static ResultSet SelectClass(Connection connection, String classname, String term) throws SQLException {

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

	public static ResultSet SelectClass(Connection connection, String classname, String term, int section) throws SQLException {

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

	public static ResultSet ShowClass(Connection connection) throws SQLException {

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

	public static ResultSet ShowCategories(Connection connection) throws SQLException {

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
		statement.setString(1, name);
		statement.setDouble(2, weight);
		statement.execute();
		statement.close();
	}

	public static ResultSet ShowAssignment(Connection connection) throws SQLException {

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
		statement.setString(1, name);
		statement.setString(2, category);
		statement.setString(3, description);
		statement.setInt(4, points);
		statement.execute();
		statement.close();
	}


	public static void AddStudent(Connection connection, String username, int studentID, String last, String first) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddStudent(?,?,?,?)}");
		statement.setString(1, username);
		statement.setDouble(2, studentID);
		statement.setString(3, last);
		statement.setString(4, first);
		statement.execute();
		statement.close();
	}

	public static void AddStudent(Connection connection, String username) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call AddStudent(?)}");
		statement.setString(1, username);
		statement.execute();
		statement.close();
	}

	public static ResultSet ShowStudents(Connection connection, String string) throws SQLException {

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

	public static ResultSet ShowStudents(Connection connection) throws SQLException {

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
		statement.setString(1, assignmentName);
		statement.setString(2, username);
		statement.setInt(3, grade);
		statement.execute();
		statement.close();
	}


	public static ResultSet StudentGrades(Connection connection, String username) throws SQLException {

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

	public static ResultSet GradeBook(Connection connection) throws SQLException {

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