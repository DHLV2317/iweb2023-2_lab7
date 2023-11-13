package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Daos.DepartmentDao;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/DepartmentServlet"})
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");
        JobDao jobDao = new JobDao();
        EmployeeDao employeeDao = new EmployeeDao();
        DepartmentDao departmentDao = new DepartmentDao();

        if (action.equalsIgnoreCase("crear")) {
            String departmentId = String.valueOf(Integer.parseInt(req.getParameter("departmentId")));
            String departmentName = req.getParameter("departmentName");
            int managerId = Integer.parseInt(req.getParameter("managerId"));
            int locationId = Integer.parseInt(req.getParameter("locationId"));

            Department bDepartment = departmentDao.obtenerOficio(departmentId);
            if (bDepartment == null) {
                departmentDao.crearOficio(departmentId, departmentName, managerId, locationId);
            } else {
                departmentDao.actualizarOficio(departmentId, departmentName, managerId, locationId);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/DepartmentServlet");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");
        DepartmentDao departmentDao = new DepartmentDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                ArrayList<Department> listaOficios = departmentDao.lista();
                req.setAttribute("lista", listaOficios);
                view = req.getRequestDispatcher("departments/listaOficios.jsp");
                view.forward(req, resp);
                break;
            case "formCrear":
                view = req.getRequestDispatcher("departments/nuevoOficio.jsp");
                view.forward(req, resp);
                break;
            case "editar":
                String departmentId = req.getParameter("id");
                Department bDepartment = departmentDao.obtenerOficio(departmentId);
                if (bDepartment == null) {
                    resp.sendRedirect(req.getContextPath() + "/DepartmentServlet");
                } else {
                    req.setAttribute("oficio", bDepartment);
                    view = req.getRequestDispatcher("departments/editarOficio.jsp");
                    view.forward(req, resp);
                }
                break;
            case "borrar":
                String departmentID = req.getParameter("id");
                if (departmentDao.obtenerOficio(departmentID) != null) {
                    departmentDao.borrarOficio(departmentID);
                }
                resp.sendRedirect(req.getContextPath() + "/DepartmentServlet");
                break;

        }


    }
}