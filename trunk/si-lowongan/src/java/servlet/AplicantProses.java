package servlet;

import dao.IJobVacancyDAO;
import dao.mysql.ApplicantsImpl;
import dao.mysql.JobImpl;
import dao.mysql.JobVacancyImpl;
import entity.Applicants;
import entity.Job;
import entity.Manager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AplicantProses extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("si-lowonganPU");
        EntityManager em = emf.createEntityManager();

        Job jb = new Job();
        Applicants app = new Applicants();

        IJobVacancyDAO jobVacancy = new JobVacancyImpl(em);
        ApplicantsImpl ApI = new ApplicantsImpl(em);

        String uploadTo = getServletContext().getRealPath("/") + "file\\";



        Date today = new Date();

        String firstname = "";
        String middlename = "";
        String lastname = "";
        String email = "";
        String phone = "";
        String Keyword = "";
        String Comment = "";
        String Status = "";
        String Method = "";
        String Vacancy = "";
        String resume = "";
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            // no multipart foirm
            if (!isMultipart) {
            } // multipart form
            else {
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

                // parse requests
                List<FileItem> fileItems = upload.parseRequest(request);

                for (FileItem fileItem : fileItems) {
                    // a regular form field
                    if (fileItem.isFormField()) {
                        if (fileItem.getFieldName().equals("firstname")) {
                            firstname = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("middlename")) {
                            middlename = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("lastname")) {
                            lastname = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("vacancy")) {
                            Vacancy = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("email")) {
                            email = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("phone")) {
                            phone = fileItem.getString();
                        }
                    } // upload field
                    else {
                        String fileName = fileItem.getName();
                        resume = fileName;
                        File fileTo = new File(uploadTo + fileName);
                        fileItem.write(fileTo);
                    }
                }

                app.setFirstName(firstname);
                app.setMiddleName(middlename);
                app.setLastName(lastname);
                app.setEmail(email);
                app.setPhone(phone);
                app.setKeyword(Keyword);
                app.setComment(Comment);
                app.setDateApply(today);
                app.setStatus(Status);
                app.setMethod(Method);
                app.setResume(resume);
                app.setIdJobVacancy(jobVacancy.get(Vacancy));
                ApI.insert(app);
            }

        } catch (Exception ex) {
            Logger.getLogger(AplicantProses.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        RequestDispatcher rq = request.getRequestDispatcher("applicant.jsp");
        rq.forward(request, response);
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
