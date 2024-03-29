<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>


<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Manager"%>

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

                List<Manager> manager = new ArrayList<Manager>();
                manager = (List<Manager>) request.getAttribute("Manager");

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
                        <table border="0.5" cellspacing="7px" width="600px" style="background-color: #CDCDCD; ">
                            <tr>
                                <th>Manager ID</th>
                                <th>Manager Name</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            <%
                                        if (manager != null) {
                                            for (int i = 0; i < manager.size(); i++) {
                            %>
                            <tr>
                                <td align="center"><%= manager.get(i).getIdManager()%></td>
                                <td align="center"><%= manager.get(i).getNamaManager()%></td>
                                <td align="center"><a href="UbahManager.jsp?id=<%= manager.get(i).getIdManager()%>"><input type="button" value="Update"/></a></td>
                                <td align="center"><a href="HapusManager?id=<%= manager.get(i).getIdManager()%>" id="hapus"><input type="button" value="Delete"/></a></td>
                            </tr>
                            <%
                                            }
                                        }
                            %>
                        </table>
                        <a href="TambahManager.jsp" style="margin-left: 15px"><input type="button" value="Add Manager"/></a>
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
