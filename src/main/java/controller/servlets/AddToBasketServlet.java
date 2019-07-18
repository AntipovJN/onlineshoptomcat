package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(value = "/products/buy")
public class AddToBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        if (Objects.isNull(req.getSession().getAttribute("basket"))) {
            req.getSession().setAttribute("basket", "");
        }
        req.getSession().setAttribute("basket",
                req.getSession().getAttribute("basket").toString() + id + ";");
        System.out.println(req.getSession().getAttribute("basket"));
        resp.sendRedirect("/products");
    }
}
