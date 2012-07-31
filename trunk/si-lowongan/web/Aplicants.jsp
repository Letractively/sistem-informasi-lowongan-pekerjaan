<%-- 
    Document   : Aplicants
    Created on : Jul 31, 2012, 3:22:42 PM
    Author     : wilbert
--%>
<%@page import="servlet.Manager"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="dao.mysql.JobVacancyImpl"%>
<%@page import="dao.mysql.JobImpl"%>
<%@page import="entity.Job"%>
<%@page import="java.util.List"%>
<%@page import="entity.JobVacancy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            HttpSession mySession = request.getSession();
            Manager manager = new Manager();
            EntityManager em = manager.getEntityManager();

            List<Job> listJob = new JobImpl(em).gets();

        %>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="AplicantsProses"  method="post">
            <table>
                <tr>
                    <td>Daftarkan Diri !!!</td>
                </tr>
                <tr>
                    <td>Id Applicant : <input type="text"  name="idapp"/> </td>
                </tr>
                <tr>
                    <td>First Name : <input type="text" name="firstname"/> Middle Name : <input type="text" name="middlename" /> Last Name :<input type="text" name ="lastname" /></td>
                </tr>
                <tr>
                    <td>Email : <input type="text" name="email"/></td>
                </tr>
                <tr>
                    <td>Phone : <input type="text" name="phone" /></td>
                </tr>
                <tr>
                    <td>Job Title   : 
                        <select name="vacancy">
                            <option value="all">All</option>
                            <%
                                for (Job jobTitle : listJob) {
                                    out.println("<option value=\""
                                            + jobTitle.getJobTitle() + "\">"
                                            + jobTitle.getJobTitle() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr><td><input type="submit" Value="Submit"></td></tr>

            </table>
        </form>
    </body>
</html>
