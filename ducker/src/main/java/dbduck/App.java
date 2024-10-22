package dbduck;

/**
 * Hello world!
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        /** 
         * 
         * Recieve duckdb file name 
         * Use it to query 
         * return the output 
         * 
         * 
         ***/

        try{
            System.out.println("Initiating connection .............");
            Connection conn = DriverManager.getConnection("jdbc:duckdb:/home/blesson/duckDB/dbFiles/test_db.duckdb");
            Statement stmt = conn.createStatement();
            System.out.println("Creating Tables .............");
            stmt.execute("CREATE TABLE items (item VARCHAR, value DECIMAL(10, 2), count INTEGER)");
            // insert two items into the table
            System.out.println("Inserting value to tables .............");
            stmt.execute("INSERT INTO items VALUES ('jeans', 20.0, 1), ('hammer', 42.2, 2)");
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM items")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getInt(3));
                    }
            }
            System.out.println("Closing Connection !!!!");
            stmt.close();
            }catch (SQLException e) {
                System.out.println("An error occurred while connecting to the database: " + e.getMessage());
                e.printStackTrace();
                }
    }
}
