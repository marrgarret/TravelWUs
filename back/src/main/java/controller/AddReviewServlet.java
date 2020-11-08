package controller;

import dao.ReviewDao;
import model.Review;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        String rev = request.getParameter("review");
        int tour_id = Integer.parseInt(request.getParameter("tour_id"));

        Review review = new Review();

        review.setText(rev);
        review.setUser_id(user);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        

        ReviewDao reviewDao = new ReviewDao();

        String reviewAdded = reviewDao.insert(review);

        if (reviewAdded.equals("SUCCESS")) {
            response.sendRedirect(request.getContextPath() + "/tour?id=" + tour_id);
        } else {
            request.setAttribute("errMessage", reviewDao);
            response.sendRedirect(request.getContextPath() + "/tour?id=" + tour_id);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        request.setAttribute("user", user);
    }
}
