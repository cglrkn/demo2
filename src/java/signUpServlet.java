/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cgl
 */
@WebServlet(urlPatterns = {"/signUpServlet"})
public class signUpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        
        String JDBC_DRIVER="com.mysql.jdbc.Driver";
        String DB_URL="jdbc:mysql://localhost/cagla";
        
        String USER="root";
        String PASS="root";
        
        response.setContentType("text/html");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            Statement stmt = conn.createStatement();
            
            String sql;
            sql = "select id, email from profilepage where email='" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            
            if( rs.next()==false ) {
              // out.println("adfasdfasdfasdfa");
               /*out.println("  firstName: " + firstName
                         + "  lastName: " + lastName
                         + "  email: " + email
                         + "  day: " + day
                         + "  month: " + month
                         + "  year: " + year
                         + "  password: " + password
                         + "  gender: " + gender);
            */
               String insertSQL = "insert into profilepage"
                       + "(email, firstname,lastname, gender,password) values"
                       + "(?,?,?,?,?)";
               
                
                PreparedStatement ps = conn.prepareStatement(insertSQL);
                
                
                ps.setString(1, email);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, "M");
                ps.setString(5, password);
               
                int i = ps.executeUpdate();
                if(i == 1){
                  response.getWriter().write("done");
                }
                else{
                  out.println("failed to insert the data");
                 }   
                
            }
            else { 
                out.println("E-mail is in use");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            out.println(ex.getMessage().toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
