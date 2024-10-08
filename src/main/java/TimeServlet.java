
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        getTime(req,resp);
        resp.getWriter().close();
    }

    private   void getDefaultTime( HttpServletResponse response){
        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String currentTime = utc.format(dateTimeFormatter);
        try {
            response.getWriter().write(currentTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getTime(HttpServletRequest request, HttpServletResponse response){
        String timeZoneValue = request.getParameter("timezone");

        if(timeZoneValue==null){
            getDefaultTime(response);
        }

        getDefaultTime(response, timeZoneValue);
    }
    private  void getDefaultTime(HttpServletResponse response, String UTC){
        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of(UTC));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String currentTime = utc.format(dateTimeFormatter);
        try {
            response.getWriter().write(currentTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
