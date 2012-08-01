<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Cher99
--%>


<%@page import="dao.mysql.ApplicantsImpl"%>
<%@page import="javax.persistence.EntityManager"%>
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
                User user = (User) mySession.getAttribute("user");
                if (user == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    EntityManager em = (EntityManager) mySession.getAttribute("throwEM");
                    ApplicantsImpl daoApplicants = new ApplicantsImpl(em);
                    List<Applicants> listApplicants = daoApplicants.gets();


                    Boolean flagError = (Boolean) request.getAttribute("throwFlagError");
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
                        <h2 class="title" >
                            Applicants
                        </h2>

                        <div class="entry">
                            <p>
                                <%
                                                    if (flagError != null) {
                                                        if (!flagError) {
                                                            out.println("<h2 class=\"title\" style=\"color: red\">Status updated!</h2>");
                                                        }
                                                    }
                                %>
                                <!-- Kerja dari sini -->
                            <table border="0.5" cellspacing="17px" width="600px" style="background-color: #CDCDCD; ">

                                <tr>
                                    <td><b><u>Vacancy</u></b></td>
                                    <td><b><u>Candidate</u></b></td>
                                    <td><b><u>Hiring Manager</u></b></td>
                                    <td><b><u>Date of Application</u></b></td>
                                    <td><b><u>Status</u></b></td>
                                    <td><b><u>Resume</u></b></td>

                                    <td colspan="2" align="center"><b><u>Action</u></b></td>
                                </tr>
                                <%
                                                    String fullName = "";
                                                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                                    String[] actionCombo = {"--Action--", "Reject", "Applying", "Interview", "Pass Interview", "Hire"};
                                                    for (Applicants a : listApplicants) {
                                                        out.print("<tr><td>" + a.getIdJobVacancy().getTitleVacancy() + "</td>");

                                                        fullName = a.getFirstName();
                                                        if (a.getMiddleName() != null) {
                                                            fullName += " " + a.getMiddleName();
                                                        }

                                                        if (a.getLastName() != null) {
                                                            fullName += " " + a.getLastName();
                                                        }
                                                        out.print("<td>" + fullName + "</td>");


                                                        out.print("<td>" + a.getIdJobVacancy().getIdManager().getNamaManager() + "</td>");
                                                        out.print("<td>" + formatter.format(a.getDateApply()) + "</td>");
                                                        out.print("<td>" + a.getStatus() + "</td>");
                                                        out.print("<td><a href='download.jsp?resume=" + a.getResume() + "'>download</a></td>");
                                                        out.print("<form action=\"ServletActionApplicants\"><td><select name=\"sel_applicants_acts\">");

                                                        for (int i = 0; i < actionCombo.length; i++) {
                                                            out.print("<option value='" + i + "'>"
                                                                    + actionCombo[i] + "</option>");
                                                        }
                                                        out.print("</select></td>");


                                                        out.print("<td ><input type=\"hidden\" name=\"selApplicantsID\" value=\"" + a.getIdApplicants() + "\" /><input type=\"submit\" value=\"Go\" /></td></form></tr>");
                                                    }
                                %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <%}%>
</html>