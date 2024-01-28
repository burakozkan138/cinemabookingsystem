package com.burakozkan138.cinemabookingsystem.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponse;

@RestControllerAdvice
public class BaseResponseAdvice<T> implements ResponseBodyAdvice<BaseResponse<T>> {

  @Override
  @Nullable
  public BaseResponse<T> beforeBodyWrite(@Nullable BaseResponse<T> body, MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {
    if (body != null) {
      response.setStatusCode(body.getStatus());
    }

    return body;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return returnType.getParameterType().isAssignableFrom(BaseResponse.class);
  }

}
