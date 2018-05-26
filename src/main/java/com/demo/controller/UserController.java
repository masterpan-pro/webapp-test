package com.demo.controller;

import com.demo.entity.User;
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
import java.nio.charset.Charset;
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

    @ResponseBody
    @RequestMapping("json")
    public List<User> index() {
        return userService.find();
    }

    @ResponseBody
    @RequestMapping("file")
    public void downloadA(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "ids[]") List<Long> ids) throws IOException {
        // 通过ids获取链接
        List<String> uris = Arrays.asList(
                "/Users/pengpan/Documents/九江银行网贷平台资金存管系统接口规范V2.80.pdf",
                "/Users/pengpan/Documents/hello.txt");
        File file;
        String tempZipFile = null;
        if (uris.isEmpty()) {
            // 文件为空
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        } else if (uris.size() > 1) {
            //多文件下载，压缩
            tempZipFile = request.getSession().getServletContext().getRealPath("/").concat("/" + System.currentTimeMillis() + ".zip");
            byte[] buffer = new byte[1024];
            try {
                FileOutputStream fos = new FileOutputStream(tempZipFile);
                ZipOutputStream zos = new ZipOutputStream(fos);

                for (String uri : uris) {
                    File zipFIle = new File(uri);
                    ZipEntry ze = new ZipEntry(zipFIle.getName());
                    zos.putNextEntry(ze);
                    FileInputStream in = new FileInputStream(zipFIle);
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    in.close();
                }
                zos.closeEntry();
                //remember close it
                zos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            file = new File(tempZipFile);
        } else {
            file = new File(uris.get(0));
        }

        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
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
