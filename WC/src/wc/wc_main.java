package wc;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;

// �������û������ģʽΪ��wc.exe [parameter] [file_name]

public class wc_main {

	private static String judgeData = null,judgeData1 = null,judgeData2 = null;
	private static String jdg;
	private static String fileName;
	static String ipt1 = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("�������û������ģʽΪ��wc.exe [parameter] [file_name](eg:wc.exe -c wcTest.txt)");
		System.out.println("�����ĵ���");
		System.out.println("-c �����ļ����ַ���");
		System.out.println("-w �����ļ��ĵ�����");
		System.out.println("-l �����ļ�������");
		System.out.println("-a �����ļ��Ĵ�����/����/ע������");
		System.out.println("-s �ݹ鴦��Ŀ¼�·����������ķ����ļ����ַ���");
		System.out.println("֧��ͨ���*");
		System.out.println(" ");
		System.out.println("������������ݣ�");
		//��ȡ�û��������Ϣ
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		
		//������Ӧ�����ݻ�ȡ�����
				r_data rd = new r_data();
				jdg = judge(input);//�жϽ���������ʲô����
		
		//ѡ���ļ�
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
				  System.out.println("���ļ��к��У�");
				  System.out.println("�����У�" + odata.rNode);
				  System.out.println("�հ��У�" + odata.rEmpty);
				  System.out.println("ע���У�" + odata.rNote);
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
				  System.out.println("�û������ʽ����ȷ");
				  break;
			}
		}
		sc.close();
	}

	//�ж��û��������Ϣ
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
	
	//�ж�������Ƿ���·��
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
            System.out.println("�û�����·��������ļ�������");
        }    
        return existFlag;
    }
}
