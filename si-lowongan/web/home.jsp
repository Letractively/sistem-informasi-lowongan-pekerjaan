<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>


<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
                HttpSession mySession = request.getSession(true);
                String pesan = (String) mySession.getAttribute("throwMessage");
                Boolean flag = (Boolean) mySession.getAttribute("throwFlagMessage");
                User user = (User) mySession.getAttribute("user");

             
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
                    <li><a href="applicants.jsp">Applicants</a></li>
                    <li><a href="job.jsp">Job Conf</a></li>
                    <li><a href="#">Manager Conf</a></li>
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
                            <%
                                            if (flag == null) {
                                                flag = new Boolean(false);
                                            }


                                            if (!flag) {
                                                out.println("Selamat Datang " + user.getIdUser() + "..");
                                            }

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
