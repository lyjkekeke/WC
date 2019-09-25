package wc;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//��������
public class r_data {

	
	public r_data(){
		
	}
	
	//�����ļ��ַ���
	public int r_charactor(File file) {
		int rData=0;
		Reader r_file = null;
		try {
			r_file = new InputStreamReader(new FileInputStream(file));
			while((r_file.read()) != -1) {
				if((char)r_file.read() != '\r' && (char)r_file.read() != '\n') {
					rData++;
				}
			}
			r_file.close();
			
		}
		catch(Exception e) {
			System.out.println("ָ�������ļ�������");
		}
		return rData;
	}
	
	//�����ļ�������
	public int r_word(File file) {
		int rData=0;
		BufferedReader r_file = null;
		String str;
		try {
			r_file = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((str = r_file.readLine()) != null) {
				
			    Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
		        Matcher matcher=pattern.matcher(str);
		        while(matcher.find()){
		        	rData++;
		        }
			}
			r_file.close();
			
		}
		catch(Exception e) {
			System.out.println("ָ�������ļ�������");
		}
		return rData;
	}
	
	//�����ļ�����
	public int r_line(File file) {
		int rData=0;
		BufferedReader r_file = null;
		try {
			r_file = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((r_file.readLine()) != null) {
			    rData++;
			}
			r_file.close();
			
		}
		catch(Exception e) {
			System.out.println("ָ�������ļ�������");
		}
		return rData;
	}
	
    
	
	//���ش�����/����/ע����
	public oData r_other(File file) {
		oData odata = new oData();
		BufferedReader r_file = null;
		boolean tag = false;
		int rLine = 0,rNode=0,rEmpty=0,rNote=0;
		try {
			r_file = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String str = null;
			while((str = r_file.readLine()) != null) {
			    rLine++;
			    str = str.replaceAll("\r\n"," ");
			    //����ע����ͳ��
			    if(tag == true) {
			    	++rNote;
			    	if(str.endsWith("*/")){
					    tag = false;//����ע���ڱ��н���
				    }
			    }
			    if(str.startsWith("/*") || str.startsWith("{/*")) {
			    	if(str.endsWith("*/") ) {
				    	++rNote;
				    }
			    	else {
			    		++rNote;
			    		tag = true;
			    	}
			    }
			    //�հ���ͳ��
			    if(str.matches("\\s*") || str.equals("{") || str.equals("}")){
			    	++rEmpty;
			    }
			  //����ע��ͳ��
			    if(str.startsWith("//") || str.startsWith("{//")) {
			    	++rNote;
			    }
			    rNode = rLine - rNote - rEmpty;
			}
			r_file.close();
		}
		catch(Exception e) {
			System.out.println("ָ�������ļ�������");
		}
		
		odata.rNote = rNote;
		odata.rNode = rNode;
		odata.rEmpty = rEmpty;
		return odata;
	}
	
	//�ݹ��ȡ�ļ�
	public void r_file(String path,String flag){
		
		String type = null;
		List<File> filelist;
		File file = new File(path);
		path = file.getAbsolutePath();
		file = new File(path);
		if(file.isDirectory()) {
			System.out.println("����Ҫ�����ĸ�ʽΪ������.txt����");
			//��ȡ�û��������Ϣ
			try {
			BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in));
			type = bufferedReader.readLine();
			bufferedReader.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			type = path.substring(path.lastIndexOf("."));
			path = file.getParent();
		}
		filelist = getFile.getFileList(path,type);
		if(flag == "n") {
			System.out.println("�����������ļ��У�");
			for(int i=0;i<filelist.size();i++) {
	        	String strFileName = filelist.get(i).getAbsolutePath();
	            System.out.println(i+1 + ". " + strFileName);
	        }
		} 
	    else if(flag == "a"){
			System.out.println("�����������ļ����������£�");
			for (int i=0;i<filelist.size();i++) {
                String strFileName = filelist.get(i).getAbsolutePath();
                System.out.println(i+1 + " " + strFileName);
                r_data rd = new r_data();
                System.out.println("�ַ�����"+rd.r_charactor(filelist.get(i)));
        		System.out.println("��������"+rd.r_word(filelist.get(i)));
        		System.out.println("������"+rd.r_line(filelist.get(i)));
        		oData odata = new oData();
				odata  = rd.r_other(file);
				System.out.println("���ļ��к��У�");
				System.out.println("�����У�" + odata.rNode);
				System.out.println("�հ��У�" + odata.rEmpty);
				System.out.println("ע���У�" + odata.rNote);
            }
		}
	}
}
class oData{
	int rNode;
	int rEmpty;
	int rNote;
}