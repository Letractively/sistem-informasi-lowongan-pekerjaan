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
                String message = (String) mySession.getAttribute("throwMessage");
                try {
                    user = (User) mySession.getAttribute("throwUser");
                } catch (Exception e) {
                    user = null;
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
                        <%
                                    if (user != null) {
                        %>
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
                        <%                            }
                        %>
                    </ul>
                </div>
                <div id="tb-tanggal">
                    <a href="Logout" class="logout" id="logout">Logout</a> |
                </div>
                <div class="clearer"></div>
            </div>
            <div id="container">
                <%
                            if (user == null) {
                %>
                <form action="Login" method="post">
                    <table>
                        <tr>
                            <td colspan="2">
                                <%
                                                                if (message != null) {
                                                                    if (!message.equalsIgnoreCase("")) {
                                                                        out.println(message);
                                                                    }
                                                                }
                                %>
                            </td>
                        </tr>
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name="id_user"/></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="password"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right"><input type="submit" name="submit" value="Login"/></td>
                        </tr>                           
                    </table>
                </form>
                <%
                            }
                %>
            </div>
            <div id="footer" >
                Proyek Sistem Informasi Lowongan Pekerjaan - Teknologi Persistance<br/>
                Fakultas Teknologi Informasi<br/>
                Universitas Kristen Satya Wacana
            </div>
        </div>
    </body>
</html>