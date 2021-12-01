package ru.vote.web;

import ru.vote.util.RestaurantUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to restaurants");

        req.setAttribute("restaurantList", RestaurantUtil.getSortedByVoteCountList());
        req.getRequestDispatcher("restaurants.jsp").forward(req, resp);
    }
}
