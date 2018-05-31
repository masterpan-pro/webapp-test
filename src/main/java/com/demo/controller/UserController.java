package com.demo.controller;

import com.demo.annotation.OperationLog;
import com.demo.entity.Log;
import com.demo.entity.User;
import com.demo.service.LogService;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        User user = new User(null, "admin", "123456", new Timestamp(System.currentTimeMillis()));
        userService.insert(user);
        List<User> users = userService.find();
        log.debug("{}", users);
        modelAndView.setViewName("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping("webjar")
    public ModelAndView webjar(ModelAndView modelAndView) {
        modelAndView.setViewName("webjar");
        return modelAndView;
    }

    @OperationLog(description = "查询User数据")
    @ResponseBody
    @RequestMapping("json")
    public List<User> index() {
        return userService.find();
    }

    @ResponseBody
    @RequestMapping("log")
    public List<Log> log() {
        return logService.find();
    }

    @ResponseBody
    @RequestMapping("file")
    public void downloadA(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "ids[]") List<Long> ids) throws IOException {
        List<String> pathArr;
        File file;
        String tempZipFile = null;
        if (ids.size() == 1) {
            pathArr = Arrays.asList("/Users/pengpan/Documents/文档.txt");
        } else {
            pathArr = Arrays.asList(
                    "/Users/pengpan/Documents/文档.txt",
                    "/Users/pengpan/Documents/doc.txt");
        }
        if (pathArr.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('未找到对应文件！');history.go(-1);</script>");
            response.getWriter().flush();
            return;
        } else if (pathArr.size() == 1) {
            file = new File(pathArr.get(0));
        } else {
            tempZipFile = request.getSession().getServletContext().getRealPath("/").concat("/" + System.currentTimeMillis() + ".zip");
            byte[] buffer = new byte[1024];
            try {
                FileOutputStream fos = new FileOutputStream(tempZipFile);
                ZipOutputStream zos = new ZipOutputStream(fos);
                for (String uri : pathArr) {
                    File zipFile = new File(uri);
                    if (zipFile.exists()) {
                        ZipEntry ze = new ZipEntry(zipFile.getName());
                        zos.putNextEntry(ze);
                        FileInputStream in = new FileInputStream(zipFile);
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        in.close();
                    }
                }
                zos.closeEntry();
                zos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            file = new File(tempZipFile);
        }

        if (!file.exists()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('文件不存在！');history.go(-1);</script>");
            response.getWriter().flush();
            return;
        }
        InputStream in = new FileInputStream(file);
        String fileName = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
        String mimeType = URLConnection.guessContentTypeFromName(fileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
        if (tempZipFile != null) {
            file.delete();
        }
    }
}
