package com.great.interception;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: LoginInterception <br/>
 * Description: <br/>
 * date: 2020/3/27 11:01<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
public class LoginInterception implements HandlerInterceptor {
    /**
     *  该方法会在控制器方法前执行，其返回值表示是否中断后续操作。当其返回值为true时，表示继续向下执行；
     * 当其返回值为false时，会中断后续的所有操作（包括调用下一个拦截器和控制器类中的方法执行等）
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//获取请求路径url
        String url = request.getRequestURI();
        System.out.println(url);
        //判断是否包含admin,表示是否是后端操作
        if (url.contains("admin")){
            //如果是登录页面就放行
            if (url.endsWith("/admin/test")||url.endsWith("/admin/login")){
                return true;
            }
            //如果是登录操作就放行
            if (url.endsWith("/break/jsp/login.jsp")){
                return true;
            }
            //判断是否登录
            if (null!=request.getSession()&&null!=request.getSession().getAttribute("account")){
                return true;
            }

            response.sendRedirect(request.getContextPath()+"/admin/test");
            //文件都放在WEB-INF下,所有无法通过重定向来实现跳转，可以通过请求转发来跳转
            // request.getRequestDispatcher("/web/break/jsp/login.jsp").forward(request,response);
            System.out.println("后台被拦截");
            return false;
        }else {

            //如果是登录或注册页面就放行
            if (url.endsWith("/front/jsp/login.jsp")||url.endsWith("/front/jsp/reg.jsp")){
                return true;
            }
            //如果是前端登录、注册操作就放行
            if (url.endsWith("/user/test")||url.endsWith("/user/toReg")||url.endsWith("/user/login")){
                return true;
            }
            if (null!=request.getSession()&&null!=request.getSession().getAttribute("user_id")){
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/user/test");
            //由于把文件都放在WEB-INF下,所有无法通过重定向来实现跳转，可以通过请求转发来跳转
            //request.getRequestDispatcher("/front/jsp/login.jsp").forward(request,response);
            System.out.println("前台被拦截");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
