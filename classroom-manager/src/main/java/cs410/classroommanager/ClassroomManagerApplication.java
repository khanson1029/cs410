package cs410.classroommanager;
import java.security.DrbgParameters;
import java.sql.*;
import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.constraints.Null;

public class ClassroomManagerApplication {

	private static int num;
	private static double dub;
	private Statement statement;
	private Connection connection;
	private static ResultSet resultSet;
	private static int classid;
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
	
	public static void main(String args[]) throws Exception {

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

					//Class Management
					System.out.println("NewClass <Class Name> <Term> <Section Number> <Description> \n\tcreates a new class");
					System.out.println("ListClasses \n\tlists all the classes w/ number of attending students");
					System.out.println("SelectClass <Class Name> \n\tselects the only section of <Class Name> in the most recent term, " +
										"if there is only one such section; if there are multiple sections it fails.");
					System.out.println("SelectClassTerm <Class Name> <Term> \n\tselects the only section of <Class Name> in <Term>;" +
										"if there are multiple such sections, it fails.");
					System.out.println("SelectClassSection <Class Name> <Term> <Section #> \n\tselects a specific section");
					System.out.println("ShowClass \n\tlists all the currently active classes");

					//Category and Assignment Management
					System.out.println("ShowCategories \n\tlist the categories with their weights");
					System.out.println("AddCategory <Category Name> <Weight> \n\tadd a new category");
					System.out.println("ShowAssignment \n\tlist the assignments with their point values, grouped by category");
					System.out.println("AddAssignment <Assignment Name> <Category> <Description> <Points> \n\tadd a new assignment");

					//Student Management
					System.out.println("AddStudent1 <Username> <studentId> <First Name Last Name > \n\tadds a student and enrolls" +
										" them in the current class. If the student already exists, enroll them in the class");
					System.out.println("AddStudent2 <Username> \n\tadds an already existing student to the current class");
					System.out.println("ShowStudents1 \n\tshow all students in the current class");
					System.out.println("ShowStudents2 <string> \n\tshow all students with ‘string’ in their name or username" +
										"(case-insensitive)");
					System.out.println("Grade <Assignment Name> <Username> <Grade> \n\tAssign a grade for the <assignment> " +
										"update the grade if it already exists");

					//Grade Reporting
					System.out.println("StudentGrades <Username> \n\tshow student’s current grade: all assignments, visually " +
										"grouped by category, with the student’s grade (if they have one)");
					System.out.println("GradeBook  \n\tshow the current class’s gradebook: students (username, student ID, and name), " +
										"along with their total grades in the class.");

					//support functions
					System.out.println("help \n\tlists help information");
					System.out.println("quit \n\tExits the program");

				} else if (command.equals("test") && commandArguments.get(0).equals("connection")) {
					System.out.println("Attempting to connect to MySQL database using:");
					System.out.printf("CS410_HOST: onyx.boisestate.edu");
					System.out.printf("CS410_PORT: %s%n", nRemotePort);
					System.out.printf("CS410_USERNAME: msandbox");
					System.out.printf("CS410_PASSWORD: %s%n", strDbPassword);
			
					try{
						String sql = "SELECT VERSION();";
						CallableStatement sqlStatement = con.prepareCall(sql);
						resultSet = sqlStatement.executeQuery(sql);
						resultSet.next();
						System.out.printf("Connected SUCCESS! Database Version: %s%n", resultSet.getString(1));
					} catch (SQLException sql){
						System.out.println("Failed to connect to database. Please make sure your connection string is correct.");
					} finally {
						try { resultSet.close(); } catch (Exception e) { /* ignored */ }}
				 } else if (command.equals("NewClass")) {
				 		NewClass(con, commandArguments.get(0), commandArguments.get(1), Integer.parseInt(commandArguments.get(2)), commandArguments.get(3));
				 } else if (command.equals("ListClasses")) {
						ListClasses(con);
				 } else if (command.equals("SelectClass")) {
						SelectClass(con, commandArguments.get(0));
				 } else if (command.equals("SelectClassTerm")) {
						SelectClassTerm(con, commandArguments.get(0), commandArguments.get(1));
				 } else if (command.equals("SelectClassSection")) {
						SelectClassSection(con, commandArguments.get(0), commandArguments.get(1), Integer.parseInt(commandArguments.get(2)));
				 } else if (command.equals("ShowClass")) {
						ShowClass(con);
				 } else if (command.equals("ShowCategories")) {
						ShowCategories(con);
				 } else if (command.equals("ShowAssignment")) {
						ShowAssignment(con);
				 } else if (command.equals("AddCategory")) {
						AddCategory(con, commandArguments.get(0), Double.parseDouble(commandArguments.get(1)));
				 } else if (command.equals("AddAssignment")) {
						AddAssignment(con, commandArguments.get(0), commandArguments.get(1), commandArguments.get(2), Integer.parseInt(commandArguments.get(3)));
				 } else if (command.equals("AddStudent2")) {
						AddStudent2(con, commandArguments.get(0));
				 } else if (command.equals("AddStudent1")) {
						AddStudent1(con, commandArguments.get(0), Integer.parseInt(commandArguments.get(1)), commandArguments.get(2));
				 } else if (command.equals("ShowStudents1")) {
						ShowStudents1(con);
				 } else if (command.equals("ShowStudents2")) {
						ShowStudents2(con, commandArguments.get(0));
				 } else if (command.equals("Grade")) {
						Grade(con, commandArguments.get(0), commandArguments.get(1), Integer.parseInt(commandArguments.get(2)));
				 } else if (command.equals("StudentGrades")) {
						StudentGrades(con, commandArguments.get(0));
				 } else if (command.equals("GradeBook")) {
						GradeBook(con);
				 } else if (!(command.equals("quit") || command.equals("exit"))) {
						System.out.println(command);
						System.out.println("Command not found. Enter 'help' for list of commands");
				}
				System.out.println("-".repeat(80));
			} while (!(command.equals("quit") || command.equals("exit")));
				System.out.println("Bye!");

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
		statement.setInt(2, section);
		statement.setString(3, term);
		statement.setString(4, description);
		statement.execute();
		statement.close();
	}

	public static ResultSet ListClasses(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ListClasses()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		System.out.println("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static ResultSet SelectClass(Connection connection, String classname) throws Exception {

		CallableStatement statement = connection.prepareCall("{call SelectClass(?)}");
		statement.setString(1, classname);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		boolean multipleRows = resultSet.isBeforeFirst() && resultSet.next() && !resultSet.isLast();
		try {
			if(multipleRows){
				throw new IllegalArgumentException("There seem to be multiple sections of that class. Please specify with SelectClassTerm or SelectClassSection (use help to see the full list of commands and arguments)");
			}else{
				resultSet = statement.executeQuery();
				for(int i = 1; i<= columnsNumber; i++){
					System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
				}
				System.out.println("\n");
				while (resultSet.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						if(i == 1){
							classid = resultSet.getInt(i);
						}				
						String columnValue = resultSet.getString(i);
						System.out.printf("%30.30s",columnValue);
					}
					System.out.println("");
				}
				statement.close();
				return resultSet;
			}
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}

		return resultSet;
	}

	public static ResultSet SelectClassTerm(Connection connection, String classname, String term) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClassTerm(?,?)}");
		statement.setString(1, classname);
		statement.setString(2, term);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		boolean multipleRows = resultSet.isBeforeFirst() && resultSet.next() && !resultSet.isLast();
		try {
			if(multipleRows){
				throw new IllegalArgumentException("There seem to be multiple sections of that class. Please use  SelectClassSection (use help to see the full list of commands and arguments)");
			}else{
				for(int i = 1; i<= columnsNumber; i++){
					System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
				}
				System.out.println("\n");
				while (resultSet.next()) {
					for (int i = 1; i <= columnsNumber; i++) {	
						if(i == 1){
							classid = resultSet.getInt(i);
						}				
						String columnValue = resultSet.getString(i);
						System.out.printf("%30.30s",columnValue);
					}
					System.out.println("");
				}
				statement.close();
				return resultSet;
			}
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}

		return resultSet;
	}

	public static ResultSet SelectClassSection(Connection connection, String classname, String term, int section) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call SelectClassSection(?,?,?)}");
		statement.setString(1, classname);
		statement.setString(2, term);
		statement.setInt(3, section);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		System.out.println("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if(i == 1){
					classid = resultSet.getInt(i);
				}	
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static ResultSet ShowClass(Connection connection) throws SQLException {
		try {
			if(classid == 0){
				throw new IllegalArgumentException("Please select a class first using SelectClass (use help to show a full list of commands and arguments)");
			}
			CallableStatement statement = connection.prepareCall("{call ShowClass("+classid+")}");
			resultSet = statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			for(int i = 1; i<= columnsNumber; i++){
				System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
			}
			System.out.println("\n");
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					
					String columnValue = resultSet.getString(i);
					System.out.printf("%30.30s",columnValue);
				}
				System.out.println("");
			}
			statement.close();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}

		return resultSet;
	}

	public static ResultSet ShowCategories(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowCategories()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		System.out.println("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {			
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static void AddCategory(Connection connection, String name, double weight) throws SQLException {
		try {
			if(classid == 0){
				throw new IllegalArgumentException("Please select a class first using SelectClass (use help to show a full list of commands and arguments)");
			}
			CallableStatement statement = connection.prepareCall("{call AddCategory(?,?,?)}");
			statement.setString(1, name);
			statement.setDouble(2, weight);
			statement.setInt(3, classid);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}

	}

	public static ResultSet ShowAssignment(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowAssignment()}");
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		System.out.println("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static void AddAssignment(Connection connection, String name, String category, String description, int points) throws SQLException {
		try {
			if(classid == 0){
				throw new IllegalArgumentException("Please select a class first using SelectClass (use help to show a full list of commands and arguments)");
			}
			CallableStatement statement = connection.prepareCall("{call AddAssignment(?,?,?,?)}");
			statement.setString(1, name);
			statement.setString(2, category);
			statement.setString(3, description);
			statement.setInt(4, points);
			statement.setInt(5, classid);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
	}


	public static void AddStudent1(Connection connection, String username, int studentID, String fullname) throws SQLException {
		try {
			if(classid == 0){
				throw new IllegalArgumentException("Please select a class first using SelectClass (use help to show a full list of commands and arguments)");
			}
			CallableStatement statement = connection.prepareCall("{call GetStudentID(?)}");
			statement.setInt(1, studentID);
			resultSet = statement.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1) != studentID){
				CallableStatement statement4 = connection.prepareCall("{call ShowStudents2(?)}");
				statement4.setString(1, fullname);
				ResultSet resultSet2 = statement4.executeQuery();
				resultSet2.next();
				if(resultSet2.getString(1).toLowerCase() != fullname.toLowerCase()){
					System.out.println("WARNING: Changing full name");
					CallableStatement statement1 = connection.prepareCall("{call UpdateStudent(?,?)}");
					statement1.setInt(1, studentID);
					statement1.setString(2, fullname);
					statement1.execute();
					statement1.close();
					CallableStatement statement3 = connection.prepareCall("{call AddStudent2(?,?)}");
					statement3.setString(1, username);
					statement3.setInt(2, classid);
					statement3.execute();
					statement3.close();
				}
			}else{
				CallableStatement statement2 = connection.prepareCall("{call AddStudent1(?,?)}");
				statement2.setInt(1, studentID);
				statement2.setInt(2, classid);
				statement2.execute();
				statement2.close();
			}
			statement.close();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	public static void AddStudent2(Connection connection, String username) throws SQLException {
		try {
			if(classid == 0){
				throw new IllegalArgumentException("Please select a class first using SelectClass (use help to show a full list of commands and arguments)");
			}
			CallableStatement statement = connection.prepareCall("{call AddStudent(?,?)}");
			statement.setString(1, username);
			statement.setInt(2, classid);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}


	public static ResultSet ShowStudents1(Connection connection) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowStudents1(?)}");
		statement.setInt(1, classid);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		System.out.println("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}

	public static ResultSet ShowStudents2(Connection connection, String string) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call ShowStudents(?)}");
		statement.setString(1, string);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		System.out.println("\n");
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}


	public static void Grade(Connection connection, String assignmentName, String username, int grade) throws SQLException {
		try {
			System.out.println("IN GRADE");

			CallableStatement statement = connection.prepareCall("{call GradeExists(?,?)}");
			statement.setString(1, assignmentName);
			statement.setString(2, username);
			resultSet = statement.executeQuery();
			int points;
			boolean isEmpty = !resultSet.isBeforeFirst();
			while (resultSet.next()) {
				for (int i = 1; i <= 2; i++) {
					
					String columnValue = resultSet.getString(i);
					System.out.printf("%30.30s",columnValue);
				}
				System.out.println("");
			}
			if(isEmpty){
				System.out.println("Grade doesn't exist");
				CallableStatement statement2 = connection.prepareCall("{call InsertGrade(?,?,?)}");
				statement2.setString(1, assignmentName);
				statement2.setString(2, username);
				statement2.setInt(3, grade);
				statement2.execute();
				statement2.close();
			}else{
				System.out.println("Grade exists");
				CallableStatement statement3 = connection.prepareCall("{call UpdateExistingGrade(?,?,?)}");
				statement3.setString(1, assignmentName);
				statement3.setString(2, username);
				statement3.setInt(3, grade);
				statement3.execute();
				statement3.close();
			}
			CallableStatement statement4 = connection.prepareCall("{call GradeExists(?,?)}");
			statement4.setString(1, assignmentName);
			statement4.setString(2, username);
			resultSet = statement4.executeQuery();
			resultSet.next();
			if(resultSet.getInt(2) < grade){
				System.out.println("WARNING: Grade exceeds max points");
			}
			statement4.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static ResultSet StudentGrades(Connection connection, String username) throws SQLException {

		CallableStatement statement = connection.prepareCall("{call StudentGrades(?)}");
		statement.setString(1, username);
		resultSet = statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
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
		for(int i = 1; i<= columnsNumber; i++){
			System.out.printf("%1$30s", resultSet.getMetaData().getColumnName(i));
		}
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				
				String columnValue = resultSet.getString(i);
				System.out.printf("%30.30s",columnValue);
			}
			System.out.println("");
		}
		statement.close();
		return resultSet;
	}
}