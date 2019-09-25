package wc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 

public class getFile {

	static List<File> filelist = new ArrayList<>();
	public static List<File> getFileList(String filePath,String type) {
		
        File file = new File(filePath);
        File[] files = file.listFiles(); // ���ļ�Ŀ¼���ļ�ȫ����������
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // �ж����ļ������ļ���
                    getFileList(files[i].getAbsolutePath(),type); // ��ȡ�ļ�����·��
                } 
                if (fileName.endsWith(type)) { // �ж��ļ����Ƿ����û���Ҫ�ĸ�ʽ
                    filelist.add(files[i]);
                } 
            }

        }
        return filelist;
 
    }
	
}

