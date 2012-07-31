/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import dao.mysql.UserDAOImpl;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cher99
 */
public class ServletLogin extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         HttpSession mySession = request.getSession(true);
        String username = request.getParameter("usernameTextField");
        String password = request.getParameter("passwordTextField");
        String url = "";
        String message = "";
        Boolean flagMessage = false;

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("si-lowonganPU");
        EntityManager em = emf.createEntityManager();



        if (username.equals("") || password.equals("")) {
            flagMessage = true;
            message = "Please fill the form first!";
            url = "index2.jsp";
        } else {
            UserDAOImpl dao = new UserDAOImpl(em);
            try {
                User user = dao.getById(username);
                if (user == null) {
                    flagMessage = true;
                    message = "Your ID is not registered yet!";
                    url = "index2.jsp";
                } else {
                    if (!user.getPassword().equalsIgnoreCase(password)) {
                        flagMessage = true;
                        message = "The password you entered is wrong! Please try again.";
                        url = "index2.jsp";
                    } else {
                        flagMessage = false;
                        url = "index.jsp";

                        mySession.setAttribute("throwEM", em);
                        mySession.setAttribute("user", user);
                    }
                }

            } catch (Exception ex) {
                flagMessage = true;
                message = ex.getMessage();
                url = "index.jsp";
            }

            if (flagMessage) {
                mySession.setAttribute("throwMessage", message);
                mySession.setAttribute("throwFlagMessage", flagMessage);
            }

            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.include(request, response);
            try {
                /* TODO output your page here
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Login at " + request.getContextPath () + "</h1>");
                out.println("</body>");
                out.println("</html>");
                 */
            } finally {
//            out.close();
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
