package servlet;

import dao.mysql.ApplicantsImpl;
import dao.mysql.JobImpl;
import entity.Applicants;
import entity.Job;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilbert
 */
public class AplicantProses extends HttpServlet {

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

        Manager manager = new Manager();
        EntityManager em = manager.getEntityManager();

        Job jb = new Job();
        Applicants app = new Applicants();

        JobImpl Jbi = new JobImpl(manager.getEntityManager());
        ApplicantsImpl ApI = new ApplicantsImpl(manager.getEntityManager());

        Date today = new Date();
        //Byte op[]=0;
        try {
            app.setIdApplicants(1);
            app.setFirstName(request.getParameter("firstname"));
            app.setMiddleName(request.getParameter("middlename"));
            app.setLastName(request.getParameter("lastname"));
            app.setEmail(request.getParameter("email"));
            app.setPhone(request.getParameter("phone"));
            //app.setResume(Byte.parseByte("dfas"));
            app.setKeyword("");
            app.setComment("");
            app.setDateApply(today);
            app.setStatus("");
            app.setMethod("");
            ApI.insert(app);
//             app.setFirstName("wilbert");
//            app.setMiddleName("gaah");
//            app.setLastName("sadfjk");
//            app.setEmail("nkdf");
//            app.setPhone("asdf");
//            //app.setResume(Byte.parseByte("dfas"));
//            app.setKeyword("");
//            app.setComment("");
//            app.setDateApply(today);
//            app.setStatus("");
//            app.setMethod("");
//            ApI.insert(app);
            
            
            jb.setJobTitle(request.getParameter("vacancy"));
            Jbi.insert(jb);
            
            //STr
            RequestDispatcher rq = request.getRequestDispatcher("applicant.jsp");
            rq.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AplicantProses.class.getName()).log(Level.SEVERE, null, ex);
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
