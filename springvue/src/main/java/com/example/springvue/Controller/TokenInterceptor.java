package com.example.springvue.Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

//    public String GetToken(){
//        String token = request.getHeader("token");
//    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        //跨域请求会首先发送一个option请求，直接通过拦截器
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader("Authorization");  //取token
        if(token != null ){
            try {
                boolean result = TokenUtil.verify(token);
                if(result){
                    System.out.println("通过拦截器");
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

//        if(TokenUtil.isTokenExpired(token)){
//            JSONObject json = new JSONObject();
//            json.put("msg","Token is invalid");
//            json.put("code","500");
//            response.getWriter().append(json.toString());
//            return false;
//        }
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json = new JSONObject();
            json.put("msg","token verify fail");
            json.put("code","500");
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            return false;
        }

        return false;
    }
}
