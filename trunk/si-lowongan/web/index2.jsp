<html xmlns="http://www.w3.org/1999/xhtml">

    <%

                String pesan = (String) request.getAttribute("throwMessage");
    %>

    <head>
        <title>Admin - SI Lowongan Pekerjaan</title>
        <link href="default3.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <!-- start header -->
        <div id="header">

            <div id="logo">
                <h1>
                    <a href="#">SI Lowongan Pekerjaan</a></h1>
            </div>

        </div>
        <hr />
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="page">
                <!-- start content -->
                <div id="content">
                    <div class="post">
                        <h2 class="title">
                            LOGIN</h2>

                        <div class="entry">
                            <p>
                            <form action="ServletLogin">
                                <table border="0">
                                    <tbody>
                                        <tr>
                                            <td>User ID</td>
                                            <td>:</td>
                                            <td><input type="text" name="usernameTextField" value="" /></td>
                                        </tr>
                                        <tr>
                                            <td>Password</td>
                                            <td>:</td>
                                            <td><input type="password" name="passwordTextField" value="" /></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2"></td>
                                            <td><input type="submit" value="Login" /></td>
                                        </tr>
                                    </tbody>
                                </table>

                                <%
                                            if (pesan != null) {
                                                out.println("<h2 class=\"title\">" + pesan + "</h2>");
                                            }
                                %>

                            </form>
                            </p>
                        </div>
                    </div>
                </div>
                <br style="clear: both;" />
            </div>
        </div>
        <!-- end page -->
        <!-- start footer -->
        <div id="wrapper2">
            <div id="footer">
                PT. DREAMDAY
            </div>
            <p id="legal">
                Copyright (c) 2011 D.B.D.R All rights reserved.
            </p>
        </div>
    </body>
</html>
