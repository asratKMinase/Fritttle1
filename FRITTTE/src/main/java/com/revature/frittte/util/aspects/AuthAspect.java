package com.revature.frittte.util.aspects;

import com.revature.frittte.exception.AuthenticationException;
import com.revature.frittte.util.web.SecureEndpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuthAspect {

    private HttpServletRequest request;

    @Autowired
    public AuthAspect(HttpServletRequest request){
        this.request = request;
    }

    @Around("@annotation(com.revature.frittte.util.web.SecureEndpoint)")
    public Object secureEndpoints(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        SecureEndpoint anno = method.getAnnotation(SecureEndpoint.class);

        List<String> allowedUsers = Arrays.asList(anno.allowedUsers());


        if(request.getParameter("username") == null) throw new AuthenticationException("Please log in");

        String username = request.getParameter("username");
        if(anno.isLoggedIn() == true && username == null) throw new AuthenticationException("Please log in before requesting this endpoint");
        if(!allowedUsers.isEmpty() && !allowedUsers.contains(username)) throw new AuthenticationException("Forbidden request made to sensistive endpoint by user: " + username);

        // This continues to execute the method in question (method below the @SecureEndpoint annotation)
        Object returned = pjp.proceed();

        return returned;
    }

}
