package org.example;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static java.util.Objects.isNull;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timeZone = getTimeZoneFromRequest(req);
        TimeZone utc = TimeZone.getTimeZone(timeZone);
        String currencyTime = LocalDateTime.now(utc.toZoneId())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss ")) + timeZone;
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(currencyTime);
        resp.getWriter().close();
    }

    private String getTimeZoneFromRequest(HttpServletRequest req) {
        String timezone = req.getParameter("timezone");
        return isNull(timezone) ? "UTC" : timezone;
    }
}

