package yoonseop_lee_sec008_comp228_exercise2A;
// Fig. 24.29: JdbcRowSetTest.java
// Displaying the contents of the Authors table using JdbcRowSet.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;    
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetTest {
	//.. For local access i.e. within the college network, use the following:
   private static final String DATABASE_URL = "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD";
   
// For remote access i.e. outside the college network, use the following:
// final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
   
   private static final String USERNAME = "COMP214_F19_zor_50";
   private static final String PASSWORD = "password";
   
   public static void main(String args[]) {
      // connect to database books and query database
      try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet())            
          {

         // specify JdbcRowSet properties 
         rowSet.setUrl(DATABASE_URL);                            
         rowSet.setUsername(USERNAME);                           
         rowSet.setPassword(PASSWORD);                           
         rowSet.setCommand("SELECT FirstName, LastName FROM authors where authorid > 3 ORDER BY FirstName"); 
         rowSet.execute(); // execute query                      

         // process query results
         ResultSetMetaData metaData = rowSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
         System.out.printf("Authors Table of Books Database:%n%n");

         // display rowset header
         for (int i = 1; i <= numberOfColumns; i++) {
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         }
         System.out.println();
         
         // display each row
         while (rowSet.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {
               System.out.printf("%-8s\t", rowSet.getObject(i));
            }
            System.out.println();
         } 
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         System.exit(1);
      } 
   } 
}

