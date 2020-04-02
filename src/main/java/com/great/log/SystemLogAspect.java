package com.great.log;


import com.great.javabean.SystemLog;
import com.great.service.SystemLogServiceImp;
import org.jboss.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zx
 * @desc 切点类 
 */

@Aspect
@Component
public class SystemLogAspect {

    //注入Service用于把日志保存数据库  
    @Resource  //这里我用resource注解
      private SystemLogServiceImp systemLogService;
    
    
  //这里的zxtest要和log4j.properties里的配置一致，否则写不到文件中
	private static Logger logger = Logger.getLogger("zxtest");  
    
    //Controller层切点  
    @Pointcut("execution (* com.great.controller.*.*(..)) && @annotation(com.great.log.Log)")
    public  void controllerAspect() {  
    }  
    
    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */ 
//    @Before("controllerAspect()")
//    public void doBefore(JoinPoint joinPoint) {
//        System.out.println("==========执行controller前置通知===============");
//
//        if(logger.isInfoEnabled()){
//            logger.info("before " + joinPoint);
//        }
//    }
    
    //配置controller环绕通知,使用在方法aspect()上注册的切入点
//      @Around("controllerAspect()")
//      public void around(JoinPoint joinPoint){
//          System.out.println("==========开始执行controller环绕通知===============");
//          long start = System.currentTimeMillis();
//          //(signature是信号,标识的意思):获取被增强的方法相关信息.其后续方法有两个
//          //getDeclaringTypeName: 返回方法所在的包名和类名
//          //getname(): 返回方法名
//          String methodName = joinPoint.getSignature().getName();
//
//          try {
//              //ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行
//              ((ProceedingJoinPoint) joinPoint).proceed();
//              long end = System.currentTimeMillis();
//              if(logger.isInfoEnabled()){
//                  logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
//              }
//              System.out.println("==========结束执行controller环绕通知===============");
//          } catch (Throwable e) {
//        	  System.out.println("环绕通知中的异常--------------------------------"+methodName+"-------"+e.getMessage());
//              long end = System.currentTimeMillis();
//              if(logger.isInfoEnabled()){
//                  logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
//              }
//          }
//      }
    
    /** 
     * 后置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */  
    @After("controllerAspect()")  
    public  void after(JoinPoint joinPoint) throws Throwable{
  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String roleName="";
        if (session.getAttribute("roleName")!=null){
           roleName+= (String) session.getAttribute("roleName") ;
        }else {
           roleName+="未知用户";
        }

        String role="";

        //请求的IP
        String ip = request.getRemoteAddr();
         try {  
            
            String targetName = joinPoint.getTarget().getClass().getName();  
            String methodName = joinPoint.getSignature().getName();  
            Object[] arguments = joinPoint.getArgs();  
            Class targetClass = Class.forName(targetName);  
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            String operationRole = "";
             for (Method method : methods) {  
                 if (method.getName().equals(methodName)) {  
                    Class[] clazzs = method.getParameterTypes();  
                     if (clazzs.length == arguments.length) {  
                         operationType = method.getAnnotation(Log.class).operationType();
                         operationName = method.getAnnotation(Log.class).operationName();
                         operationRole = method.getAnnotation(Log.class).operationRole();
                         break;  
                    }  
                }  
            }
            //*========控制台输出=========*//  
            System.out.println("=====controller后置通知开始=====");  
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
            System.out.println("方法描述:" + operationName);  
            System.out.println("请角色:" + operationRole);
            System.out.println("请求IP:" + ip);  
            //*========数据库日志=========*//
            if(operationRole.equals("用户")){
                role+="用户";
            }else {
                role+="管理";
            }
             SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
             String date = df.format(new Date());

             SystemLog log = new SystemLog();
             log.setLog_operator(roleName);
             log.setLog_operator_ip(ip);
             log.setLog_operator_role(role);
             log.setLog_date(date);
             log.setLog_content(operationName);
             log.setLog_result("success");
             log.setLog_type(operationType);
            //保存数据库  
           systemLogService.insert(log);
            System.out.println("=====controller后置通知结束=====");  
        }  catch (Exception e) {  
            //记录本地异常日志  
            logger.error("==后置通知异常==");  
            logger.error("异常信息:{}------"+ e.getMessage());  

            throw e;
        }  
    } 
    
    //配置后置返回通知,使用在方法aspect()上注册的切入点
//      @AfterReturning("controllerAspect()")
//      public void afterReturn(JoinPoint joinPoint){
//          System.out.println("=====执行controller后置返回通知=====----");  
//              if(logger.isInfoEnabled()){
//                  logger.info("afterReturn " + joinPoint);
//              }
//      }
    
    /** 
     * 异常通知 用于拦截记录异常日志 
     * 
     * @param joinPoint 
     * @param e 
     */ 
     @AfterThrowing(pointcut = "controllerAspect()", throwing="e")  
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable{  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

         String roleName="";
         if (session.getAttribute("roleName")!=null){
             roleName+= (String) session.getAttribute("roleName") ;
         }else {
             roleName+="未知用户";
         }

         String role="";

        //获取请求ip  
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串  
        System.out.println("-------------------异常通知开始-----------------------");
        
        String params = "";  
         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {  
             for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
//                params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";  
            	 params += joinPoint.getArgs()[i] + ";"; 
            }  
        }  
         try {  
             
             String targetName = joinPoint.getTarget().getClass().getName();  
             String methodName = joinPoint.getSignature().getName();  
             Object[] arguments = joinPoint.getArgs();  
             Class targetClass = Class.forName(targetName);  
             Method[] methods = targetClass.getMethods();
             String operationType = "";
             String operationName = "";
             String operationRole = "";
              for (Method method : methods) {  
                  if (method.getName().equals(methodName)) {  
                     Class[] clazzs = method.getParameterTypes();  
                      if (clazzs.length == arguments.length) {  
                          operationType = method.getAnnotation(Log.class).operationType();
                          operationName = method.getAnnotation(Log.class).operationName();
                          operationRole = method.getAnnotation(Log.class).operationRole();
                          break;  
                     }  
                 }  
             }

             /*========控制台输出=========*/  
            System.out.println("=====异常通知开始=====");  
            System.out.println("异常代码:" + e.getClass().getName());  
            System.out.println("异常信息:" + e.getMessage());  
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
            System.out.println("方法描述:" + operationName);  
            System.out.println("请求人:" + roleName);
            System.out.println("请求IP:" + ip);  
            System.out.println("请求参数:" + params);  
               /*==========数据库日志=========*/
             SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
             String date = df.format(new Date());
             if(operationRole.equals("用户")){
                 role+="用户";
             }else {
                 role+="管理";
             }
            SystemLog log = new SystemLog();
            log.setLog_operator(roleName);
            log.setLog_operator_ip(ip);
            log.setLog_operator_role(role);
            log.setLog_date(date);
            log.setLog_content(operationName);
            log.setLog_result("success");
            log.setLog_type(operationType);
            //保存数据库
            systemLogService.insert(log);
            System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {  
            //记录本地异常日志  
            logger.error("==异常通知异常==");  
            logger.error("异常信息:{}------"+ ex.getMessage());
        }  
         /*==========记录本地异常日志==========*/  
//        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}-----"+joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);  
  
    }

}