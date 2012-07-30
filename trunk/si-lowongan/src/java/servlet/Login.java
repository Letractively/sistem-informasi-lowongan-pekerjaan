/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.mysql.UserDAOImpl;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WILLIAM
 */
public class Login extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Manager manager = new Manager();
    EntityManager em;
    List<User> listUser;
    UserDAOImpl userDao;
    User user;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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

        RequestDispatcher rd = null;
        HttpSession mySession;
        boolean validasiLogin = true;
        boolean emptyField = false;
        String warning = "";

        user = new User();
        user.setIdUser(request.getParameter("id_user"));
        user.setPassword(request.getParameter("password"));
        if (user.getIdUser().equals("")) {
            emptyField = true;
            warning = "Username cannot be empty";
        } else if (user.getPassword().equals("")) {
            emptyField = true;
            warning = "Password cannot be empty";
        }

        if (emptyField) {
            request.setAttribute("emptyField", emptyField);
            request.setAttribute("warning", warning);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }

        em = manager.getEntityManager();
        userDao = new UserDAOImpl(em);
        try {
            listUser = userDao.gets();
            if (listUser.size() > 0) {
                for (User u : listUser) {
                    if (user.getIdUser().equals(u.getIdUser())
                            && user.getPassword().equals(u.getPassword())) {
                        mySession = request.getSession(true);
                        validasiLogin = true;
                        request.setAttribute("validasiLogin", validasiLogin);
                        mySession.setAttribute("user", user);
                        rd = request.getRequestDispatcher("index.jsp");
                        break;
                    } else {
                        validasiLogin = false;
                        request.setAttribute("validasiLogin", validasiLogin);
                        rd = request.getRequestDispatcher("index.jsp");
                    }
                }
                rd.forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

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
