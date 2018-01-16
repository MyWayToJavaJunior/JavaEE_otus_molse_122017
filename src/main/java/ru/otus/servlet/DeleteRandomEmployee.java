package ru.otus.servlet;

import ru.otus.model.Employee;
import ru.otus.util.LocalEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/delete")
public class DeleteRandomEmployee extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query qEmp = em.createQuery("from Employee");
            List<Employee> employees = qEmp.getResultList();

            Collections.shuffle(employees);
            employees
                    .stream()
                    .limit(3)
                    .forEach(em::remove);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
