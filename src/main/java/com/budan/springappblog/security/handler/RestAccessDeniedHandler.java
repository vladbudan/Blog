package com.budan.springappblog.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final String DENIED_MESSAGE = "Sorry, you don't have required for this operation";

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, DENIED_MESSAGE);
    }
}
