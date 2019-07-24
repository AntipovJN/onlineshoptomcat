package controller.servlets;

import factory.BasketServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.User;
import service.BasketService;
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
    private static final BasketService basketService = BasketServiceFactory.getInstance();

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
        User user = (User) req.getSession().getAttribute("user");
        Code code = orderService.addOrder(address, payment, basketService.getBasket(user));
        if (orderService.getByCode(code).isPresent()) {
            Long id = orderService.getByCode(code).get().getId();
            req.getSession().setAttribute("orderId", id);
            resp.sendRedirect("/confirmOrder");
            return;
        }
            resp.sendRedirect("/products");
    }
}
