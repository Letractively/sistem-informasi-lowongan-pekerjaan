/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.mysql.JobVacancyImpl;
import entity.Job;
import entity.JobVacancy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class JobVacancyProcess extends HttpServlet {

    Manager manager = new Manager();
    EntityManager em = null;
    JobVacancy jobVacancy;
    JobVacancyImpl jvi = null;
    HttpSession mySession;

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
        mySession = request.getSession(true);
        em = (EntityManager) mySession.getAttribute("throwEM");

        jvi = new JobVacancyImpl(em);



        try {
            if (request.getParameter("manage_job_vacancy") != null) {
                try {
                    if (request.getParameter("manage_job_vacancy").toString().equals("add")) {
                        System.out.println("lewat 1");

                        response.sendRedirect("job_vacancies_form.jsp");

                    } else if (request.getParameter("manage_job_vacancy").toString().equals("delete")) {
                        System.out.println("lewat 2");
                        String[] deletes = request.getParameterValues("delete");
                        for (String str : deletes) {
                            JobVacancy jv = jvi.get(str);
                            //willy
                            jvi.delete(jv);
                        }
                        response.sendRedirect("job_vacancies.jsp");
                    } else if (request.getParameter("manage_job_vacancy").toString().equals("search")) {
                        System.out.println("lewat 3");
                        String[] objResult = new String[]{
                            request.getParameter("job_titles"),
                            request.getParameter("vacancy"),
                            request.getParameter("hiring_manager"),
                            request.getParameter("status")};
                        request.setAttribute("objectResult", objResult);
                        RequestDispatcher rd = request.getRequestDispatcher("job_vacancies.jsp");
                        rd.forward(request, response);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JobVacancyProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        PrintWriter out = response.getWriter();
        mySession = request.getSession(true);
        em = (EntityManager) mySession.getAttribute("throwEM");

        jvi = new JobVacancyImpl(em);

        System.out.println("JVI : " + jvi);
        try {
            JobVacancy jv = null;
            if (request.getParameter("id") != null) {
                jv = jvi.get(request.getParameter("id").toString());
            }
//            out.println(request.getParameter("id").toString() + " sdfg");
            request.setAttribute("jobVacancy", jv);
            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("job_vacancies_form.jsp");
            rd.include(request, response);
        } catch (Exception ex) {
            Logger.getLogger(JobVacancyProcess.class.getName()).log(Level.SEVERE, null, ex);
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
        PrintWriter out = response.getWriter();
//        if (request.getParameter("submit_job_vacancy").equals("submit")) {
        try {
//            boolean save = true;
            int pos = -1;
            String errorMsg = "";

            jobVacancy = new JobVacancy();
            Date date = new Date();

            if (request.getParameter("txt_vacancy_name").isEmpty()
                    || request.getParameter("txt_description").isEmpty()
                    || request.getParameter("txt_number_pos").isEmpty()) {
                errorMsg = "Please complete field";
                request.setAttribute("errorMsg", errorMsg);
                RequestDispatcher rd = request.getRequestDispatcher("job_vacancies_form.jsp");
                rd.forward(request, response);
            } else {
                try {
                    pos = Integer.parseInt(request.getParameter("txt_number_pos"));
                    jobVacancy.setTitleVacancy(request.getParameter("txt_vacancy_name"));
                    Job job = new Job();
                    job.setIdJob(Integer.parseInt(request.getParameter("job_titles")));
                    jobVacancy.setIdJob(job);
                    entity.Manager mgr = new entity.Manager();
                    mgr.setIdManager(Integer.parseInt(request.getParameter("hiring_manager")));
                    jobVacancy.setIdManager(mgr);
                    jobVacancy.setNumberPosition(Integer.parseInt(request.getParameter("txt_number_pos")));
                    jobVacancy.setDescription(request.getParameter("txt_description"));
                    jobVacancy.setPostDate(date);

                    String[] active = request.getParameterValues("cbx_active");
                    if (active != null) {
                        jobVacancy.setStatus("Active");
                    } else {
                        jobVacancy.setStatus("Inactive");
                    }

                    JobVacancyImpl jvi = new JobVacancyImpl(manager.getEntityManager());
                    if (request.getParameter("id_job_vacancy") != null) {
                        jobVacancy.setIdJobVacancy(Integer.parseInt(request.getParameter("id_job_vacancy")));
                        jvi.update(jobVacancy);
                    } else {
                        jvi.insert(jobVacancy);
                    }
                    response.sendRedirect("job_vacancies.jsp");
                } catch (Exception ex) {
                    errorMsg = "Please use number for number position";
                    request.setAttribute("errorMsg", errorMsg);
                    RequestDispatcher rd = request.getRequestDispatcher("job_vacancies_form.jsp");
                    rd.forward(request, response);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(JobVacancyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

//        }
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
