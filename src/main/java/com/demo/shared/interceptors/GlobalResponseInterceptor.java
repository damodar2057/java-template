package com.demo.shared.interceptors;


import java.time.LocalDateTime;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.demo.shared.dto.ApiResponse;
import com.demo.shared.dto.PaginatedResponse;
import com.demo.shared.exception.ErrorResponse;



@ControllerAdvice
public class GlobalResponseInterceptor implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        // Avoid double wrapping
        if (body instanceof ApiResponse || body instanceof ErrorResponse) {
            return body;
        }

        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Request processed successfully");
        apiResponse.setTimestamp(LocalDateTime.now());
        apiResponse.setPath(request.getURI().getPath());

        if (body instanceof PaginatedResponse<?>) {
            PaginatedResponse<?> paginated = (PaginatedResponse<?>) body;
            apiResponse.setData(paginated.getData());
            apiResponse.setPagination(paginated.getPagination());
        } else {
            apiResponse.setData(body);
        }

        return apiResponse;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // Apply to all responses
        return true;
    }
}
