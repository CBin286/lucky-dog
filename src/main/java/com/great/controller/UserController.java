package com.great.controller;


import com.google.gson.Gson;
import com.great.javabean.*;
import com.great.log.Log;
import com.great.service.UserServiceImp;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName: UserController <br/>
 * Description: <br/>
 * date: 2020/3/24 17:25<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImp userServiceImpl;
    @Resource
    private Document document;


    @RequestMapping("/path/{url}")
    public String getUrl(@PathVariable(value = "url") String path){
        return "/fount/jsp/" +path;
    }

    @RequestMapping("/test")
    public String  test(){
        return "fount/jsp/login";
    }

    @RequestMapping("/login")
    public String toLogin(String id, String password, HttpServletRequest request){

        //System.out.println(id);
        //System.out.println(password);

        User user=userServiceImpl.login(id,password);

        //System.out.println(user);

        request.getSession().setAttribute("user_id",user.getUser_id());
        request.getSession().setAttribute("roleName",user.getUser_account());

        //System.out.println(user.getUser_id());

        return "/fount/jsp/manage";
    }

    @RequestMapping("/testUserAccount")
    public void testUserAccount(String user_account, HttpServletResponse response, HttpServletRequest request) throws IOException {

        System.out.println(user_account);

        boolean flag=userServiceImpl.testUserAccount(user_account);

        if (flag){
            response.getWriter().print("Error");
        }else {
            response.getWriter().print("Success");

        }

    }

    @RequestMapping("/toReg")
    @Log(operationType = "增加操作", operationName = "注册用户",operationRole = "用户")
    public String toReg(User user, HttpServletResponse response, HttpServletRequest request) throws IOException {

        //System.out.println(user);

        userServiceImpl.addUserByReg(user);

        response.getWriter().print("Success");

        return null;
    }

    @RequestMapping(value="/download",method= RequestMethod.GET) //匹配的是href中的download请求
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename,
                                           Model model) throws IOException{

        String downloadFilePath="D:\\java_jdk\\idea_project\\MavenDemoProject\\src\\main\\webapp\\file";//从我们的上传文件夹中去取

        File file = new File(downloadFilePath+File.separator+filename);//新建一个文件

        HttpHeaders headers = new HttpHeaders();//http头信息

        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");//设置编码

        headers.setContentDispositionFormData("attachment", downloadFileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);

    }

    //文件上传
    @RequestMapping("/upload")
    @ResponseBody//ajax返回值json格式转换
    @Log(operationType = "文件上传", operationName = "文件上传",operationRole = "用户")
    public String upload(MultipartFile file, String downScore, String bookName, String intro, HttpServletRequest request) throws IOException {
        System.out.println("downScore==="+downScore);//分数
        System.out.println("bookName==="+bookName);//标题
        System.out.println("intro==="+intro);//简介
        if (!StringUtils.isEmpty(file) && file.getSize()>0&&null!=downScore&&!"".equals(downScore)&&null!=bookName&&!"".equals(bookName)){
            String name= file.getOriginalFilename();//是得到上传时的文件名。
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");//设置时间格式
            String rtime = sdf.format(new Date());//操作时间

            document.setDocument_integral(Double.valueOf(downScore));
            document.setDocument_head(bookName);
            document.setDocument_synopsis(intro);
            document.setDocument_date(rtime);
            document.setDocument_state(1);
            document.setDocument_down_num(0);
            document.setUser_id( user_id);
            document.setDocument_type_id(-1);

            //System.out.println("文件类型："+suffix);

            List<DocumentType> documentTypes=userServiceImpl.findDocumentType();

            //System.out.println(documentTypes);

            for (int i= 0;i<documentTypes.size();i++){

                if (suffix.equals(documentTypes.get(i).getDocument_type_name())){
                    document.setDocument_type_id(documentTypes.get(i).getDocument_type_id());
                }

            }
            if (document.getDocument_type_id()!=-1){//集合是否包含要上传的文档类型

                Integer a= userServiceImpl.insertInfByUid(document);

                file.transferTo(new File("D:\\java_jdk\\idea_project\\springboot\\src\\main\\webapp\\file\\" + name));
                return "{\"code\":0, \"msg\":\"\", \"data\":{}}";
            }
            return "{\"code\":2, \"msg\":\"\", \"data\":{}}";
        }
        return "{\"code\":3, \"msg\":\"\", \"data\":{}}";
    }


    @RequestMapping("/userScore")
    public void userScore(HttpServletRequest request, HttpServletResponse response,Integer page, Integer limit) throws IOException {

        // 设置浏览器字符集编码.
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        // 设置response的缓冲区的编码.
        response.setCharacterEncoding("UTF-8");

        Integer user_id= (Integer) request.getSession().getAttribute("user_id");

        ObjectResult objectResult = userServiceImpl.getScore(user_id,page,limit);

        response.getWriter().print(new Gson().toJson(objectResult));

    }

    @RequestMapping("/getDocumentTbl")
    public void getDocumentTbl(Integer page, Integer limit, String document_head, HttpServletResponse response) throws IOException {

        // 设置浏览器字符集编码.
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        // 设置response的缓冲区的编码.
        response.setCharacterEncoding("UTF-8");

        ObjectResult objectResult = userServiceImpl.getDocumentTbl(page, limit, document_head);

        response.getWriter().print(new Gson().toJson(objectResult));

    }

    @RequestMapping("/getUserFile")
    public void getUserFile(Integer page, Integer limit, String time1, String time2, String state, String type, HttpServletResponse response,HttpServletRequest request) throws IOException{

        Map<String,Object> map=new HashMap<>();

        int maxlimit=limit;
        int minlimit=(page-1)*limit;

        map.put("maxlimit",maxlimit);
        map.put("minlimit",minlimit);
        map.put("user_id",request.getSession().getAttribute("user_id"));
        if(time1!=null&&!time1.equals("")){
            map.put("time1",time1);
        }
        if(time2!=null&&!time2.equals("")){
            map.put("time1",time2);
        }
        if(state!=null&&!state.equals("")){
            map.put("time1",state);
        }
        if(type!=null&&!type.equals("")){
            map.put("time1",type);
        }
        System.out.println(map);
        // 设置浏览器字符集编码.
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        // 设置response的缓冲区的编码.
        response.setCharacterEncoding("UTF-8");

        ObjectResult objectResult = userServiceImpl.getUserDocumentTbl(map);

        response.getWriter().print(new Gson().toJson(objectResult));

    }


    @RequestMapping("/judgeScore")
    public void judgeScore(Document document ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer user_id = (Integer) request.getSession().getAttribute("user_id");
        System.out.println("下载分=="+document.getDocument_integral());
        Integer num= userServiceImpl.checkUserScore(user_id,document.getDocument_integral());
        System.out.println(num);
        if (1==num){
            response.getWriter().print("success");
        }else {
            response.getWriter().print("error");
        }

    }


    //文件下载
    @RequestMapping("/downDocumentInf")
    @Log(operationType = "下载操作", operationName = "下载文件",operationRole = "用户")
    public ResponseEntity<byte[]> downDocumentInf(String filename) throws IOException {

        //把文件以二进制形式写回
        String downloadFilePath="D:\\java_jdk\\idea_project\\springboot\\src\\main\\webapp\\file";//从我们的上传文件夹中去取

        File file = new File(downloadFilePath+File.separator+filename);//新建一个文件

        HttpHeaders headers = new HttpHeaders();//http头信息

        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");//设置编码

        headers.setContentDispositionFormData("attachment", downloadFileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        //更新数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }


}