package controller.servlets;

import factory.MailServiceFactory;
import factory.OrderServiceFactory;
import model.Order;
import model.User;
import service.MailService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {

    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final MailService mailService = MailServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Long.valueOf(req.getSession().getAttribute("orderId").toString());
        req.getSession().removeAttribute("orderId3");
        req.setAttribute("orderId", id);
        User user = (User) req.getSession().getAttribute("user");
        if (orderService.getById(id).isPresent()) {
            Order order = orderService.getById(id).get();
            mailService.sendMessage(order.getCode(), user.getEmail());
            req.getRequestDispatcher("/confirmOrder.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        int code = Integer.valueOf(req.getParameter("code"));
        if (code != orderService.getById(id).get().getCode().getCode()) {
            req.setAttribute("error", "invalid code");
            req.getRequestDispatcher("/confirmOrder.jsp").forward(req, resp);
        } else {
            req.getSession().removeAttribute("basket");
            resp.sendRedirect("/products");
        }
    }
}
