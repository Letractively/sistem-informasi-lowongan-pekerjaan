<%@page import="dao.IJobVacancyDAO"%>
<%@page import="servlet.Manager"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="dao.mysql.JobVacancyImpl"%>
<%@page import="java.util.List"%>
<%@page import="entity.JobVacancy"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Proyek Rekayasa Web</title>
        <link href="Style/admin.css" rel="stylesheet" type="text/css"/>        
        <link href="Style/menu.css" rel="stylesheet" type="text/css"/>
        <link href="Style/jquery-ui-custom.css" rel="stylesheet" type="text/css"/>
        <script src="Script/jquery_002.js" language="javascript" type="text/javascript"></script>
        <script src="Script/jquery.js" language="javascript" type="text/javascript"></script>
        <script src="Script/jquery-ui-custom.js" language="javascript" type="text/javascript"></script>
        <script src="Script/app-common.js" language="javascript" type="text/javascript"></script>
    </head>
    <body>
        <div id="wrapper">
            <div id="top-heading">
            </div>
            <div id="tb-utama">
                <div id="tb-menu">
                    <ul class="topnav">
                        <li id="first"><a href="index.jsp">News</a></li>                      
                    </ul>
                </div>
                <div id="tb-tanggal">
                    <a href="Logout" class="logout" id="logout">Logout</a> |
                </div>
                <div class="clearer"></div>
            </div>
            <div id="container">
                <table align ="center" border="2">

                    <tr>
                        <td>Title Vacation</td>
                        <td>Description</td>
                        <td>Show Descrition</td>
                    </tr>
                    <%
                        List<JobVacancy> lj;
                        Manager manager = new Manager();
                        EntityManager em = manager.getEntityManager();
                        lj = new JobVacancyImpl(em).gets();
                        try {
                            lj = new JobVacancyImpl(em).gets();
                            int size = lj.size();
                            for (int i = 0; i < size; i++) {
                                
                                %>
                                <tr>
                                    <td><%  out.print(lj.get(i).getTitleVacancy() + "");%></td>
                                    <td><% out.print(lj.get(i).getDescription() + "");%></td>
                                    <td><a href="#">Show Details</a></td>
                                          
                                </tr>
                    
                                <%
                              
                            }
                        } catch (Exception ex) {
                        }
                    %>

                </table>


            </div>
            <div id="footer">
                Proyek Sistem Informasi Lowongan Pekerjaan - Teknologi Persistance<br/>
                Fakultas Teknologi Informasi<br/>
                Universitas Kristen Satya Wacana
            </div>
        </div>
    </body>
</html>