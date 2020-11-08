package controller;

import dao.LoginDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        String name = req.getParameter("name");
        String pattern  = "^([a-z0-9_\\.-])+@[a-z0-9-]+\\.([a-z]{2,4}\\.)?[a-z]{2,4}$";
        System.out.println(name);
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String pathFile = "";
        String absPath = "";

        if (!Pattern.matches(pattern,email) && !email.equals("")){
            req.setAttribute("error_email", "email не подходит под example@email.com");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/profile.ftl");
            requestDispatcher.forward(req, resp);
            return;
        }

        if (!name.equals("")){
            user.setName(name);
        }
        if (!surname.equals("")){
            user.setSurname(surname);
        }
        if (!email.equals("")){
            user.setEmail(email);
        }
        if (!phone.equals("")){
            user.setPhone(phone);
        }

        if (LoginDao.uniqUser(user) && email.equals(user.getEmail())) {
            req.setAttribute("error_email", "Такой email уже есть");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login/lk.html");
            requestDispatcher.forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("user",user);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login/");
        requestDispatcher.forward(req, resp);
    }
}
