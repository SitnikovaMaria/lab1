package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.jdbcCopyOfBook;

public class ChangeCopyOfBook extends HttpServlet {

    /**Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeCopyOfBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeCopyOfBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**Handles the HTTP <code>GET</code> method.
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

    /**Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inventoryNumber = request.getParameter("inventoryNumber");
        String idBook = request.getParameter("idBook");
        String issue = request.getParameter("issue");
        String idUser = request.getParameter("idUser");
        Logger.getLogger(ChangeCopyOfBook.class.getName()).log(Level.SEVERE, "!!!1 issue:" + issue);
        Logger.getLogger(ChangeCopyOfBook.class.getName()).log(Level.SEVERE, "!!!1 idUser:" + idUser);
        try {
            jdbcCopyOfBook tmpCopyOfBook= new jdbcCopyOfBook();
            if (issue.equals("0")){
                idUser = "1";
            }
            if (idUser.equals("1")&&(issue.equals("1"))){
                issue = "0";
            }
            tmpCopyOfBook.change(inventoryNumber,idBook,issue,idUser);
            RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
            view.forward(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(ChangeCopyOfBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangeCopyOfBook.class.getName()).log(Level.SEVERE, null, ex);
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
