<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>

<%@page import="dao.mysql.JobVacancyImpl"%>
<%@page import="entity.JobVacancy"%>
<%@page import="dao.mysql.ApplicantsImpl"%>
<%@page import="entity.Applicants"%>
<%@page import="dao.mysql.ManagerImpl"%>
<%@page import="servlet.Manager"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="dao.mysql.JobImpl"%>
<%@page import="entity.Job"%>
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
                String pesan = (String) mySession.getAttribute("throwMessage");
                Boolean flag = (Boolean) mySession.getAttribute("throwFlagMessage");
                User user = (User) mySession.getAttribute("user");

                try {
                    user = (User) mySession.getAttribute("user");
                } catch (Exception e) {
                    user = null;
                }

                Manager manager = new Manager();
                EntityManager em = manager.getEntityManager();

                if (user != null) {
                    List<Job> listJob = new JobImpl(em).gets();
                    List<entity.Manager> listManager = new ManagerImpl(em).gets();
                    List<JobVacancy> listJobVacancy = new JobVacancyImpl(em).gets();
                    String[] objResult = null;
                    try {
                        objResult = (String[]) request.getAttribute("objectResult");
                    } catch (Exception ex) {
                    }

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
                            Job Vacancy
                        </h2>

                        <div class="entry">
                            <p>
                                <br>
                            <form action="JobVacancyProcess">

                                <table border="0.5" cellspacing="14px" style="background-color: #CDCDCD; ">
                                    <tr>
                                        <td><b>Job Title</b></td>
                                        <td>
                                            <select name="job_titles">
                                                <option value="all">All</option>
                                                <%
                                                            for (Job job : listJob) {
                                                                out.println("<option value='" + job.getIdJob() + "'>"
                                                                        + job.getJobTitle() + "</option>");
                                                            }
                                                %>
                                            </select>
                                        </td>
                                        <td>
                                            <b>Vacancy</b>
                                        </td>
                                        <td>
                                            <select name="vacancy">
                                                <option value="all">All</option>
                                                <%
                                                            for (JobVacancy jobVacancy : listJobVacancy) {
                                                                out.println("<option value=\""
                                                                        + jobVacancy.getTitleVacancy() + "\">"
                                                                        + jobVacancy.getTitleVacancy() + "</option>");
                                                            }
                                                %>
                                            </select>
                                        </td>
                                        <td>
                                            <b>Hiring Manager</b>
                                        </td>
                                        <td>
                                            <select name="hiring_manager">
                                                <option value="all">All</option>
                                                <%
                                                            for (entity.Manager mgr : listManager) {
                                                                out.println("<option value='" + mgr.getIdManager() + "'>"
                                                                        + mgr.getNamaManager() + "</option>");
                                                            }
                                                %>
                                            </select>
                                        </td>
                                        <td>
                                            <b> Status</b>
                                        </td>
                                        <td>
                                            <select name="status">
                                                <option value="all">All</option>
                                                <option value="Active">Active</option>
                                                <option value="Inactive">Inactive</option>
                                            </select>
                                        </td>
                                        <td><input type="submit" name="manage_job_vacancy" value="search"/></td>
                                    </tr>
                                </table>
                            </form>
                            <form action="JobVacancyProcess">
                                <table border="0">
                                    <tr>
                                        <td><input type="submit" name="manage_job_vacancy" value="add"/></td>
                                        <td><input type="submit" name="manage_job_vacancy" value="delete"/></td>
                                    </tr>                    
                                </table>

                                <table border="0.5" cellspacing="17px" style="background-color: #CDCDCD; ">
                                    <tr>
                                        <td><input type="checkbox" name="" value="ON" disabled  /></td>
                                        <td>Vacancy</td>
                                        <td>Job Titles</td>
                                        <td>Hiring Manager</td>
                                        <td>Status</td>
                                    </tr>
                                    <%
                                                if (objResult != null) {
                                                    boolean result = false;
                                                    boolean show = false;
                                                    for (JobVacancy jobVacancy : listJobVacancy) {
                                                        if (objResult[0].equalsIgnoreCase("all")) {
                                                            show = true;
                                                        } else {
                                                            if (jobVacancy.getIdJob().getIdJob()== Integer.parseInt(objResult[0])) {
                                                                show = true;
                                                            } else {
                                                                show = false;
                                                                continue;
                                                            }

                                                        }
                                                        if (objResult[1].equalsIgnoreCase("all")) {
                                                            show = true;
                                                        } else {
                                                            if (jobVacancy.getTitleVacancy().equals(objResult[1])) {
                                                                show = true;
                                                            } else {
                                                                show = false;
                                                                continue;
                                                            }
                                                        }
                                                        if (objResult[2].equalsIgnoreCase("all")) {
                                                            show = true;
                                                        } else {
                                                            if (jobVacancy.getIdManager().getIdManager() == Integer.parseInt(objResult[2])) {
                                                                show = true;
                                                            } else {
                                                                show = false;
                                                                continue;
                                                            }
                                                        }
                                                        if (objResult[3].equalsIgnoreCase("all")) {
                                                            show = true;
                                                        } else {
                                                            if (jobVacancy.getStatus().equals(objResult[3])) {
                                                                show = true;
                                                            } else {
                                                                show = false;
                                                                continue;
                                                            }
                                                        }

                                                        if (show) {
                                                            out.println("<tr>");
                                                            out.println("<td><input type=\"checkbox\" name=\"delete\" value=\""
                                                                    + jobVacancy.getIdJobVacancy() + "\"/></td>");
                                                            out.println("<td><a href=JobVacancyProcess?id="
                                                                    + jobVacancy.getIdJobVacancy() + ">"
                                                                    + jobVacancy.getTitleVacancy() + "</a></td>");
                                                            out.println("<td>" + jobVacancy.getIdJob().getJobTitle()
                                                                    + "</td>");
                                                            out.println("<td>" + jobVacancy.getIdManager().getNamaManager()
                                                                    + "</td>");
                                                            out.println("<td>" + jobVacancy.getStatus() + "</td>");
                                                            result = true;
                                                            out.println("</tr>");
                                                        }
                                                    }
                                                    if (!result) {
                                                        out.println("<tr><td colspan=\"5\">No Result<td></tr>");
                                                    }
                                                } else {
                                                    if (listJobVacancy.size() == 0) {
                                                        out.println("<tr>");
                                                        out.println("<td colspan=\"5\">There is no data</td>");
                                                        out.println("</tr>");
                                                    } else {
                                                        for (JobVacancy jobVacancy : listJobVacancy) {
                                                            out.println("<tr>");
                                                            out.println("<td><input type=\"checkbox\" name=\"delete\" value=\""
                                                                    + jobVacancy.getIdJobVacancy() + "\"/></td>");
                                                            out.println("<td><a href=JobVacancyProcess?id="
                                                                    + jobVacancy.getIdJobVacancy() + ">"
                                                                    + jobVacancy.getTitleVacancy() + "</a></td>");
                                                            out.println("<td>" + jobVacancy.getIdJob().getJobTitle()
                                                                    + "</td>");
                                                            out.println("<td>" + jobVacancy.getIdManager().getNamaManager()
                                                                    + "</td>");
                                                            out.println("<td>" + jobVacancy.getStatus() + "</td>");
                                                            out.println("</tr>");
                                                        }
                                                    }
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
                    response.sendRedirect("login.jsp");
                }
    %>
</html>
