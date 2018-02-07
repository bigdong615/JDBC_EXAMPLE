import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExample {

	public static void main(String[] args) throws SQLException {

		System.out.println("This is testing for JDBC driver");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

			System.out.println("Driver class doesn't exit");
		}

		System.out.println("Driver class  exit");

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.64.151.235:1521:CCB2502", "CISADM",
					"CISADM");
		} catch (SQLException e) {
			System.out.println("couldn't connect to Target DB");
			e.printStackTrace();
		}
		if (connection != null) {

			System.out.print("You are connected to Target DB" + "\n");

		} else {
			System.out.print("failed to connect to Target DB");
		}

		String sql = "SELECT USER_ID  FROM SC_USER";

		PreparedStatement prepareStatement = connection.prepareStatement(sql);

		ResultSet result = prepareStatement.executeQuery();

		while (result.next()) {
			System.out.println(result.getObject("USER_ID"));
		}

		/* insertDemo */

		PreparedStatement insertUser = connection
				.prepareStatement("INSERT INTO SC_USER (COL1,COL2,COL2,COL4) VALUES (?,?,?,?)");
		insertUser.setInt(1, 75);
		insertUser.setString(2, "Colombian");
		insertUser.executeUpdate();

		/* updateDemo */

		PreparedStatement updateUser = connection
				.prepareStatement("UPDATE SC_USER SET SALES = ? WHERE COF_NAME LIKE ? ");
		updateUser.setInt(1, 75);
		updateUser.setString(2, "Colombian");
		updateUser.executeUpdate();

		prepareStatement.close();
		result.close();
		connection.close();

		/*
		 * More informatin can be found at
		 * 
		 * http://www.mkyong.com/jdbc/jdbc-preparestatement-example-create-a-table/?
		 * utm_source=mkyong&utm_medium=author&utm_campaign=related-post&utm_content=5
		 */

		/*
		 * while (result.next()) {
		 * 
		 * if (result.getString("user_id") == "ROCK") {
		 * 
		 * System.out.println("you enter the correct name"); }
		 * System.out.println("Current Date from Oracle : " +
		 * result.getString("user_id")); } System.out.println("done");
		 */
	}

}
