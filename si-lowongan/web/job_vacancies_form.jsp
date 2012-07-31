<%--
    Document   : absen
    Created on : Nov 29, 2011, 9:16:24 PM
    Author     : Acer
--%>

<%@page import="entity.JobVacancy"%>
<%@page import="dao.mysql.ManagerImpl"%>
<%@page import="entity.Job"%>
<%@page import="dao.mysql.JobImpl"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="servlet.Manager"%>
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

        Boolean flagError = (Boolean) request.getAttribute("throwFlagError");
        String messge = (String) request.getAttribute("throwMessageError");
        
        EntityManager em = (EntityManager) mySession.getAttribute("throwEM");

        try {
            user = (User) mySession.getAttribute("user");
        } catch (Exception e) {
            user = null;
        }

        if (user != null) {
            List<Job> listJob = new JobImpl(em).gets();
            List<entity.Manager> listManager = new ManagerImpl(em).gets();
            JobVacancy jv = null;
            try {
                jv = (JobVacancy) request.getAttribute("jobVacancy");
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
                    <li><a href="ManageManager.jsp">Manager Conf</a></li>
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
                            Job Vacancy Form
                        </h2>

                        <div class="entry">
                            <p>
                                <br>
                            <form action="JobVacancyProcess" method="POST">
                                <table>
                                    <tr>
                                        <td>Job Title</td>
                                        <td>
                                            <%
                                                if (jv != null) {
                                                    out.println("<input type=\"hidden\" name=\"id_job_vacancy\" value=\"" + jv.getIdJobVacancy() + "\"/>");
                                                }
                                            %>
                                            <select name="job_titles">
                                                <%
                                                    for (Job job : listJob) {
                                                        out.println("<option value=\"" + job.getIdJob() + "\">"
                                                                + job.getJobTitle() + "</option>");
                                                    }

                                                    if (jv != null) {
                                                        for (Job job : listJob) {
                                                            if (job.getIdJob() == jv.getIdJob().getIdJob()) {
                                                                out.println("<option value=\"" + job.getIdJob() + "\" select>"
                                                                        + job.getJobTitle() + "</option>");
                                                            } else {
                                                                out.println("<option value=\"" + job.getIdJob() + "\">"
                                                                        + job.getJobTitle() + "</option>");
                                                            }
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Vacancy Name</td>
                                        <td>
                                            <%
                                                if (jv == null) {
                                                    out.println("<input type=\"text\" "
                                                            + "name=\"txt_vacancy_name\"/>");
                                                } else {
                                                    out.println("<input type=\"text\" "
                                                            + "name=\"txt_vacancy_name\" "
                                                            + "value=\""
                                                            + jv.getTitleVacancy() + "\"/>");
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Hiring Manager</td>
                                        <td>
                                            <select name="hiring_manager">
                                                <%
                                                    if (jv == null) {
                                                        for (entity.Manager mgr : listManager) {
                                                            out.println("<option value='" + mgr.getIdManager() + "'>"
                                                                    + mgr.getNamaManager() + "</option>");
                                                        }
                                                    } else {
                                                        for (entity.Manager mgr : listManager) {
                                                            if ((mgr.getIdManager().equals(jv.getIdManager().getIdManager()))) {
                                                                out.println("<option value='" + mgr.getIdManager() + "' select>"
                                                                        + mgr.getNamaManager() + "</option>");
                                                            } else {
                                                                out.println("<option value='" + mgr.getIdManager() + "'>"
                                                                        + mgr.getNamaManager() + "</option>");
                                                            }
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Number Of Positions</td>                            
                                        <td>
                                            <%
                                                if (jv == null) {
                                                    out.println("<input type=\"text\" name=\"txt_number_pos\">");
                                                } else {
                                                    out.println("<input type=\"text\" name=\"txt_number_pos\" value = \"" + jv.getNumberPosition() + "\">");
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Description</td>
                                        <td>
                                            <%
                                                if (jv == null) {
                                                    out.println("<textarea name=\"txt_description\" rows=\"5\" cols=\"50\"></textarea>");
                                                } else {
                                                    out.println("<textarea name=\"txt_description\" rows=\"5\" cols=\"50\">" + jv.getDescription() + "</textarea>");
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Active</td>
                                        <td>
                                            <%
                                                if (jv == null) {
                                                    out.println("<input type=\"checkbox\" name=\"cbx_active\" value=\"Active\"/>");
                                                } else {
                                                    if (jv.getStatus().equals("Active")) {
                                                        out.println("<input type=\"checkbox\" name=\"cbx_active\" value=\"Active\" checked/>");
                                                    } else {
                                                        out.println("<input type=\"checkbox\" name=\"cbx_active\" value=\"Active\"/>");
                                                    }
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" name="submit_job_vacancy" value="submit"/></td>
                                    </tr>

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
