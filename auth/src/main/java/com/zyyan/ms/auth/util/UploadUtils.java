/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.ms.auth.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传工具类
 *
 */
public class UploadUtils {

    /**
     * 表单字段常量
     */
    public static final String FORM_FIELDS = "form_fields";
    /**
     * 文件域常量
     */
    public static final String FILE_FIELDS = "file_fields";

    //返回信息分类
    public static final String VALIDATE_ERROR = "validateError";//严重错误信息
    public static final String SAVE_ERROR = "saveError";//报错错误信息
    public static final String SAVE_PATH = "savePath";//报错的绝对路径
    public static final String SAVE_URL = "saveUrl";//保存的相对路径
    public static final String FILE_PATH = "fileUrl";//文件

    public static final String PARAM_NAME = "name";//文件名(带后缀)
    public static final String PARAM_TYPE = "type";//文件类型
    public static final String PARAM_SUFFIX = "suffix";//后缀名
    public static final String PARAM_URL = "url";//相对路径
    public static final String PARAM_PATH = "path";//绝对路径（物理路径）
    public static final String PARAM_ERROR = "error";//验证错误
    public static final String PARAM_SYS_ERROR = "sysError";//系统错误

    //文件归类的文件夹名称
    public static final String DIR_NAME_IMAGES = "images";
    public static final String DIR_NAME_FLASHS = "flashs";
    public static final String DIR_NAME_MEDIAS = "medias";
    public static final String DIR_NAME_FILES = "files";

    // 最大文件大小
    private long maxSize = 1000000;
    //返回内容Map
    private Map<String, String> paramMap = new HashMap<String, String>();
    // 文件保存目录相对路径
    private String basePath = "upload/files";
    // 上传临时路径
    private static final String TEMP_PATH = "/temp";
    private String tempPath = basePath + TEMP_PATH;
    // 若不指定则文件名默认为 yyyyMMddHHmmss_xyz
    private String fileName;

    // 文件保存目录路径
    private String savePath;
    // 文件保存目录url
    private String saveUrl;
    // 文件最终的url包括文件名
    private String fileUrl;

    public UploadUtils() {
        paramMap.put(PARAM_ERROR, "");
    }

