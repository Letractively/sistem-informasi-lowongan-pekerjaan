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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Admin - Sistem Informasi Lowongan Pekerjaan</title>
        <link href="Style/admin.css" rel="stylesheet" type="text/css"/>        
        <link href="Style/menu.css" rel="stylesheet" type="text/css"/>
        <link href="Style/jquery-ui-custom.css" rel="stylesheet" type="text/css"/>
        <script src="Script/jquery_002.js" language="javascript" type="text/javascript"></script>
        <script src="Script/jquery.js" language="javascript" type="text/javascript"></script>
        <script src="Script/jquery-ui-custom.js" language="javascript" type="text/javascript"></script>
        <script src="Script/app-common.js" language="javascript" type="text/javascript"></script>
    </head>
    <%
        HttpSession mySession = request.getSession();
        User user;
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
    <body>
        <div id="wrapper">
            <div id="top-heading">
            </div>  
            <div id="tb-utama">
                <div id="tb-menu">
                    <ul class="topnav">
                        <li id="first"><a href="index.jsp">Home</a></li>
                        <li><a href="#">Recruitment</a>
                            <ul style="display: none;" class="subnav">
                                <li><a href="job_vacancies.jsp">Job Vacancies</a></li>
                                <li><a href="#">Applicants</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Job</a>
                            <ul style="display: none;" class="subnav">
                                <li><a href="#">Job Titte</a></li>
                                <li><a href="#">Job Spesification</a></li>
                            </ul>
                        </li>  
                        <li><a href="#">Commpany Info</a>
                            <ul style="display: none;" class="subnav">
                                <li><a href="#">About US</a></li>
                                <li><a href="#">Contact US</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div id="tb-tanggal">
                    <a href="Logout" class="logout" id="logout">Logout</a> |
                </div>
                <div class="clearer"></div>
            </div>
            <div id="container">
                <form action="JobVacancyProcess">
                    <table border="1">
                        <tr>
                            <td>Job Title</td>
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
                                Vacancy
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
                                Hiring Manager
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
                                Status
                            </td>
                            <td>
                                <select name="status">
                                    <option value="all">All</option>
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right"><input type="submit" name="manage_job_vacancy" value="search"/></td>
                        </tr>                           
                    </table>
                </form>
                <form action="JobVacancyProcess">
                    <table border="1">
                        <tr>
                            <td><input type="submit" name="manage_job_vacancy" value="add"/></td>
                            <td><input type="submit" name="manage_job_vacancy" value="delete"/></td>
                        </tr>                    
                    </table>
                    <table border="1" width="100%">
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
                                        if (jobVacancy.getIdJob().getIdJob().equals(objResult[0])) {
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
                                        if (jobVacancy.getIdManager().getIdManager().equals(objResult[2])) {
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

                        %>
                    </table>
                </form>
            </div>
            <div id="footer" >
                Proyek Sistem Informasi Lowongan Pekerjaan - Teknologi Persistance<br/>
                Fakultas Teknologi Informasi<br/>
                Universitas Kristen Satya Wacana
            </div>
        </div>
    </body>
    <%
        } else {
            response.sendRedirect("index.jsp");
        }
    %>
</html>