<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Cher99
--%>


<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entity.Applicants"%>
<%@page import="java.util.List"%>
<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
                HttpSession mySession = request.getSession(true);
                List<Applicants> listApplicants = (List<Applicants>) mySession.getAttribute("listApplicants");
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
                            Applicants
                        </h2>

                        <div class="entry">
                            <p>
                                <%

                                            if (listApplicants == null) {
                                                out.println("buruk kosong");
                                            } else {
                                                out.println("tidak kosong!");
                                            }

                                %>
                                <!-- Kerja dari sini -->
                            <table border="0.5" cellspacing="17px" style="background-color: #CDCDCD; ">

                                <tr>
                                    <td><b><u>Vacancy</u></b></td>
                                    <td><b><u>Candidate</u></b></td>
                                    <td><b><u>Hiring Manager</u></b></td>
                                    <td><b><u>Date of Application</u></b></td>
                                    <td><b><u>Status</u></b></td>
                                    <td><b><u>Resume</u></b></td>

                                    <td colspan="3" align="center"><b><u>Action</u></b></td>
                                </tr>
                                <%
                                            String fullName = "";
                                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                            String[] actionCombo = {"--Action--", "Applying", "Interview", "Pass Interview", "Hire"};
                                            for (Applicants a : listApplicants) {
                                                out.print("<tr><td>" + a.getIdJobVacancy().getTitleVacancy() + "</td>");

                                                fullName = a.getFirstName();
                                                if (a.getMiddleName() != null) {
                                                    fullName += " " + a.getMiddleName();
                                                }

                                                if (a.getLastName() != null) {
                                                    fullName += " " + a.getLastName();
                                                }
                                                out.print("<tr><td>" + fullName + "</td>");


                                                out.print("<td>" + a.getIdJobVacancy().getIdManager().getNamaManager() + "</td>");
                                                out.print("<td>" + formatter.format(a.getDateApply()) + "</td>");
                                                out.print("<td>" + a.getStatus() + "</td>");
                                                out.print("<td>download</td>");
                                                /*out.print("<td colspan=\"2\"><select name=\"sel_applicants_acts\">"

                                                for (int i = 0; i < actionCombo.length; i++) {
                                                     out.println("<option value='" + i + "'>"
                                                            + actionCombo[i] + "</option>");
                                                }*/
                                                out.print("</select></td>");



                                                out.print("<td ><a href=\"ServletActionApplicants?id_applicants=" + a.getIdApplicants() + "&&action=0\">REJECT</a></td>");
                                                out.print("<td><a href=\"ServletActionApplicants?id_applicants=" + a.getIdApplicants() + "&&action=1\">APPLYING</a></td>");
                                                out.print("<td><a href=\"ServletActionApplicants?id_applicants=" + a.getIdApplicants() + "&&action=1\">APPLYING</a></td>");
                                                out.print("<td><a href=\"ServletDecline?id=" + i.getId() + "\">DECLINE</a></td></tr>");

                                            }
                                %>
                            </table>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
