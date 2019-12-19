package com.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tools.io.FileTool;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadAct {
    public static String database_path="";
    
    
    @RequestMapping("/upload")
    public String upload(){
        return "WEB-INF/html/upload";
    }
    
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile image_file,HttpServletRequest request,String name){
        try {
            String folder="/image";
            String file_name=image_file.getOriginalFilename();
            if(image_file!=null){
                FileTool.springFileUpload(image_file, request, folder ,file_name);
            }
            File file=FileTool.multipartToFile(image_file);
            
            database_path=folder+"/"+file_name;
            return "文件上传成功:"+FileTool.getFileVisitPath(  FileTool.getFileVisitPath(database_path)  );
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }
    
    @RequestMapping("/createFile")
    @ResponseBody
    public String createFile(MultipartFile image_file,HttpServletRequest request,String name){
        File file = null;
        InputStream is = null;
        OutputStream fiso = null;
        try {
            String folder="/image";
            String file_name=image_file.getOriginalFilename();
            database_path=folder+"/"+file_name;
            String disk_path=FileTool.getFileDiskPath(database_path);
            
            file=FileTool.multipartToFile(image_file);
            
            is=new FileInputStream(file);
//            InputStream is=image_file.getInputStream();
            fiso=new FileOutputStream(disk_path);
            
            int l=0;
            byte b[] = new byte[1024000];
            while ((l=is.read(b))!=-1) {
                fiso.write(b,0,l);
            }
            fiso.flush();
            
            
            database_path=folder+"/"+file_name;
            return "文件上传成功:"+FileTool.getFileVisitPath(  FileTool.getFileVisitPath(database_path)  );
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败";
        } finally {
            try {
                fiso.close();
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            file.deleteOnExit();  当web程序停止时删除
            file.delete();   //直接删除
        }
    }
    
    
    @RequestMapping("deleteFile")
    @ResponseBody
    public String delete(){
        //删除文件,database_path为保存路径。
        String filePath=FileTool.getFileDiskPath(database_path);
        FileTool.delete(filePath);
        return "删除文件："+filePath;
    }
    
}
