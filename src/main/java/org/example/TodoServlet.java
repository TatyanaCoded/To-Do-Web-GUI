package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet; // ❌ NO LONGER NEEDED
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// ❌ The @WebServlet annotation has been removed to avoid conflict with web.xml
public class TodoServlet extends HttpServlet {

    private TodoDao todoDao;

    // Use init() to get the single Singleton instance
    @Override
    public void init() throws ServletException {
        super.init();
        this.todoDao = TodoDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("delete")) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                todoDao.deleteItem(id);
            } catch (NumberFormatException e) {
                // Ignore if ID is missing or malformed
            }
        }

        // Fetch the current list of items
        List<TodoItem> items = todoDao.getItems();
        request.setAttribute("items", items);

        // Forward to the JSP view
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String task = request.getParameter("task");

        if (task != null && !task.trim().isEmpty()) {
            todoDao.addItem(task);
        }

        // Use Post/Redirect/Get pattern
        response.sendRedirect(request.getContextPath() + "/todo");
    }
}