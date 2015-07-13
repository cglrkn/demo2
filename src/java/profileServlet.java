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
@WebServlet(urlPatterns = {"/profileServlet"})
public class profileServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
         String JDBC_DRIVER="com.mysql.jdbc.Driver";
        String DB_URL="jdbc:mysql://localhost/cagla";
        
        String USER="root";
        String PASS="root";
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            java.sql.Statement stmt = conn.createStatement();
            
            int id = Integer.parseInt(request.getAttribute("id").toString());
            String sql = "select * from profilepage where id='" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            //String ad = rs.getString("first");
            //String soyad = rs.getString("soyad");
            int flag=0;
            List<profileClass> profile = new ArrayList<>();
            if(  rs.next()== true ) {
                flag = 1;
            
                profileClass prof = new profileClass();
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                
                
                /*int id = rs.getInt("id");
                int user_id = rs.getInt("firstName");
                int writer_id  = rs.getInt("lastName");*/
                //String comment = rs.getString("comment");
                //int comment_reply_id = rs.getInt("comment_reply_id");
                
                prof.setEmail(email);
                prof.setId(id);
                prof.setFirstName(firstName);
                prof.setLastName(lastName);
                
                profile.add(prof);
                
            }
            if(flag==1) {
                
                Result res = Result.SUCCESS.setContent(profile);
                Gson gs = new Gson();
                out.print(gs.toJson(res));
            }
            else{
                out.println("no");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            out.println("catch profile get");
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
