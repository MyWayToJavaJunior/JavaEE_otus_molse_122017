package ru.otus.servlet;

import ru.otus.model.Employee;
import ru.otus.util.LocalEntityManagerFactory;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/xml")
public class XMLExployee extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/xml; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("from Employee order by id desc");
            EntityGraph graph1 = em.getEntityGraph("employee");
            q.setHint("javax.persistence.fetchgraph", graph1);
            List<Employee> listEmpl = q.getResultList();
            Employee oneEmpl = listEmpl
                    .stream()
                    .filter(t->t.getId()==1)
                    .distinct()
                    .findAny().get();


            try (PrintWriter pw = response.getWriter()){

                JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(oneEmpl,pw);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
