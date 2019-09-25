package wc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 

public class getFile {

	static List<File> filelist = new ArrayList<>();
	public static List<File> getFileList(String filePath,String type) {
		
        File file = new File(filePath);
        File[] files = file.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath(),type); // 获取文件绝对路径
                } 
                if (fileName.endsWith(type)) { // 判断文件名是否是用户想要的格式
                    filelist.add(files[i]);
                } 
            }

        }
        return filelist;
 
    }
	
}

