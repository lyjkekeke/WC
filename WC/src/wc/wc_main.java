package wc;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;

// 程序处理用户需求的模式为：wc.exe [parameter] [file_name]

public class wc_main {

	private static String judgeData = null,judgeData1 = null,judgeData2 = null;
	private static String jdg;
	private static String fileName;
	static String ipt1 = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("程序处理用户需求的模式为：wc.exe [parameter] [file_name](eg:wc.exe -c wcTest.txt)");
		System.out.println("操作文档：");
		System.out.println("-c 返回文件的字符数");
		System.out.println("-w 返回文件的单词数");
		System.out.println("-l 返回文件的行数");
		System.out.println("-a 返回文件的代码行/空行/注释行数");
		System.out.println("-s 递归处理目录下符合条件的文返回文件的字符数");
		System.out.println("支持通配符*");
		System.out.println(" ");
		System.out.println("请输入操作内容：");
		//获取用户输入的信息
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		
		//进行相应的数据获取并输出
				r_data rd = new r_data();
				jdg = judge(input);//判断接下来进行什么操作
		
		//选择文件
		if(jdg == "x") {
			 selectFile sf = new selectFile();
			 sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 sf.pack();
			 sf.setVisible(true);
		}
		else{
			fileName = input.substring(10);
			File file = new File(fileName);
			//if(fileName.startsWith("*")) jdg = "n";
			switch(jdg) {
			
			  case "c":
				  System.out.println(rd.r_charactor(file));
				  break;
			  case "w":
				  System.out.println(rd.r_word(file));
				  break;
			  case "l":
				  System.out.println(rd.r_line(file));
				  break;
			  case "a":
				  oData odata = new oData();
				  odata  = rd.r_other(file);
				  System.out.println("该文件中含有：");
				  System.out.println("代码行：" + odata.rNode);
				  System.out.println("空白行：" + odata.rEmpty);
				  System.out.println("注释行：" + odata.rNote);
				  break;
			  case "s":
				  judgeData1 = "n";
				  //wc.exe -s -a wcTest.txt
				  judgeData2 = input.substring(10);
				  rd.r_file(judgeData2,judgeData1);
				  break;
			  case "n":
				  judgeData1 = "a";
				  judgeData2 = input.substring(13);
				  rd.r_file(judgeData2,judgeData1);
				  break;
		      default:
				  System.out.println("用户输入格式不正确");
				  break;
			}
		}
		sc.close();
	}

	//判断用户输入的信息
	public static String judge(String input) {
		
		String ipt = null;
		if(input.substring(0,6).equals("wc.exe")) {
			if(input.substring(7,9).equals("-c")||input.substring(7,9).equals("-w")
					||input.substring(7,9).equals("-l")||input.substring(7,9).equals("-a")) {
				if(fnameJudge(input.substring(10))) {
					ipt = input.substring(7,9);
				}
			}
			else if(input.substring(7,9).equals("-s")) {
				if(input.substring(10,12).equals("-a") && fnameJudge(input.substring(13))) {
				    ipt = "n";
				}
				else if(fnameJudge(input.substring(10))) {
					ipt = input.substring(7,9);
				}
			}
			else if(input.substring(7,9).equals("-x")) {
				ipt = input.substring(7,9);
			}
		}
		if(ipt.equals("-c")) judgeData = "c";
		else if(ipt.equals("-w")) judgeData = "w";
		else if(ipt.equals("-l")) judgeData = "l";
		else if(ipt.equals("-s")) judgeData = "s";
		else if(ipt.equals("-a")) judgeData = "a";
		else if(ipt.equals("-x")) judgeData = "x";
		else if(ipt.equals("n")) judgeData = "n";
		return judgeData;
	}
	
	//判断输入的是否是路径
	private static boolean fnameJudge(String filename){
        
		boolean existFlag = false;
        File file = new File(filename);
        if(file.exists()){
        	 existFlag = true;
        }
        else if(filename.startsWith("*")) {
        	existFlag = true;
        }
        else if(filename.startsWith("?")) {
        	existFlag = true;
        }
        else{
            System.out.println("用户输入路径错误或文件不存在");
        }    
        return existFlag;
    }
}
