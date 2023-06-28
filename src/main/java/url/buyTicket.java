package url;

import com.service.ClientService;
import com.service.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "buyTicket", value = "/buyTicket")
public class buyTicket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientService client = new ClientServiceImpl();
        HttpSession session = request.getSession();
        session.setAttribute("flightList",client.showFlight());
        request.getRequestDispatcher("buyTicket.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
