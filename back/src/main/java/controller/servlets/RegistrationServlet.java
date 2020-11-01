package controller.servlets;

import model.User;
import dao.LoginDao;
import dao.RegistrationDao;
import filter.LoginFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email= req.getParameter("email");
        String password = req.getParameter("password");
        String repeat_password = req.getParameter("password-repeat");
        String pattern = "^([a-z0-9_.-])+@[a-z0-9-]+\\.([a-z]{2,4}\\.)?[a-z]{2,4}$";
        String pattern_pass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,}";
        if (!Pattern.matches(pattern,email)){
            System.out.println("false");
            req.setAttribute("error", "Укажите правильно логин");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/registration.ftl");
            requestDispatcher.forward(req, resp);
            return;
        }

        if (!Pattern.matches(pattern_pass,password)){
            System.out.println("False2");
            req.setAttribute("error", "Укажите правильно пароль");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/registration.ftl");
            requestDispatcher.forward(req, resp);
            return;
        }

        if (!password.equals(repeat_password)) {
            System.out.println("ouu it is work");
            req.setAttribute("error", "Пароли не совпадают");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/registration.ftl");
            requestDispatcher.forward(req, resp);
            return;
        }
        RegistrationDao registrationDao = new RegistrationDao();
        User user = new User();
        user.setName(req.getParameter("username"));
        user.setSurname(req.getParameter("surname"));
        user.setEmail(email);
        user.setPhone(req.getParameter("phone"));
        user.setPassword(password);


        if (LoginDao.uniqUser(user)) {
            user = new User();
            req.setAttribute("error", "Такой email уже зарегистрирован");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/registration.ftl");
            requestDispatcher.forward(req, resp);
            return;
        } else {
            req.setAttribute("error", "");
        }

        String userRegistr = registrationDao.registerUser(user);

        if (userRegistr.equals("SUCCESS")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/login.ftl");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("error", "Укажите правильно логин или пароль");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/registration.ftl");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error","");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("templates/registration.ftl");
        requestDispatcher.forward(req, resp);
    }
}
