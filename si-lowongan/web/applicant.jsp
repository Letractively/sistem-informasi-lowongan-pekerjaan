<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>


<%@page import="dao.mysql.JobVacancyImpl"%>
<%@page import="entity.JobVacancy"%>
<%@page import="dao.mysql.JobImpl"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="servlet.Manager"%>
<%@page import="java.util.List"%>
<%@page import="entity.Job"%>
<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%


    %>
    <%

                //String pesan = (String) request.getAttribute("throwMessage");
                /*HttpSession mySession = request.getSession(true);
                String pesan = (String) mySession.getAttribute("throwMessage");
                Boolean flag = (Boolean) mySession.getAttribute("throwFlagMessage");
                User user = (User) mySession.getAttribute("user");*/

                Manager manager = new Manager();
                EntityManager em = manager.getEntityManager();
                List<JobVacancy> listJob = new JobVacancyImpl(em).gets();


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
                        <h2 class="title" style="margin-left: 30px;">
                            Please Input Field </h2>
                        <%
                                    if (request.getAttribute("Status") != null) {
                                        out.println("<h2 style='margin-left: 30px;color: red;'>" + request.getAttribute("Status") + "</h2>");
                                    }
                        %>
                        <form action="AplicantProses" method="post"  enctype="multipart/form-data" >
                            <table border="0" style="margin-left: 30px;" cellpadding="2" cellspacing="2">
                                <tr>
                                    <td>FirstName</td>
                                    <td><input type="text" name="firstname"></td>
                                </tr>
                                <tr>
                                    <td>MiddleName</td>
                                    <td><input type="text" name="lastname"></td>
                                </tr>
                                <tr>
                                    <td>LastName</td>
                                    <td><input type="text" name="middlename"></td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td><input type="text" name="email"></td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td><input type="text" name="phone"></td>
                                </tr>
                                <tr>
                                    <td>Job Title</td>
                                    <td> <select name="vacancy">
                                            <option value="all">All</option>
                                            <%
                                                        for (JobVacancy job : listJob) {
                                                            out.println("<option value='"
                                                                    + job.getIdJobVacancy() + "'>"
                                                                    + job.getTitleVacancy() + "</option>");
                                                        }
                                            %>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Resume</td>
                                    <td><input type="file" name="fileSelect"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="submit" value="Submit"></td>
                                </tr>
                            </table>
                        </form>

                        <h2 class="title">
                            <%
                                        //  if (flag == null) {
                                        //    flag = new Boolean(false);
                                        // }


                                        //if (!flag) {
                                        //  out.println("Selamat Datang " + user.getIdUser() + "..");
                                        // }

                            %>
                        </h2>

                        <div class="entry">
                            <p>
                                <br>


                                <br>
                            </p>
                        </div>
                    </div>
                </div>                
            </div>
        </div>

    </body>
</html>
