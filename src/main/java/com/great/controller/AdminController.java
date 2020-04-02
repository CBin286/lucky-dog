package com.great.controller;

import com.google.gson.Gson;
import com.great.javabean.*;
import com.great.log.Log;
import com.great.service.AdminJurTreeServiceImp;
import com.great.service.AdminServiceImp;
import com.great.service.UserTblServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminController <br/>
 * Description: <br/>
 * date: 2020/3/24 10:38<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminServiceImp adminServiceImp;
    @Resource
    private AdminJurTreeServiceImp adminJurTreeServiceImp;
    @Resource
    private UserTblServiceImp userTblServiceImp;


    //地址映射,path是个方法名,可以随便改动,{url}是参数
    @RequestMapping("/path/{url}")
    public String getUrl(@PathVariable(value = "url") String path){
        return "break/jsp/" +path;
    }


    @RequestMapping("/test")
    public String test(){
        System.out.println("aa");
        return "break/jsp/login";
    }

    @RequestMapping("/login")
    public String toLogin(String id, String password, HttpServletRequest request){
        String path=null;

        Admin admin=adminServiceImp.login(id,password);

        if (admin!=null){
            path="break/jsp/manage";

            Map<String,List<Menu>> map=adminServiceImp.getMenu(admin);

            request.getSession().setAttribute("account",admin.getAdminAccount());
            request.getSession().setAttribute("roleName",admin.getAdminAccount());
            request.getSession().setAttribute("adminId",admin.getAdminId());
            request.getSession().setAttribute("menumap",map);
        }

        return path;
    }



    @RequestMapping("/getRole")
    public void getRole(HttpServletResponse response) throws IOException{


        List<Role> roles = adminJurTreeServiceImp.getAllRole();

        ObjectResult or=new ObjectResult();

        or.setCode(0);
        or.setCount(10);
        or.setData(roles);
        // 设置浏览器字符集编码.
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        // 设置response的缓冲区的编码.
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(new Gson().toJson(or));
    }

    @RequestMapping("/getAllMenu")
    public void getAllMenu(Integer roleId, HttpServletResponse response) throws IOException {

        //获取所有菜单列表
        List<Tree> allMap=adminJurTreeServiceImp.getAllMenu();

        //根据角色id获取菜单
        List<Tree> roleMenu=adminJurTreeServiceImp.getRoleMenu(roleId);

        Map<String,List<Tree>> msg=new HashMap<>();

        msg.put("allMap",allMap);
        msg.put("roleMap",roleMenu);

        Gson gson=new Gson();
        // 设置浏览器字符集编码.
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        // 设置response的缓冲区的编码.
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(gson.toJson(msg));
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    @Log(operationType = "权限操作", operationName = "修改",operationRole = "管理")
    public void  updateMenu(String checkData,Integer roleId, HttpServletResponse resp) throws IOException {

        Gson gson=new Gson();

        Tree[] treex=gson.fromJson(checkData, Tree[].class);

        for(int i=0;i<treex.length;i++){
            System.out.println(treex[i]);
        }

        adminJurTreeServiceImp.updateRoleMenu(treex,roleId);

        resp.getWriter().print("");
    }

    @RequestMapping("/getUserTbl")
    @ResponseBody
    public void getUserTbl(Integer page, Integer limit, String user_account, String user_sex, String user_date, HttpServletRequest req, HttpServletResponse resp) throws IOException{

        Map<String,Object> userData=new HashMap();
        if(null!=user_account&&!"".equals(user_account)){
            userData.put("user_name",user_account);
        }
        if(null!=user_sex&&!"".equals(user_sex)){
            userData.put("user_sex",user_sex);
        }
        if (null!=user_date&&!"".equals(user_date)){
            userData.put("user_dateA",user_date.split(" - ")[0]);
            userData.put("user_dateB",user_date.split(" - ")[1]);
        }
        if (page==0||limit==0){
            page=0;
            limit=0;
        }
        int maxlimit=limit;
        int minlimit=(page-1)*limit;
        userData.put("minlimit",minlimit);
        userData.put("maxlimit",maxlimit);


        ObjectResult obj=userTblServiceImp.getUserTbl(userData);

        // System.out.println(obj);

        Gson gson=new Gson();

        String msg=gson.toJson(obj);

        req.setAttribute("userName",user_account);
        req.setAttribute("userSex",user_sex);
        req.setAttribute("userDate",user_date);

        resp.getWriter().print(msg);

    }

    @RequestMapping("/getUserMsg")
    @ResponseBody
    public void getUserMsg(Integer user_id, HttpServletRequest req) {

        User user = userTblServiceImp.getUserMsg(user_id);

        req.getSession().setAttribute("user",user);

    }

    @RequestMapping("/addUserByAdminAdd")
    @Log(operationType = "增加操作", operationName = "添加用户",operationRole = "管理")
    public void addUserByAdminAdd(User user, HttpServletResponse resp) throws IOException{

        userTblServiceImp.addUserByAdminAdd(user);

        resp.getWriter().print(" ");

    }

    @RequestMapping("/updateUser")
    @Log(operationType = "修改操作", operationName = "修改用户",operationRole = "管理")
    public void updateUser(User user, HttpServletResponse resp) throws IOException{

        userTblServiceImp.updateUser(user);

        resp.getWriter().print(" ");

    }

    @RequestMapping("/deleteUser")
    @Log(operationType = "删除操作", operationName = "删除用户",operationRole = "管理")
    public void deleteUser(Integer user_id, HttpServletResponse resp) throws IOException{

        userTblServiceImp.deleteUser(user_id);

        resp.getWriter().print(" ");

    }


    //文档审核
    @RequestMapping("/documentReview")
    @ResponseBody//ajax返回值json格式转换
    public void documentReview(String document_head,String user_name,String document_type_name,String time1,String time2,HttpServletRequest request, HttpServletResponse response) throws IOException {
        int page= Integer.parseInt(request.getParameter("page"));
        int limit= Integer.parseInt(request.getParameter("limit"));

        Map<String,Object>InfMap = new LinkedHashMap<>();
        int minLimit = (page-1)*limit;
        int maxLimit = limit;
        InfMap.put("document_head",document_head);
        InfMap.put("user_name",user_name);
        InfMap.put("document_type_name",document_type_name);
        InfMap.put("minlimit",minLimit);
        InfMap.put("maxlimit",maxLimit);
        InfMap.put("time1",time1);
        InfMap.put("time2",time2);

        ObjectResult objectResult=adminServiceImp.documentReview(InfMap);

        // 设置浏览器字符集编码.
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        // 设置response的缓冲区的编码.
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(new Gson().toJson(objectResult));
    }


}
