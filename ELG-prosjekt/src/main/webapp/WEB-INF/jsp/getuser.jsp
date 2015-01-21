<%@page import="springmvc.domain.User"%>
<%
if ("GET".equalsIgnoreCase(request.getMethod())) {
    User u = (User)session.getAttribute("user");
    out.println(u.getEmail());
}
%>
