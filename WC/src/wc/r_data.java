package wc;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//返回数据
public class r_data {

	
	public r_data(){
		
	}
	
	//返回文件字符数
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
			System.out.println("指定输入文件不存在");
		}
		return rData;
	}
	
	//返回文件单词数
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
			System.out.println("指定输入文件不存在");
		}
		return rData;
	}
	
	//返回文件行数
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
			System.out.println("指定输入文件不存在");
		}
		return rData;
	}
	
    
	
	//返回代码行/空行/注释行
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
			    //多行注释中统计
			    if(tag == true) {
			    	++rNote;
			    	if(str.endsWith("*/")){
					    tag = false;//多行注释在本行结束
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
			    //空白行统计
			    if(str.matches("\\s*") || str.equals("{") || str.equals("}")){
			    	++rEmpty;
			    }
			  //单行注释统计
			    if(str.startsWith("//") || str.startsWith("{//")) {
			    	++rNote;
			    }
			    rNode = rLine - rNote - rEmpty;
			}
			r_file.close();
		}
		catch(Exception e) {
			System.out.println("指定输入文件不存在");
		}
		
		odata.rNote = rNote;
		odata.rNode = rNode;
		odata.rEmpty = rEmpty;
		return odata;
	}
	
	//递归获取文件
	public void r_file(String path,String flag){
		
		String type = null;
		List<File> filelist;
		File file = new File(path);
		path = file.getAbsolutePath();
		file = new File(path);
		if(file.isDirectory()) {
			System.out.println("您想要搜索的格式为（例：.txt）：");
			//获取用户输入的信息
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
			System.out.println("符合条件的文件有：");
			for(int i=0;i<filelist.size();i++) {
	        	String strFileName = filelist.get(i).getAbsolutePath();
	            System.out.println(i+1 + ". " + strFileName);
	        }
		} 
	    else if(flag == "a"){
			System.out.println("符合条件的文件及其结果如下：");
			for (int i=0;i<filelist.size();i++) {
                String strFileName = filelist.get(i).getAbsolutePath();
                System.out.println(i+1 + " " + strFileName);
                r_data rd = new r_data();
                System.out.println("字符数："+rd.r_charactor(filelist.get(i)));
        		System.out.println("单词数："+rd.r_word(filelist.get(i)));
        		System.out.println("行数："+rd.r_line(filelist.get(i)));
        		oData odata = new oData();
				odata  = rd.r_other(file);
				System.out.println("该文件中含有：");
				System.out.println("代码行：" + odata.rNode);
				System.out.println("空白行：" + odata.rEmpty);
				System.out.println("注释行：" + odata.rNote);
            }
		}
	}
}
class oData{
	int rNode;
	int rEmpty;
	int rNote;
}