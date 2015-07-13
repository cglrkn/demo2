/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cgl
 */
@WebServlet(urlPatterns = {"/commentInsert"})
public class commentInsert extends HttpServlet {

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
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/cagla";
        
        String USER= "root";
        String PASS= "root";
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            java.sql.Statement stmt = conn.createStatement();
            
            //int profile_id = (int)request.getAttribute("id");
           // String user_id =request.getParameter("user_id");
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            String comment = request.getParameter("comment");
                        
            String insertSQL = "insert into comment"
                       + "(user_id,comment) values"
                       + "(?,?)";
                            
            PreparedStatement ps = conn.prepareStatement(insertSQL);
              
                               
            ps.setInt(1, user_id);
            ps.setString(2, comment);
                          
            int i = ps.executeUpdate();
                if(i == 1){
                  response.getWriter().write("done");
                }
                else{
                  out.println("failed to insert the data");
                }  
                
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            out.println("catch comments insert");
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
