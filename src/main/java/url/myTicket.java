//package url;
//
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "myTicket", value = "/myTicket")
//public class myTicket extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ClientOp client = new ClientOp();
//        HttpSession session = request.getSession();
//        session.setAttribute("ticketList",client.showTicket());
//        request.getRequestDispatcher("myTicket.jsp").forward(request,response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//}
