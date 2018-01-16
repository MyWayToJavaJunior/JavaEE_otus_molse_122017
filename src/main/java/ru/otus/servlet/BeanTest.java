package ru.otus.servlet;

import ru.otus.bean.MyBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/bean")
public class BeanTest extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            MyBean bean = (MyBean) envCtx.lookup("bean/MyBeanFactory");

            System.out.println("foo = " + bean.getFoo() + ", bar = " +
                    bean.getBar());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
