<%@ page  import="java.io.FileInputStream" %>
<%@ page  import="java.io.BufferedInputStream"  %>
<%@ page  import="java.io.File"  %>
<%@ page import="java.io.IOException" %>
<%
            String filename = request.getParameter("resume");
            String filepath = getServletContext().getRealPath("/") + "file\\";

            BufferedInputStream buf = null;
            ServletOutputStream myOut = null;

            try {

                myOut = response.getOutputStream();
                File myfile = new File(filepath + filename);

                response.setContentType("text/plain");

                response.addHeader(
                        "Content-Disposition", "attachment; filename=" + filename);

                response.setContentLength((int) myfile.length());

                FileInputStream input = new FileInputStream(myfile);
                buf = new BufferedInputStream(input);
                int readBytes = 0;

                while ((readBytes = buf.read()) != -1) {
                    myOut.write(readBytes);
                }
            } catch (IOException ioe) {
                throw new ServletException(ioe.getMessage());
            } finally {
                if (myOut != null) {
                    myOut.close();
                }
                if (buf != null) {
                    buf.close();
                }
            }
%>