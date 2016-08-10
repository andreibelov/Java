package ru.belov.study.proj5.filter;

import ru.belov.study.proj5.core.MatchItr;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.StreamSupport.stream;

/**
 * Created by john on 7/7/2016.
 */
@WebFilter(filterName = "JspFilter",
        urlPatterns = {"/","/static/*","/jsp/*","*.jsp"},
        dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class JspFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        String uri = request.getRequestURI().substring(request.getContextPath().length()).toLowerCase();
        if (uri.startsWith("/static/")) {
            request.getServletContext().getNamedDispatcher("default").forward(request, response);
        } else if (uri.startsWith("/jsp/")){
            request.getServletContext().getNamedDispatcher("jsp").forward(request, response);
        } else chain.doFilter(request, response);
    }
}
