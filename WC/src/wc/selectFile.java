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
        this.setTitle("ѡ���ļ�");
        this.setSize(300,100); 
        FlowLayout layout = new FlowLayout();
        JLabel label = new JLabel("��ѡ���ļ���");
        textField = new JTextField(30);
        textArea = new JTextArea(30,30);
        textArea.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(textArea);
        btn1 = new JButton("ѡ��");
 
        // ���ò���
        layout.setAlignment(FlowLayout.LEFT);// �����
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
        chooser.showDialog(new JLabel(), "ѡ��");
        File file = chooser.getSelectedFile();
        textField.setText(file.getAbsoluteFile().toString());
        r_data rd = new r_data();
        oData odata = new oData();
		odata  = rd.r_other(file);
		textArea.append("���ļ��к��У�" + "\r\n");
		textArea.append("�����У�" + odata.rNode + "\r\n");
		textArea.append("�հ��У�" + odata.rEmpty + "\r\n");
		textArea.append("ע���У�" + odata.rNote + "\r\n");
        textArea.append("�ַ�����"+rd.r_charactor(file) + "\r\n");
        textArea.append("��������"+rd.r_word(file) + "\r\n");
        textArea.append("������"+rd.r_line(file) + "\r\n");
    }
}

