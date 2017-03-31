/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.jdbcUser;
import model.User;

/**
 *
 * @author user
 */
public class AddUser extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
       
        
        
        response.setContentType("text/html; charset=UTF-8 ");
        
        RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
        view.forward(request,response);       
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
           
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String middlename = request.getParameter("middlename");
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String pass2 = request.getParameter("pass2");
        
        
        response.setContentType("text/html; charset=UTF-8 ");
        
        try {
            boolean flag=false;
            jdbcUser tmpUser = new jdbcUser();
            if (!pass.equals(pass2)){
                flag=true;
                RequestDispatcher view = request.getRequestDispatcher("registration.html");
                view.forward(request,response); 
            }
            ArrayList<User> lUser= tmpUser.getLP();
            for (User user:lUser){
                if (login.equals(user.getLogin())){
                    flag=true;
                    RequestDispatcher view = request.getRequestDispatcher("registration.html");
                    view.forward(request,response); 
                }
            }
            if (!flag){
            User newUser = new User(name,middlename,lastname,"user",login,pass);
            tmpUser = new jdbcUser();
            tmpUser.add(newUser);
            RequestDispatcher view = request.getRequestDispatcher("user.jsp");
            view.forward(request,response); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
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
