<%@page import="dao.mysql.ManagerImpl"%>
<%@page import="entity.Job"%>
<%@page import="dao.mysql.JobImpl"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="servlet.Manager"%>
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

        Manager manager = new Manager();
        EntityManager em = manager.getEntityManager();

        try {
            user = (User) mySession.getAttribute("user");
        } catch (Exception e) {
            user = null;
        }

        if (user != null) {
            List<Job> listJob = new JobImpl(em).gets();
            List<entity.Manager> listManager = new ManagerImpl(em).gets();
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
                <h3>Add Job Vacancy</h3>
                <form action="JobVacancy" method="POST">
                    <table>
                        <tr>
                            <td>Job Title</td>
                            <td>
                                <select name="job_titles">
                                    <%
                                        for (Job job : listJob) {
                                            out.println("<option value='" + job.getIdJob() + "'>"
                                                    + job.getJobTitle() + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Vacancy Name</td>
                            <td><input type="text" name="txt_vacancy_name" value=""/></td>
                        </tr>
                        <tr>
                            <td>Hiring Manager</td>
                            <td>
                                <select name="hiring_manager">
                                    <%
                                        for (entity.Manager mgr : listManager) {
                                            out.println("<option value='" + mgr.getIdManager() + "'>"
                                                    + mgr.getNamaManager() + "</option>");
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Number Of Positions</td>
                            <td><input type="text" name="txt_number_pos" value=""/></td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><textarea name="txt_description" rows="5" cols="50"></textarea></td>
                        </tr>
                        <tr>
                            <td>Active</td>
                            <td><input type="checkbox" name="cbx_active" value=""/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="submit_job_vacancy" value="submit"/></td>
                        </tr>
                        
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
    <%                            } else {
            response.sendRedirect("index.jsp");
        }
    %>
</html>