package org.example;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;


@WebFilter(value = "/time/*")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        String timezone = req.getParameter("timezone");
        if(timezone.contains(utc.toString())){
            chain.doFilter(req,res);
        } else {
            res.setStatus(404);
            res.getWriter().write("Invalid timezone");
            res.getWriter().close();
        }
    }
}
