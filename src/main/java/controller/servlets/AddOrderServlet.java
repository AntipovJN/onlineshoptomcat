package controller.servlets;

import factory.OrderServiceFactory;
import model.Code;
import model.User;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/order")
public class AddOrderServlet extends HttpServlet {

    private static final OrderService orderService = OrderServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String address = req.getParameter("address");
        String payment = req.getParameter("payment");
        Code code = orderService.addOrder(address, payment,
                ((User) req.getSession().getAttribute("user")),
                req.getSession().getAttribute("basket").toString());
        if (orderService.getByCode(code).isPresent()) {
            Long id = orderService.getByCode(code).get().getId();
            req.getSession().setAttribute("orderId", id);
            resp.sendRedirect("/confirmOrder");
        } else resp.sendRedirect("/products");
    }
}
