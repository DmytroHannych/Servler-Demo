package org.example;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TimeZone utc = TimeZone.getTimeZone("UTC");
        String currencyTime = LocalDate.now(utc.toZoneId()).format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd hh:mm:ss z"));

        resp.setContentType("text/xml; charset=utf-8");
       resp.getWriter().write(currencyTime + "${timezone}".replace("${timezone}",timeZone(req)));
       resp.getWriter().close();
    }

    private String timeZone(HttpServletRequest req){
        if(req.getParameterMap().containsKey("timezone")){
            return req.getParameter("timezone");
        }
        return "<unnamed>";
    }
}
