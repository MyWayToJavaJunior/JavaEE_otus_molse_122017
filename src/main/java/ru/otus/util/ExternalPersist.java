package ru.otus.util;

import ru.otus.model.*;
import ru.otus.model.dto.ExternalDataDTO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExternalPersist {
    public static ExternalDataDTO transform(String line) {
        String[] splittedLine = line.split(",");
        return new ExternalDataDTO()
                                    .fio(splittedLine[0])
                                    .email(splittedLine[1])
                                    .enabled(Boolean.valueOf(splittedLine[2]))
                                    .login(splittedLine[3])
                                    .password(splittedLine[4])
                                    .salary(Long.valueOf(splittedLine[5]))
                                    .city(splittedLine[6])
                                    .department(splittedLine[7])
                                    .position(splittedLine[8])
                                    .roles(splittedLine[9]);
    }


    public static void persist(List<ExternalDataDTO> employeesDTO) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();



            employeesDTO.forEach(employee -> {

                TypedQuery<City> queryCity = em.createQuery("SELECT c FROM City AS c WHERE c.name=:curCity",City.class);
                queryCity.setParameter("curCity", employee.getCity());

                TypedQuery<Department> queryDep = em.createQuery("SELECT d FROM Department AS d WHERE d.name=:curDep",Department.class);
                queryDep.setParameter("curDep", employee.getDepartment());

                TypedQuery<Position> queryPos = em.createQuery("SELECT p FROM Position AS p WHERE p.name=:curPos",Position.class);
                queryPos.setParameter("curPos", employee.getPosition());

                List<Role> roles = Stream.of(employee.getRoles())
                        .map(str -> str.split(";"))
                        .flatMap(Arrays::stream)
                        .map(Role::new)
                        .collect(Collectors.toList());

                Employee newEmployee = new Employee();
                newEmployee
                        .fio(employee.getFio())
                        .email(employee.getEmail())
                        .enabled(employee.isEnabled())
                        .login(employee.getLogin())
                        .password(employee.getPassword())
                        .salary(employee.getSalary())
                        .city(queryCity.getSingleResult())
                        .department(queryDep.getSingleResult())
                        .position(queryPos.getSingleResult())
                        .roles(roles);

                em.persist(newEmployee);
            });

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static List<Role> findRoles(String roles) {
        return null;
    }

    private static Position findPosition(String position) {
        return null;
    }

    private static Department findDep(String department) {
        return null;
    }

    public static void persistCity(Set<String> cities) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            cities.forEach(city -> em.persist(new City(city)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void persistDepartment(Set<String> departments) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            departments.forEach(department ->em.persist(new Department(department)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void persistPosition(Set<String> positions) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            positions.forEach(position -> em.persist(new Position(position)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
