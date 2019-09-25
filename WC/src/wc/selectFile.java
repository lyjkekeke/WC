package wc;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class selectFile extends JFrame implements ActionListener{
 
    private static final long serialVersionUID = 1L;
 
    JButton btn1 = null;
    JButton btn2 = null;
 
    JTextField textField = null;
    JTextArea  textArea = null;
    
    public selectFile()
    {
        this.setTitle("选择文件");
        this.setSize(300,100); 
        FlowLayout layout = new FlowLayout();
        JLabel label = new JLabel("请选择文件：");
        textField = new JTextField(30);
        textArea = new JTextArea(30,30);
        textArea.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(textArea);
        btn1 = new JButton("选择");
 
        // 设置布局
        layout.setAlignment(FlowLayout.LEFT);// 左对齐
        this.setLayout(layout);
        this.setBounds(400, 400, 600, 100);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn1.addActionListener(this);
        add(label);
        add(textField);
        add(btn1);
        add(textArea);
 
    }
 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showDialog(new JLabel(), "选择");
        File file = chooser.getSelectedFile();
        textField.setText(file.getAbsoluteFile().toString());
        r_data rd = new r_data();
        oData odata = new oData();
		odata  = rd.r_other(file);
		textArea.append("该文件中含有：" + "\r\n");
		textArea.append("代码行：" + odata.rNode + "\r\n");
		textArea.append("空白行：" + odata.rEmpty + "\r\n");
		textArea.append("注释行：" + odata.rNote + "\r\n");
        textArea.append("字符数："+rd.r_charactor(file) + "\r\n");
        textArea.append("单词数："+rd.r_word(file) + "\r\n");
        textArea.append("行数："+rd.r_line(file) + "\r\n");
    }
}

