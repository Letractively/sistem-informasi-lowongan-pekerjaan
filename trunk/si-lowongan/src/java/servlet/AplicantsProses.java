/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.mysql.ApplicantsImpl;
import dao.mysql.JobImpl;
import entity.Applicants;
import entity.Job;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilbert
 */
public class AplicantsProses extends HttpServlet {

    Manager manager = new Manager();
    EntityManager em = manager.getEntityManager();
    Applicants applican;
    Job jb;
    ApplicantsImpl Api = new ApplicantsImpl(manager.getEntityManager());
    JobImpl jpm = new JobImpl(manager.getEntityManager());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
//            String pesan = "Berhasil Diinput";
            applican = new Applicants();
            jb = new Job();

            applican.setIdApplicants(request.getParameter("idapp"));
            applican.setFirstName(request.getParameter("firstname"));
            applican.setMiddleName(request.getParameter("middlename"));
            applican.setLastName(request.getParameter("lastname"));
            applican.setEmail(request.getParameter("email"));
            applican.setPhone(request.getParameter("phone"));
            Api.insert(applican);
            jb.setJobTitle(request.getParameter("vacancy"));
            jpm.insert(jb);

//            ServletContext srvCtx = getServletContext();
//            srvCtx.setAttribute("lemparMsg", pesan);
          RequestDispatcher reqDis = request.getRequestDispatcher("index.jsp");
        reqDis.include(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AplicantsProses.class.getName()).log(Level.SEVERE, null, ex);
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
