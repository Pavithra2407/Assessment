import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ConnectionManager {
    
	//static variable reference for Connection object 
	 static Connection conn = null;
    //Constuctor is private, hence no one outside this class can create an object of this class
    private ConnectionManager() {
        
    }
    
    //static method to create one instance which will be shared only by this method
    public static Connection getConnection() throws ClassNotFoundException   {
        
        try {
            
            if(conn == null) {
                
                Class.forName("com.mysql.jdbc.Driver");
                 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop","root","Pavi@12345");
            }    
                
            }catch(SQLException e) {
                
            e.printStackTrace();
            }
    
        return conn;
        }
    
   }
    
public class ConnectionManagerTest {

public static void main(String args[]) throws  ClassNotFoundException {
        
        Connection conn = ConnectionManager.getConnection();
        
        try {
            String query = "Select * from ebookshop.books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t"+rs.getString(5));
                }

       } catch(Exception e){
            System.out.println("Database connection failed");
            
        }
        
    }
}