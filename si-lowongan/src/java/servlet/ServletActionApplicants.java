/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.mysql.ApplicantsImpl;
import entity.Applicants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
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
public class ServletActionApplicants extends HttpServlet {

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

        String action = (String) request.getParameter("sel_applicants_acts");
        int selIDApplicants = Integer.parseInt(request.getParameter("selApplicantsID"));
        boolean flagError = false;
        String statusSelected = "";

        HttpSession mySession = request.getSession(true);
        EntityManager em = (EntityManager) mySession.getAttribute("throwEM");

        if (action == null || selIDApplicants <= 0 || em == null) {
            flagError = true;
        } else {
            if (action.equalsIgnoreCase("0")) {
                flagError = true;
            } else {
                flagError = false;


                // begin switching action and update to db
                if (action.equalsIgnoreCase("1")) {
                    statusSelected = "Rejected";
                } else if (action.equalsIgnoreCase("2")) {
                    statusSelected = "Application Initiated";
                } else if (action.equalsIgnoreCase("3")) {
                    statusSelected = "Interview Scheduled";
                } else if (action.equalsIgnoreCase("4")) {
                    statusSelected = "Interview Passed";
                } else if (action.equalsIgnoreCase("5")) {
                    statusSelected = "Hired";
                }

                System.out.println("Status : " + statusSelected);
                System.out.println("Combobox : " + action);

                ApplicantsImpl daoApplicants = new ApplicantsImpl(em);
                Applicants currApplicants = null;
                try {
                    currApplicants = daoApplicants.get(selIDApplicants);
                } catch (Exception ex) {
                    flagError = true;
                }

                if (currApplicants != null) {
                    currApplicants.setStatus(statusSelected);
                    try {
                        daoApplicants.update(currApplicants);
                    } catch (Exception ex) {
                        flagError = true;
                    }
                }
           }
        }

        request.setAttribute("throwFlagError", flagError);
        RequestDispatcher dis = request.getRequestDispatcher("applicants.jsp");
        dis.include(request, response);

        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletActionApplicants</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletActionApplicants at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
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
