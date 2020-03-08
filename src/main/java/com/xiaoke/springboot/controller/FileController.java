package com.xiaoke.springboot.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileController {

        // uploadFile
        @RequestMapping("/uploadFile")
        @ResponseBody
        public Map<String, Object> uploadFile(MultipartFile myfile)
                throws IllegalStateException, IOException {
            // 原始名称
            String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
            // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
            String saveFilePath = "E://img";
            // 上传图片
            if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
                // 新的图片名称
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                // 新图片
                File newFile = new File(saveFilePath + "\\" + newFileName);
                // 将内存中的数据写入磁盘
                myfile.transferTo(newFile);
                // 将新图片名称返回到前端
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", "成功啦");
                map.put("url", newFileName);
                return map;
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("error", "图片不合法");
                return map;
            }
        }


}
