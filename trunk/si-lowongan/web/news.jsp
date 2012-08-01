<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.JobVacancy"%>
<%@page import="entity.JobVacancy"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.util.List"%>
<%@page import="dao.mysql.JobVacancyImpl"%>
<%@page import="servlet.Manager"%>
<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
    %>

    <head>
        <title>User - News</title>
        <link href="default4.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <!-- start header -->
        <div id="header">
            <div id="logo">
                <h1>
                    <a href="#">SI Lowongan Pekerjaan</a></h1>
            </div>



            <div id="menu">
                <!--tab user-->
                <ul>
                    <li><a href="news.jsp">News</a></li>
                    <li><a href="#">Applicant</a></li>
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
                            NEWS
                        </h2>

                        <div class="entry">
                            <p>


                            <table border="0.5" align="center" cellspacing="17px" style="background-color: #CDCDCD; ">
                                <%
                                    List<JobVacancy> lj ;
                                    Manager manager = new Manager();
                                    EntityManager em = manager.getEntityManager();
                                    int index =0;
                                    try {
                                        lj = new JobVacancyImpl(em).gets();
                                        int size = lj.size();
                                        for (int i = 0; i < size; i++) {
                                %>
                                <tr><td><h2><% out.print(lj.get(i).getTitleVacancy() + "</h2>post on :  " +lj.get(i).getPostDate()  );%></i></h6></td></tr>
                                <tr>
                                    <td class="tab" width="10"> <% out.print(lj.get(i).getDescription() + "");%></td>
                                </tr>
                                <tr><td class="tab"><a href="detailnews.jsp?id=<%out.print(index);%>">View Detail</a></td></tr>

                                <%
                                        index++;
                                        }
                                    } catch (Exception ex) {
                                    }
                                %>
                            </table>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>