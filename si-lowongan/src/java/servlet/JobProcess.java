/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.mysql.JobImpl;
import entity.Job;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author WILLIAM
 */
public class JobProcess extends HttpServlet {

    Manager manager = new Manager();
    EntityManager em = manager.getEntityManager();
    Job job;
    JobImpl ji = new JobImpl(em);

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
        try {
            if (request.getParameter("manage_job").equals("Add")) {
                response.sendRedirect("job_form1.jsp");
            } else if (request.getParameter("manage_job").equals("Delete")) {
                String[] deletes = request.getParameterValues("delete");
                for (String str : deletes) {
                    job = ji.get(str);
                    ji.delete(job);
                }
                response.sendRedirect("job1.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(JobProcess.class.getName()).log(Level.SEVERE, null, ex);
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
        String url = "";
        RequestDispatcher rd = null;
        try {
            if (request.getParameter("txt_job_title").equals("")
                    || request.getParameter("txt_job_desc").equals("")) {
                url = "job_form1.jsp";
                String emptyField = "Field cannot be empty";
                request.setAttribute("fieldEmpty", emptyField);
            } else {
                job = new Job();
                if (request.getParameter("manage_job").equals("update")) {
                    job.setIdJob(Integer.parseInt(request.getParameter("txt_job_id")));
                }
                job.setJobTitle(request.getParameter("txt_job_title"));
                job.setJobDescription(request.getParameter("txt_job_desc"));
                try {
                    if (request.getParameter("manage_job").equals("update")) {
                        ji.update(job);
                    } else {
                        ji.insert(job);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JobProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("job1.jsp");
            }

            rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception ex) {
        }
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
