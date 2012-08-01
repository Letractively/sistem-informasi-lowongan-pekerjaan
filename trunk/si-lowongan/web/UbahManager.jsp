<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>


<%@page import="dao.mysql.ManagerImpl"%>
<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Manager"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
                HttpSession mySession = request.getSession(true);
                String pesan = (String) mySession.getAttribute("throwMessage");
                Boolean flag = (Boolean) mySession.getAttribute("throwFlagMessage");
                User user = (User) mySession.getAttribute("user");

                  if (user == null){
                    response.sendRedirect("login.jsp");
                }else{

                EntityManagerFactory emf =
                        Persistence.createEntityManagerFactory("si-lowonganPU");
                EntityManager em = emf.createEntityManager();


                Manager m = new Manager();
                m = new ManagerImpl(em).get(Integer.parseInt(request.getParameter("id")));


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
                            Manager Configuration
                        </h2>
                        <%
                                    if (request.getAttribute("Status") != null) {
                                        out.println("<h2 class=\"title\" style=\"color: red\">" + request.getAttribute("Status") + "</h2>");
                                    }
                        %>
                        <form action="UbahManager" method="POST"border="0.5" cellspacing="17px" width="600px" style="background-color: #CDCDCD; ">
                            <table cellpadding="2" cellspacing="2">
                                <tr>
                                    <td>Manager Name</td>
                                    <td>:</td>
                                    <td><input type="text" name="Nama" value="<%= m.getNamaManager()%>" /></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="hidden" name="Id" value="<%= m.getIdManager()%>"></td>
                                    <td><input type="submit" value="Update"/></td>
                                </tr>
                            </table>
                        </form>
                        <h2 class="title">
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
    <%}%>
</html>
