package com.example.writemymail.error.resolver;

import com.example.writemymail.error.NotValidTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class ExceptionResolver extends AbstractHandlerExceptionResolver {
    public void handleNotValidTokenException(HttpServletRequest request,
                                             HttpServletResponse response,
                                             NotValidTokenException ex) {
        try {
            doResolveException(request, response, null, ex);
        } catch (Exception handlerException) {
            logger.warn("Exception из handleNotValidTokenException", handlerException);
        }
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex) {
        if (ex instanceof NotValidTokenException) {
            createResponseForException(response,ex);
            return null;
        }
        return null;
    }
    private void createResponseForException(HttpServletResponse response, Exception ex) {
        try {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.setContentType("application/json");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonResponse = mapper.createObjectNode();
            jsonResponse.put("type", "about:blank");
            jsonResponse.put("title", "Bad Request");
            jsonResponse.put("status", HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.put("detail", ex.getMessage());

            String json = mapper.writeValueAsString(jsonResponse);
            response.getWriter().write(json);
        } catch (IOException e) {
            logger.error("IOException while creating response", e);
        }
    }
}
