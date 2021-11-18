package com.menej;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Order
@Configuration
public class Filter implements javax.servlet.Filter {

    Logger log = LoggerFactory.getLogger(Filter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = (req.getHeader("Authorization") ==  null)
                ? "" : req.getHeader("Authorization");

        String sessionId = (req.getHeader("sessionid") ==  null)
                ? "" : req.getHeader("sessionid");

        String userId = (req.getHeader("userid") == null)
                ? "0" : req.getHeader("userid");

        String[] urls = req.getRequestURI().split("/");
        String url = req.getRequestURI();
//        String url = (urls.length > 1) ? urls[1] : "";
//        System.out.println("url split length : "+urls.length);
//        log.info("url acccess : "+url);
//        if(!url.equals("start_data") && !url.equals("login") && !url.equals("register") &&
//                !url.equals("confirmation_registration") && !url.equals("pic_profile") &&
//                !url.equals("export_excel_tab") && !url.equals("file") &&
//                !url.equals("recover_account") && !url.equals("submit_forget_password") &&
//                !url.equals("loginApp"))
//        {
        if(!url.contains("start_data") && !url.contains("login") && !url.contains("register") &&
                !url.contains("confirmation_registration") && !url.contains("pic_profile") &&
                !url.contains("export_excel_tab") && !url.contains("file") &&
                !url.contains("recover_account") && !url.contains("submit_forget_password") &&
                !url.contains("loginApp"))
        {


            /*read token*/
            JwtTokenUtil jwt = new JwtTokenUtil();
            jwt.readToken(token);

            Utils utils = new Utils();
            Boolean isAccess = utils.getAccess(Integer.parseInt(userId), sessionId);
//            Boolean isAccess = utils.getAccess(Integer.parseInt(userId), sessionId);
            if (!isAccess && !"OPTIONS".equalsIgnoreCase(req.getMethod())){
                /*jika session id tidak valid*/
                this.response(res);
                return;
            }
        }

        chain.doFilter(req, res);
    }

    public ObjectMapper response(HttpServletResponse res) throws IOException{
        /*set header response*/
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Invalid token or session id");
        errorDetails.put("code", 401);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res.getWriter(), errorDetails);
        return mapper;
    }
}
