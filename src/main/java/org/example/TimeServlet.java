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

import static java.util.Objects.isNull;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TimeZone utc = TimeZone.getTimeZone("UTC");
        String currencyTime = LocalDate.now(utc.toZoneId()).format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd hh:mm:ss"));
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(currencyTime + "${timezone}".replace("${timezone}", timeZone(req)));
        resp.getWriter().close();
    }

    private String timeZone(HttpServletRequest req) {
        String timezone = req.getParameter("timezone");
        if (isNull(timezone)){
            return "UTC";
        } else {
            return timezone;
        }
    }
}
