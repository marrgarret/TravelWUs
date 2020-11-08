package filter;

import dao.LoginDao;
import model.User;
import utilite.Hashing;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        User user = new User();
        user.setEmail(username);
        user.setPassword(Hashing.md5(password));

        user = LoginDao.auth(user);
        final HttpSession session = req.getSession();

        if (user != null) {

            if (req.getParameter("cookie") != null) {
                Cookie cookie = new Cookie("username", user.getEmail());
                cookie.setMaxAge(60 * 60 * 24 * 30);
                res.addCookie(cookie);
                Cookie cookie1 = new Cookie("password", user.getPassword());
                cookie1.setMaxAge(60 * 60 * 24 * 30);
                res.addCookie(cookie1);

                Cookie cookie2 = new Cookie("id", user.getId());
                cookie2.setMaxAge(60 * 60 * 24 * 30);
                res.addCookie(cookie2);
            }
            session.setAttribute("user", user);
            session.setAttribute("error", "");
            req.setAttribute("error", "");
            res.sendRedirect(req.getContextPath() + "/home");
        } else {
            session.setAttribute("error", "This user no");
            req.setAttribute("error", "This user no");
            servletRequest.getRequestDispatcher("templates/login.ftl").forward(req, res);
        }
    }

    public void destroy() {

    }
}
