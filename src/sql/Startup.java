package sql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Startup extends Thread {
	/** A very basic SQL script runner
	 * @param filename the sql script to run
	 */
	private void runScript(String filename) {
		BufferedReader br = null;
		FileReader fr = null;
		Connection conn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root");
			st = conn.createStatement();
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = "", multiLineStatement = "";
			boolean multiLine = false;
			while ((line = br.readLine()) != null) {
				// Basic support to ignore blank lines and comment lines
				if (!line.equals("") && !(line.charAt(0) == '#')) {
					if (line.length() >= 2 && line.charAt(line.length()-1) == '(') {
						multiLine = true;
					} else if (multiLine && line.equals(");")) {
						multiLine = false;
						st.execute(multiLineStatement + ");");
						multiLineStatement = "";
					} else if (!multiLine) {
						st.execute(line);
					}
					
					if (multiLine) {
						multiLineStatement += line;
					}
				}
			}
			System.out.println("Successfully ran SQL script: " + filename);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Startup fnfe: " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Startup ioe: " + ioe.getMessage());
		} catch (SQLException sqle) {
			System.out.println ("Startup SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("Startup ClassNotFoundException: " + cnfe.getMessage());
		}  finally {
			try {
				br.close();
			} catch (IOException e) { /* Do nothing */ }
			try {
				fr.close();
			} catch (IOException e) { /* Do nothing */ }
			try {
				st.close();
			} catch (SQLException e) { /* Do nothing */ }

			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}
	
	/** Do startup preparation
	 * 	Run SQL scripts to create a fresh database with sample data 
	 *  @Override
	 */
	@Override
	public void run() {
		runScript("localhost/sql/CreateDatabase.sql");
		runScript("localhost/sql/PopulateDatabase.sql");
	}
	
	/** Main method for testing this thread separately
	 * @param args Command line arguments passed in
	 */
	public static void main(String[] args) {
		Startup s = new Startup();
		s.start();
	}
}
