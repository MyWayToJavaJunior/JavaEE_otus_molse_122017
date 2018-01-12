package ru.otus.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.otus.model.Employee;
import ru.otus.model.dto.ExternalDataDTO;
import ru.otus.util.ExternalPersist;
import ru.otus.util.LocalEntityManagerFactory;

@WebServlet("/uploadData")
public class UploadEmployeeData extends HttpServlet {

    private static final long serialVersionUID = 5885899233778066005L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                FileItem file = (FileItem) upload.parseRequest(request).stream().findFirst().get();
                List<String> allLines;

                try (BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                    allLines = buffer.lines().collect(Collectors.toList());
                }
                List<ExternalDataDTO> employeesDTO = allLines
                        .stream()
                        .map(ExternalPersist::transform)
                        .collect(Collectors.toList());

                ExternalPersist.persistCity(employeesDTO
                                                        .stream()
                                                        .map(ExternalDataDTO::getCity)
                                                        .collect(Collectors.toSet()));
                ExternalPersist.persistDepartment(employeesDTO
                        .stream()
                        .map(ExternalDataDTO::getDepartment)
                        .collect(Collectors.toSet()));

                ExternalPersist.persistPosition(employeesDTO
                        .stream()
                        .map(ExternalDataDTO::getPosition)
                        .collect(Collectors.toSet()));


                ExternalPersist.persist(employeesDTO);

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath());
    }
}