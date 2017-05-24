/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.jdbcUser;
import model.User;

import java.util.ArrayList;

public class CheckUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**Handles the HTTP <code>GET</code> method.
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**Handles the HTTP <code>POST</code> method.
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        response.setContentType("text/html; charset=UTF-8 ");
        try {
            jdbcUser tmpUser = new jdbcUser();
            Map<Long, User> lUser = tmpUser.getWithId();
            for (Map.Entry<Long, User> user : lUser.entrySet()) {
                if (login.equals(user.getValue().getLogin()) && pass.equals(user.getValue().getPass())) {
                    long loginId = user.getValue().getIdUser();
                    if (user.getValue().getType().equals("admin")) {
                        HttpSession se = request.getSession();
                        se.setAttribute("loginId", new Long (loginId));
                        RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
                        view.forward(request, response);
                    } else {
                        if (user.getValue().getType().equals("user")) {
                            HttpSession se = request.getSession();
                            se.setAttribute("loginId", new Long (loginId));
                            RequestDispatcher view = request.getRequestDispatcher("user.jsp");
                            view.forward(request, response);
                        }
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
