
package fifa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class My_Cnx {
   /* private static String servername="localhost";
    private static String username="root";
    private static String dbname="users_db";
    private static Integer portnumber =3306;
    private static String password="";
*/
    public static Connection getConnection(){
       Connection cnx = null;  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/users_db","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return cnx;
    }
    
}
