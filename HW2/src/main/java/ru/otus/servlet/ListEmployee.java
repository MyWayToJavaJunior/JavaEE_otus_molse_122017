package ru.otus.servlet;

import ru.otus.util.LocalEntityManagerFactory;
import ru.otus.model.Employee;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listall")
public class ListEmployee extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("from Employee order by id desc");
            EntityGraph graph1 = em.getEntityGraph("employee");
            q.setHint("javax.persistence.fetchgraph", graph1);
            List<Employee> result = q.getResultList();
            try (PrintWriter pw = response.getWriter()){
                result.stream().forEach(pw::println);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
