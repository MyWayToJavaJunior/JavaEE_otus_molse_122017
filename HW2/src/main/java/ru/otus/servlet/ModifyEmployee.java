package ru.otus.servlet;

import org.apache.commons.lang.RandomStringUtils;
import ru.otus.model.Employee;
import ru.otus.model.Position;
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

@WebServlet("/modifylist")
public class ModifyEmployee extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query qEmp = em.createQuery("from Employee");
            List<Employee> employees = qEmp.getResultList();

            Query qPos = em.createQuery("from Position");
            List<Position> positions = qPos.getResultList();

            Collections.shuffle(employees);
            employees
                    .stream()
                    .limit(2)
                    .map(emp -> {
             emp.fio(RandomStringUtils.random(15, true, false));
             Position position = emp.getPosition();
             Position newPosition = positions
                                            .stream()
                                            .filter(p-> p.getId()!=position.getId())
                                            .findFirst().get();
             emp.setPosition(newPosition);
             return emp;
                    })
                    .forEach(em::merge);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
