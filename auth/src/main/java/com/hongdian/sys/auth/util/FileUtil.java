package com.hongdian.sys.auth.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("all")
public class FileUtil {

    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());
    private static final int BUFFER_SIZE = 16 * 1024;
    private static final double SIZE = 1024;
    private static final int DECIMAL_DIGITS = 3;
    public static final String FILE_PATH = "C:/SSHupload/";

    /**
     * 设置导出中response属性
     *
     * @param response
     * @param clearCacheFlag
     * @param outFileName
     */
    public static void setResponseAttr(HttpServletResponse response, Boolean clearCacheFlag, String outFileName) throws Exception {
        // 进行转码，使其支持中文文件名
        response.reset();
        response.setContentType("application/x-download; charset=utf-8");
        //清除jsp页面缓存，用window open打开非jsp页面导出，有此项IE会报错
        if (clearCacheFlag) {
            response.setHeader("Cache-Control", "no-cache");//http 1.1
            response.setHeader("Pragma", "no-cache");//http 1.0
        }
        response.setDateHeader("Expires", 0);//http 1.0和1.1都支持
        outFileName = URLEncoder.encode(outFileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + outFileName + "\"");
    }

    /**
     * 导出文件(字符缓冲)
     *
     * @param out 输出流
     * @param path 文件路径
     * @param localFileName 本地磁盘文件名称
     * @deprecated pdf,zip有问题
     */
    public static void exportFileBisOut(OutputStream out, String path, String localFileName) {
        try {
            //把文件放到输入流中
            BufferedInputStream bfis = null;
            BufferedOutputStream bfos = null;
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;//读取字节长度
            try {
                bfis = new BufferedInputStream(new FileInputStream(path + localFileName));
                //输出流写到文件中
                bfos = new BufferedOutputStream(out);
                while ((len = bfis.read(buffer)) != -1) {
                    bfos.write(buffer, 0, len);//因为没有清空buffer，所以不能直接写整个数组
                }
            } catch (IOException ex) {
                logger.error(null, ex);
            } finally {
                try {
                    if (null != bfis) {
                        bfis.close();
                    }
                } catch (IOException e) {
                    logger.error(null, e);
                }
                try {
                    if (null != out) {
                        out.flush();
                    }
                } catch (IOException e) {
                    logger.error(null, e);
                }
                try {
                    if (null != out) {
                        out.close();
                    }
                } catch (IOException e) {
                    logger.error(null, e);
                }
            }
        } catch (Exception ex) {
            logger.error(null, ex);
        }
    }

    /**
     * 导出文件
     *
     * @param out 输出流
     * @param path 文件路径
     * @param localFileName 本地磁盘文件名称
     */
    public static void exportFileFisOut(OutputStream out, String path, String localFileName) {
        try {
            //把文件放到输入流中
            FileInputStream fis = null;
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;//读取字节长度
            try {
                fis = new FileInputStream(path + localFileName);
                //输出流写到文件中
                while ((len = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } catch (IOException ex) {
                logger.error(null, ex);
            } finally {
                try {
                    if (null != fis) {
                        fis.close();
                    }
                } catch (IOException e) {
                    logger.error(null, e);
                }
                try {
                    if (null != out) {
                        out.flush();
                    }
                } catch (IOException e) {
                    logger.error(null, e);
                }
                try {
                    if (null != out) {
                        out.close();
                    }
                } catch (IOException e) {
                    logger.error(null, e);
                }
            }
        } catch (Exception ex) {
            logger.error(null, ex);
        }
    }

    //删除文件
    public static void delFile(String filePath) {
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.watch.base.service.BaseService#saveFileForStruts2(java.io.File,
     *      java.lang.String)
     */
    //上传文件
    public static void saveFileForStruts2(File src, String fileName)
            throws Exception {
        InputStream in = null;
        OutputStream out = null;
        File dst = null;
        try {
            try {
                dst = new File(FILE_PATH + fileName);

                in = new BufferedInputStream(new FileInputStream(src),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst),
                        BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0) {
                    out.write(buffer);
                }

            } catch (Exception e) {
                throw new Exception("保存文件时出错!可能是因为上传的文件不存在，请用浏览的方式选择需要上传的文件。");
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.flush();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            throw new Exception("保存文件时出错!可能是因为上传的文件不存在，请用浏览的方式选择需要上传的文件。");
        }
    }

    //下载文件
    public static FileInputStream downLoadFile(String fileId) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(FILE_PATH + fileId);
            fileInputStream = new FileInputStream(FILE_PATH + fileId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("下载文件时失败！！");
        }
        return fileInputStream;
    }

    //得到文件大小
    public static String getFileSize(long length) {
        String reStr = "0 K";
        double size = (double) length;
        if (size > 1024) {
            size = size / SIZE;
            if (size > SIZE) {
                size = size / SIZE;
                if (size > SIZE) {
                    size = size / SIZE;
                    if (size > SIZE) {
                        size = size / SIZE;
                        reStr = getFileLength(size) + " T";
                    } else {
                        reStr = getFileLength(size) + " G";
                    }
                } else {
                    reStr = getFileLength(size) + " M";
                }
            } else {
                reStr = getFileLength(size) + " K";
            }
        } else {
            reStr = getFileLength(size) + " B";
        }
        return reStr;
    }

    /**
     * 得到web根目录
     *
     * @return web-inf所在的目录
     * @author	YuanYuan
     * @version v1.0 2008-12-10
     */
	public static String getBasePath() {
        String path = null;
        Class theClass = FileUtil.class;
        java.net.URL u = theClass.getResource("");
        //str会得到这个函数所在类的路径
        path = u.toString();
        //截去一些前面6个无用的字符
        path = path.substring(6, path.length());
        //将%20换成空格（如果文件夹的名称带有空格的话，会在取得的字符串上变成%20）
        path = path.replaceAll("%20", " ");
        //查找“WEB-INF”在该字符串的位置
        int num = path.indexOf("WEB-INF");
        //截取即可
        path = path.substring(0, num);
        return path;
    }

    /**
     * 获得web-inf下的配置文件对象
     *
     * @param fileName	配置文件名
     * @return	配置文件对象
     * @author	YuanYuan
     * @version v1.0 2009-5-6
     */
    public static Properties getProperties(String fileName) {
        String path = getBasePath() + "WEB-INF/" + fileName;
        Properties props = new Properties();
        try {
            //win操作系统不加/，Linux操作系统加/
            if (System.getProperty("os.name").toUpperCase().indexOf("WIN") != -1) {
                props.load(new FileInputStream(path));
            } else {
                props.load(new FileInputStream("/" + path));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
            props = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
            props = null;
        }
        return props;
    }

    public static void updateProperties(Properties props, String fileName, String decription) {
        String path = getBasePath() + "WEB-INF/" + fileName;
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            props.store(os, decription);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
            props = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
            props = null;
        }
    }

    private static String getFileLength(double size) {
        String reStr = size + "";
        int length = reStr.length();
        int subLength = reStr.indexOf(".");
        if (length - subLength - 1 <= DECIMAL_DIGITS) {//小数位小于等于DECIMAL_DIGITS时，直接返回，否则截取DECIMAL_DIGITS位
        } else {
            reStr = reStr.substring(0, subLength + DECIMAL_DIGITS);

        }
        return reStr;
    }

    public static boolean saveImg(String path, ByteBuffer imgData, String imgName) {
        String[] imgParam = imgName.split("\\.");
        boolean flag = false;
        FileOutputStream out = null;
        if (imgParam.length > 1) {
            try {
                String prefix = imgParam[0];
                String suffix = imgParam[1];
                File fileDir = new File(path);
                if (!fileDir.isDirectory()) {
                    fileDir.mkdirs();
                    logger.error(path + "不存在，创建文件夹成功！");
                }
                File file = new File(path + prefix + "." + suffix);
                out = new FileOutputStream(file);
                byte[] bytes = imgData.array();
//                System.err.println("===图片大小： "+bytes.length);
                for (int i = 0; i < bytes.length; i++) {
                    out.write(bytes[i]);
                }
                out.flush();
                flag = true;
            } catch (Exception e) {
                flag = false;
                logger.error(null, e);
            } finally {
                try {
                    out.close();
                    return flag;
                } catch (IOException e) {
                    logger.error(null, e);
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 图片数据转码
     *
     * @param ch
     * @return
     */
    private static int charToInt(byte ch) {
        int val = 0;
        if (ch >= 0x30 && ch <= 0x39) {
            val = ch - 0x30;
        } else if (ch >= 0x41 && ch <= 0x46) {
            val = ch - 0x41 + 10;
        }
        return val;
    }

    public static void createFolder(String dir) {
        File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * 生成压缩文件
     *
     * @param files
     * @return 返回压缩文件名称
     * @throws Exception
     */
//    public static String zipFiles(File[] files) throws Exception {
//        String zipFileName = "db_backup_" + CommUtils.convertDateToString(new Date(), "yyyy-MM-dd_HH-mm-ss") + ".zip";
//        File zipFile = new File(ConstantsCtr.BACKUP_PARAM.getBackupDir() + zipFileName);
//        BufferedInputStream bis = null;
//        ZipOutputStream out = null;
//        ZipEntry entry;
//        byte[] buffer = new byte[1024];
//        int len;
//        try {
//            //Zip输出流
//            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
//            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
//            out = new ZipOutputStream(cos);
//            for (File sqlFile : files) {
//                //添加下一个文件到压缩包
//                entry = new ZipEntry(sqlFile.getName());
//                out.putNextEntry(entry);
//                //文件输入流
//                bis = new BufferedInputStream(new FileInputStream(sqlFile));
//                while ((len = bis.read(buffer)) != -1) {
//                    out.write(buffer, 0, len);
//                }
//                try {
//                    bis.close();
//                } catch (IOException ex) {
//                    logger.error(null, ex);
//                }
//                out.closeEntry();
//            }
//        } catch (IOException ex) {
//            logger.error(null, ex);
//        } finally {
//            if (null != out) {
//                try {
//                    out.flush();
//                } catch (IOException ex) {
//                    logger.error(null, ex);
//                }
//            }
//            if (null != out) {
//                try {
//                    out.close();
//                } catch (IOException ex) {
//                    logger.error(null, ex);
//                }
//            }
//            return zipFileName;
//        }
//    }
}
