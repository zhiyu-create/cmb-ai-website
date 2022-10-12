package com.example.cmbaiwebsite.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局日志处理
 */
@Component
@Aspect
public class LogAspect {


        private final static Logger log = LoggerFactory.getLogger(LogAspect.class);

        @Pointcut("execution(* com.example.cmbaiwebsite.controller..*(..))")
        public void requestServer() {
        }

        @Around("requestServer()")
        public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            long start = System.currentTimeMillis();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Object result = proceedingJoinPoint.proceed();
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setIp(request.getRemoteAddr());
            requestInfo.setUrl(request.getRequestURL().toString());
            requestInfo.setHttpMethod(request.getMethod());
            requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                    proceedingJoinPoint.getSignature().getName()));
            requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
            requestInfo.setResponse(result);
            requestInfo.setTimeCost(System.currentTimeMillis() - start);
            log.info("Request And Response Info      : {}", JSON.toJSONString(requestInfo));

            return result;
        }


        @AfterThrowing(pointcut = "requestServer()", throwing = "e")
        public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
            requestErrorInfo.setIp(request.getRemoteAddr());
            requestErrorInfo.setUrl(request.getRequestURL().toString());
            requestErrorInfo.setHttpMethod(request.getMethod());
            requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName()));
            requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
            requestErrorInfo.setException(e);
            log.info("Error Request And Response Info      : {}", JSON.toJSONString(requestErrorInfo));
        }

        /**
         * 获取入参
         *
         * @param proceedingJoinPoint
         * @return
         */
        private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
            //参数名
            String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
            //参数值
            Object[] args = proceedingJoinPoint.getArgs();
            Object[] arguments = handleParamValue(args);
            return buildRequestParam(paramNames, arguments);
        }

        private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
            //参数名
            String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            //参数值
            Object[] args = joinPoint.getArgs();
            Object[] arguments = handleParamValue(args);
            return buildRequestParam(paramNames, arguments);
        }

        private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
            Map<String, Object> requestParams = new HashMap<>();
            for (int i = 0; i < paramNames.length; i++) {
                Object value = paramValues[i];
                //如果是文件对象
                if (value instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) value;
                    value = file.getOriginalFilename();  //获取文件名
                }
                requestParams.put(paramNames[i], value);
            }
            return requestParams;
        }

        public Object[] handleParamValue(Object[] args) {
            Object[] arguments = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse) {
                /*
                ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException:
                It is illegal to call this method if the current request is not in asynchronous mode
                (i.e. isAsyncStarted() returns false)

                ServletResponse不能序列化 从入参里排除，否则报异常：
                java.lang.IllegalStateException: getOutputStream() has already been called for this response
                 */
                    continue;
                }
                arguments[i] = args[i];
            }
            return arguments;
        }

        @Data
        public class RequestInfo {
            private String ip;
            private String url;
            private String httpMethod;
            private String classMethod;
            private Object requestParams;
            private Object response;
            private Long timeCost;
        }

        @Data
        public class RequestErrorInfo {
            private String ip;
            private String url;
            private String httpMethod;
            private String classMethod;
            private Object requestParams;
            private RuntimeException exception;
        }
}


