package url;

import com.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "administrator", value = "/administrator")
@Component
public class administrator extends HttpServlet {

    @Autowired
    AdministratorService admin;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("flightList",admin.showFlight());
        session.setAttribute("flightNumber",admin.showFlightNumber());
        request.getRequestDispatcher("administrator.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
