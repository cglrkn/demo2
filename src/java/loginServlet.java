/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cgl
 */
@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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
         String JDBC_DRIVER="com.mysql.jdbc.Driver";
        String DB_URL="jdbc:mysql://localhost/cagla";
        
        String USER="root";
        String PASS="root";
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
            
        //out.println("entered email: " + email);
        //out.println("entered password: " + password);
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            Statement stmt = conn.createStatement();
            
            String sql;
            sql = "select id, email, password from profilepage where email='" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()==false)
            {
                out.println("empty");
            }
            else
            {
                //out.println("<font color='red'><b>asdfasdfasdfa</b></font>");
                if( rs.getString("password").equals(password))
                {
                    int id =rs.getInt("id");
                    //response.getWriter().write("password is correct");
                    request.setAttribute("id", id);
                    RequestDispatcher rd = request.getRequestDispatcher("profileServlet");
                    rd.forward(request, response);
                    
                }
                else if (rs.getString("email").isEmpty()) {
                    out.println("E-mail is not registered.");

                }
                else {
                    out.println("You have entered incorrect password");

                } 
            }
        } catch (SQLException se) {
            out.println("<font color='red'><b>catch1</b></font>");
            se.printStackTrace();
        
        } catch ( Exception e ) {
            out.println("<font color='red'><b>catch2</b></font>");
            e.printStackTrace();
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
