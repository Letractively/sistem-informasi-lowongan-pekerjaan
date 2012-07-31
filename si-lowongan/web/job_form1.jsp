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
        String id = null;

        try {
            user = (User) mySession.getAttribute("user");
            id = (String) request.getParameter("id");
        } catch (Exception e) {
            user = null;
            id = null;
        }

        Job job = null;
        JobImpl ji = new JobImpl(em);
        if (id != null) {
            job = ji.get(id);
        }

        if (user != null) {


    %>

    <head>
        <title>Admin - SI Lowongan Pekerjaan</title>
        <link href="default4.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <!-- start header -->
        <div id="header">                           
            <div id="logo">
                <h1>
                    <a href="#">SI Lowongan Pekerjaan</a></h1>
            </div>
            <%%>

            <div id="menu">
                <!--tab user-->
                <ul>
                    <li><a href="home.jsp">Home</a></li>
                    <li><a href="job_vacancies.jsp">Job Vacancies</a></li>
                    <li><a href="#">Applicants</a></li>
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
                            <form action="JobProcess" method="get">
                                <%
                                    if (id != null) {
                                        out.println("<input type=\"hidden\" name=\"txt_job_id\" value=\"" + job.getIdJob() + "\"/>");
                                    }
                                %>
                                <table border="0.5" cellspacing="17px" style="background-color: #CDCDCD; ">
                                    <tr>
                                        <td>Title</td>
                                        <%
                                            if (id != null) {
                                                out.println("<td><input type=\"text\" "
                                                        + "name=\"txt_job_title\" value=\""
                                                        + job.getJobTitle() + "\"/></td>");
                                            } else {
                                                out.println("<td><input type=\"text\" "
                                                        + "name=\"txt_job_title\" "
                                                        + "value=\"\"/></td>");
                                            }
                                        %>
                                    </tr>
                                    <tr>
                                        <td>Description</td>
                                        <%
                                            if (id != null) {
                                                out.println("<td><input type=\"text\" "
                                                        + "name=\"txt_job_desc\" value=\""
                                                        + job.getJobDescription() + "\"/></td>");
                                            } else {
                                                out.println("<td><input type=\"text\" name=\"txt_job_desc\" value=\"\"/></td>");
                                            }
                                        %>
                                    </tr>
                                    <tr>
                                        <%
                                            if (id != null) {
                                                out.println("<td colspan=\"2\"><input type=\"submit\" name=\"manage_job\" value=\"update\"/></td>");
                                            } else {
                                                out.println("<td colspan=\"2\"><input type=\"submit\" name=\"manage_job\" value=\"submit\"/></td>");
                                            }
                                        %>
                                    </tr>
                                </table>
                            </form>
                            <br>
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
