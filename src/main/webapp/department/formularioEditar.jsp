<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@page import="com.example.webapphr1_2023.Beans.Job" %>
<%@page import="com.example.webapphr1_2023.Beans.Department" %>
<jsp:useBean id="listaDepartamentos" type="ArrayList<Department>" scope="request" />
<jsp:useBean id="department" type="com.example.webapphr1_2023.Beans.Department" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <title>Editar department</title>
    </head>
    <body>
        <div class='container mb-4'>
            <div class="row justify-content-center">
                <h1 class='mb-3'>Editar department</h1>
                <hr>
                <form method="POST" action="DepartmentServlet?action=actualizar" class="col-md-6 col-lg-6">
                    <input type="hidden" name="employee_id" value="<%= department.getDepartmentId()%>"/>
                    <div class="mb-3">
                        <label for="department_id">Department ID</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" id="department_id"
                               value="<%= department.getDepartmentId() == null ? "" : department.getDepartmentId()%>">
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Department Name</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" id="department_name"
                               value="<%= department.getDepartmentName() == null ? "" : department.getDepartmentName()%>">
                    </div>
                    <div class="mb-3">
                        <label for="manager">Manager ID</label>
                        <input type="text" class="form-control form-control-sm" name="manager_id" id="manager"
                               value="<%= department.getManagerId() == int ? "" : department.getManagerId()%>">
                    </div>
                    <div class="mb-3">
                        <label for="location">Location ID</label>
                        <input type="text" class="form-control form-control-sm" name="location_id" id="location"
                               value="<%= department.getLocationId() == int ? "" : department.getLocationId()%>">
                    </div>
                    <a href="<%= request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="Actualizar" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>
