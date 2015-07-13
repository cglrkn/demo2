/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cgl
 */
@WebServlet(urlPatterns = {"/commentServlet"})
public class commentServlet extends HttpServlet {

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
            String profile_id_string = request.getParameter("id");
            int profile_id = Integer.parseInt(profile_id_string);
            String sql = "select * from comments where profile_id='" + profile_id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            //String ad = rs.getString("first");
            //String soyad = rs.getString("soyad");
            List<comments> commArr = new ArrayList<>();
            while(rs.next()) {
                comments comm = new comments();

                
                int writer_id = rs.getInt("writer_id");
                String comment = rs.getString("comment");
                int comment_reply_id = rs.getInt("comment_reply_id");
                comm.setUser_id(profile_id);
                comm.setWriter_id(writer_id);
                comm.setComment(comment);
                comm.setComment_reply_id(comment_reply_id);
                commArr.add(comm);
                
            }
            Gson gs = new Gson();
            out.print(gs.toJson(commArr));
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            out.println("catch profile");
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
