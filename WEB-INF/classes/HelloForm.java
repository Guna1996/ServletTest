import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloForm extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + title + "</h1>\n" +
               "<ul>\n" +
                  "  <li><b>First Name</b>: "
                  + request.getParameter("first_name") + "\n" +
                  "  <li><b>Last Name</b>: "
                  + request.getParameter("last_name") + "\n" +
               "</ul>\n" 
        );
      
        String n = request.getParameter("first_name");
        String p = request.getParameter("last_name");
        try {  
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/dbtest?verifyServerCertificate=false&useSSL=true","root","Guna@1996");    //test1 is dbname,   root is dbusername and dbpassword
            Statement s = c.createStatement();
       	    // s.execute("create table users(fname text(16),lname text(6))");
             String query1 = "INSERT INTO users " + "VALUES ('"+n+"', '"+p+"')";
              s.executeUpdate(query1);
            ResultSet rs = s.executeQuery("select * from users"); 
            while (rs.next()) {    
                out.println("\n<table><tr><td>" + rs.getString("fname") + "</td><td>" + rs.getString("lname") + "</td></tr></table>" +
            "</body>" +
         "</html>");   
            }  
            System.out.println("Table updated");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    } 
}