    /**
     * 文件上传
     *
     * @param request
     * @return infos info[0] 验证文件域返回错误信息 info[1] 上传文件错误信息 info[2] savePath
     * info[3] saveUrl info[4] fileUrl
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> uploadFile(HttpServletRequest request) {
        // 验证
        String validate = this.validateFields(request);
        // 初始化表单元素
        Map<String, Object> fieldsMap = new HashMap<String, Object>();
        if (validate.equals("true")) {
            fieldsMap = this.initFields(request);
        } else {
            paramMap.put(PARAM_ERROR, paramMap.get(PARAM_ERROR) + "," + validate);
        }
        // 上传
        List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtils.FILE_FIELDS);
        String saveInfo = null;
        if (fiList != null) {
            //目前我们用的都是单文件上传，如果真的有多文件上传，此处的全局变量等需要更改
            //，否则只会记录最后一个文件的上传信息，切记
            for (FileItem item : fiList) {
                saveInfo = this.saveFile(item);
                if (!saveInfo.equals("true")) {
                    paramMap.put(PARAM_ERROR, paramMap.get(PARAM_ERROR) + "," + saveInfo);
                }
            }
        }
        return paramMap;
    }

    /**
     * 上传验证,并初始化文件目录
     *
     * @param request
     */
    private String validateFields(HttpServletRequest request) {
        String errorInfo = "true";
        // 获取内容类型
        String contentType = request.getContentType();
        int contentLength = request.getContentLength();
        // 文件保存目录路径
        savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        // 文件保存目录URL
//        saveUrl = request.getContextPath() + "/" + basePath + "/";
        saveUrl = basePath + "/";
        File uploadDir = new File(savePath);
        // 检查目录
        if (!uploadDir.isDirectory()) {
            File dirFile = new File(savePath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
        }
        if (contentType == null || !contentType.startsWith("multipart")) {
            // TODO
            System.out.println("请求不包含multipart/form-data流");
            errorInfo = "请求不包含multipart/form-data流";
        } else if (maxSize < contentLength) {
            // TODO
            System.out.println("上传文件大小超出文件最大大小");
            errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
        } else if (!ServletFileUpload.isMultipartContent(request)) {
            // TODO
            errorInfo = "请选择文件";
        } else if (!uploadDir.canWrite()) {
            // TODO
            errorInfo = "上传目录[" + savePath + "]没有写权限";
        } else {
            // 创建文件夹
            File saveDirFile = new File(savePath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            savePath += ymd + "/";
            saveUrl += ymd + "/";
            File dirFile = new File(savePath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            // 获取上传临时路径
            tempPath = request.getSession().getServletContext().getRealPath("/") + tempPath + "/";
            File file = new File(tempPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        return errorInfo;
    }

    /**
     * 处理上传内容
     *
     * @param request
     * @param maxSize
     * @return
     */
//    @SuppressWarnings("unchecked")
    private Map<String, Object> initFields(HttpServletRequest request) {

        // 存储表单字段和非表单字段
        Map<String, Object> map = new HashMap<String, Object>();

        // 第一步：判断request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 第二步：解析request
        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 阀值,超过这个值才会写到临时目录,否则在内存中
            factory.setSizeThreshold(1024 * 1024 * 10);
            factory.setRepository(new File(tempPath));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setHeaderEncoding("UTF-8");

            // 最大上传限制
            upload.setSizeMax(maxSize);

            /* FileItem */
            List<FileItem> items = null;
            // Parse the request
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // 第3步：处理uploaded items
            if (items != null && items.size() > 0) {
                Iterator<FileItem> iter = items.iterator();
                // 文件域对象
                List<FileItem> list = new ArrayList<FileItem>();
                // 表单字段
                Map<String, String> fields = new HashMap<String, String>();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    // 处理所有表单元素和文件域表单元素
                    if (item.isFormField()) { // 表单元素
                        String name = item.getFieldName();
                        String value = item.getString();
                        fields.put(name, value);
                    } else { // 文件域表单元素
                        list.add(item);
                    }
                }
                map.put(FORM_FIELDS, fields);
                map.put(FILE_FIELDS, list);
            }
        }
        return map;
    }

    /**
     * 保存文件
     *
     * @param obj 要上传的文件域
     * @param file
     * @return
     */
    private String saveFile(FileItem item) {
        String error = "true";
        String fileName = item.getName();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (item.getSize() > maxSize) { // 检查文件大小
            // TODO
            error = "上传文件大小超过限制";
        } else {
            //文件名重命名
            String newFileName;
//            if ("".equals(fileName.trim())) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
//            } else {
//                newFileName = fileName;
//            }
            // .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
            fileUrl = saveUrl + newFileName;
            try {
                File uploadedFile = new File(savePath, newFileName);

                item.write(uploadedFile);

                paramMap.put(PARAM_NAME, fileName);
                paramMap.put(PARAM_SUFFIX, fileExt);
                paramMap.put(PARAM_TYPE, item.getContentType());
                paramMap.put(PARAM_URL, fileUrl);
                paramMap.put(PARAM_PATH, uploadedFile.getPath());

                /*
                 * FileOutputStream fos = new FileOutputStream(uploadFile); // 文件全在内存中 if (item.isInMemory()) { fos.write(item.get()); } else { InputStream is = item.getInputStream(); byte[] buffer =
                 * new byte[1024]; int len; while ((len = is.read(buffer)) > 0) { fos.write(buffer, 0, len); } is.close(); } fos.close(); item.delete();
                 */
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败了！！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return error;
    }

    /**
     * **********************get/set方法*********************************
     */
    public String getSavePath() {
        return savePath;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        tempPath = basePath + TEMP_PATH;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
