package yoonseop_lee_sec008_comp228_exercise1B;
// Fig. 24.23: DisplayAuthors.java
// Displaying the contents of the Authors table.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DisplayAuthors {
   public static void main(String args[]) {
      // For local access, use the following:
	   //final String DATABASE_URL = "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD";
	   
	// For remote access i.e. outside the college network, use the following:
	   final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
      
	   final String SELECT_QUERY =  "SELECT isbn, title FROM titles where editionNumber > 6 and editionNumber < 10 ORDER BY isbn";
      // use try-with-resources to connect to and query the database
      try (                                                        
         // Connection connection = DriverManager.getConnection(DATABASE_URL, "deitel", "deitel");                     
    	Connection connection = DriverManager.getConnection(DATABASE_URL, "COMP214_F19_zor_51", "password"); 
         Statement statement = connection.createStatement();       
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY)
         ) 
           {

         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Authors Table of Books Database:%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++) {
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         }
         System.out.println();
         
         // display query results
         while (resultSet.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {
               System.out.printf("%-8s\t", resultSet.getObject(i));
            }
            System.out.println();
         } 
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
      }                                                   
   } 
} 



 