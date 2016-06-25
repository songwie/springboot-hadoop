package com.mogoroom.hadoop.service.dataTrans;

import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

public class HDFSUtil {
    private static Logger log = Logger.getLogger(HDFSUtil.class);
    
    private synchronized static FileSystem getFileSystem(Configuration config) {
        FileSystem fs = null;
        try {
            fs = FileSystem.get(config);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return fs;
    }
    /**
     * 删除目录和子目录
     *
     * @param fs
     * @param dirName
     */
    public synchronized static void rmdirs(Configuration config, String dirName) {
        Path src = new Path(dirName);
        boolean succ;
        try {
            succ = getFileSystem(config).delete(src, true);
            if (succ) {
                log.info("remove directory " + dirName + " successed. ");
            } else {
                log.info("remove directory " + dirName + " failed. ");
            }
        } catch (Exception e) {
            log.error("remove directory " + dirName + " failed :" );
        }
    }
    /**
     * 遍历HDFS上的文件和目录
     *
     * @param fs
     * @param path
     */
	public synchronized static List<String> listFile(Configuration config, String path) {
    	List<String> files = new LinkedList<>();
        Path dst = new Path(config.get("fs.defaultFS") + path);
        try {
            FileStatus[] fList = getFileSystem(config).listStatus(dst);
            for (FileStatus f : fList) {
                if (f!=null && f.isFile()) {
                	files.add(path + "/" + f.getPath().getName());
                }
            }
        } catch (Exception e) { 
        	e.printStackTrace();
        } 
        
        return files;
    }
}
