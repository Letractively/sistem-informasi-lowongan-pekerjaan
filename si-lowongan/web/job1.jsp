<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>

<%@page import="dao.mysql.JobImpl"%>
<%@page import="java.util.List"%>
<%@page import="entity.Job"%>
<%@page import="servlet.Manager"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
        HttpSession mySession = request.getSession();
        User user;

        Manager manager = new Manager();
        EntityManager em = manager.getEntityManager();

        try {
            user = (User) mySession.getAttribute("user");
        } catch (Exception e) {
            user = null;
        }

        if (user != null) {
            List<Job> listJob = new JobImpl(em).gets();
    %>

    <head>
        <title>Admin - SI Job Vacancy</title>
        <link href="default4.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <!-- start header -->
        <div id="header">
            <div id="logo">
                <h1>
                    <a href="#">SI Job Vacancy</a></h1>
            </div>
            <%%>

            <div id="menu">
                <!--tab user-->
                <ul>
                    <li><a href="home.jsp">Home</a></li>
                    <li><a href="job_vacancies.jsp">Job Vacancies</a></li>
                    <li><a href="applicants.jsp">Applicants</a></li>
                    <li><a href="job1.jsp">Job Conf</a></li>
                    <li><a href="ManageManager">Manager Conf</a></li>
                    <li><a href="#">Company Info</a></li>
                    <li><a href="Logout">Logout</a></li>

                </ul>

            </div>
        </div>

        <hr />
        <!-- end header -->
        <div id="wrapper">

            <div id="page">
                <!-- start content -->
                <div id="content">
                    <div class="post">
                        <h2 class="title">
                            Job
                        </h2>
                        <div class="entry">
                            <p>
                                <br>
                            <form action="JobProcess">
                                <table>
                                    <tr>
                                        <td><input type="submit" name="manage_job" value="Add"/></td>
                                        <td><input type="submit" name="manage_job" value="Delete"/></td>
                                    </tr>
                                </table>
                                <table border="0.5" cellspacing="17px" style="background-color: #CDCDCD; ">
                                    <tr>
                                        <td><input type="checkbox" name="" value="" disabled/></td>
                                        <td>Title</td>
                                        <td>Description</td>
                                    </tr>
                                    <%
                                        for (Job job : listJob) {
                                            out.println("<tr>");
                                            out.println("<td><input type=\"checkbox\" "
                                                    + "name=\"delete\" value=\"" + job.getIdJob() + "\"/></td>");
                                            out.println("<td><a href=job_form1.jsp?id="
                                                    + job.getIdJob() + ">" + job.getJobTitle() + "</a></td>");
                                            out.println("<td>" + job.getJobDescription() + "</td>");
                                            out.println("</tr>");
                                        }
                                    %>
                                </table>
                            </form>

                            <br>
                            </p>
                        </div>
                    </div>
                </div>                
            </div>
        </div>

    </body>
    <%
        } else {
            response.sendRedirect("index.jsp");
        }
    %>
</html>
