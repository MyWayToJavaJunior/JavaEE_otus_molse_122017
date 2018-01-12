package ru.otus.servlet;

import ru.otus.util.LocalEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/max")
public class MaxSalaryEmployee extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("max_salary");
            storedProcedure.execute();
            String fio = (String) storedProcedure.getSingleResult();

            try (PrintWriter pw = response.getWriter()){
               pw.println(fio);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
