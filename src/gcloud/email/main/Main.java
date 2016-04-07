package gcloud.email.main;

import gcloud.email.Util.GetUserEmail;
import gcloud.email.Util.ReadExcelUtil;
import gcloud.email.entity.UserEmail;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 客户端 界面
 * 
 * @author Administrator
 * 
 */
public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	private JTextField textField_2_2;
	private JTextField textField_1;
	private JProgressBar aJProgressBar;
	File file;
	File fileCSV;

	public Main() {
		setTitle("上传客户端");
		getContentPane().setLayout(null);
		this.setResizable(false);
		JLabel lblNewLabel_2 = new JLabel("请上传最新员工信息的Excel文件");
		JLabel lblNewLabel_2_2 = new JLabel("请输入发放工资的月份");
		JLabel lblNewLabel_1 = new JLabel("请上传当月工资清单文件");
		final JLabel lblNewLabelerror = new JLabel();
		final JLabel lblNewLabel2error = new JLabel();
		lblNewLabel_2.setBounds(37, 24, 231, 15);
		lblNewLabel_1.setBounds(37, 86, 231, 15);
		lblNewLabel_2_2.setBounds(37, 150, 231, 15);
		lblNewLabelerror.setBounds(110, 58, 200, 20);
		lblNewLabel2error.setBounds(110, 122, 200, 20);
		lblNewLabel_2.setFont(new Font("Dialog", 1, 13));
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2_2.setFont(new Font("Dialog", 1, 13));
		lblNewLabel_2_2.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Dialog", 1, 13));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabelerror.setFont(new Font("Dialog", 0, 10));
		lblNewLabelerror.setForeground(Color.RED);
		lblNewLabel2error.setFont(new Font("Dialog", 0, 10));
		lblNewLabel2error.setForeground(Color.RED);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(lblNewLabel_2_2);
		getContentPane().add(lblNewLabelerror);
		getContentPane().add(lblNewLabel2error);
		textField_2 = new JTextField();
		textField_2_2 = new JTextField();
		textField_1 = new JTextField();
		aJProgressBar = new JProgressBar();
		aJProgressBar.setBounds(40, 200, 300, 35);
		aJProgressBar.setStringPainted(true); // 显示百分比字符
		aJProgressBar.setIndeterminate(false); // 不确定的进度条
		textField_2.setBounds(37, 105, 231, 21);
		textField_2_2.setBounds(100, 170, 100, 21);
		textField_1.setBounds(37, 40, 231, 21);

		getContentPane().add(textField_2);
		getContentPane().add(textField_2_2);
		getContentPane().add(textField_1);
		getContentPane().add(aJProgressBar);
		textField_2.setColumns(10);
		textField_2_2.setColumns(10);
		SimpleDateFormat format = new SimpleDateFormat("MM");

		String month = format.format(new Date());
		if (month.startsWith("0")) {
			month = month.substring(1);
		}
		textField_2_2.setText(month);
		textField_1.setColumns(10);
		/**
		 * 选择
		 */
		JButton button_1 = new JButton("浏览");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jchoose = new JFileChooser();
				if (jchoose.showDialog(null, "确定") == 1) {
					return;
				}
				textField_2.setText(jchoose.getSelectedFile().toString());
				textField_2.setEnabled(false);
				file = new File(textField_2.getText());
				if (textField_2.getText() != "") {
					lblNewLabel2error.setText("");
				} else {
					lblNewLabel2error.setText("Excel文件不能为空");
				}

			}
		});
		button_1.setBounds(301, 104, 93, 23);
		getContentPane().add(button_1);

		JButton button_CSV = new JButton("浏览");
		button_CSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jchoose = new JFileChooser();
				// jchoose.showDialog(null, "确定");
				if (jchoose.showDialog(null, "确定") == 1) {
					return;
				}
				// file = jchoose.getSelectedFile();
				textField_1.setText(jchoose.getSelectedFile().toString());
				textField_1.setEnabled(false);
				fileCSV = new File(textField_1.getText());
				if (textField_2.getText() != "") {
					lblNewLabelerror.setText("");
				} else {
					lblNewLabelerror.setText("文件不能为空");
				}
			}
		});
		button_CSV.setBounds(301, 40, 93, 23);
		getContentPane().add(button_CSV);
		/**
		 * 发送
		 */
		JButton button_2 = new JButton("执行");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ExcelName = textField_2.getText();
					String CSVName = textField_1.getText();
					String igon = "";
					if (textField_2_2.getText() != "") {
						igon = textField_2_2.getText();
					}
					if (ExcelName.equals("")) {
						lblNewLabel2error.setText("Excel文件不能为空");
					}
					if (CSVName.equals("")) {
						lblNewLabelerror.setText("文件不能为空");
					}
					if (!ExcelName.equals("") && !CSVName.equals("")) {
						List<UserEmail> list = GetUserEmail.GetAllEmailFromCSV(CSVName);
						InputStream ifs = new FileInputStream(ExcelName);
						XSSFWorkbook wb = new XSSFWorkbook(ifs);
						XSSFSheet sheet = wb.getSheetAt(0);
						int rowNum = sheet.getLastRowNum();
						Thread stepper = new BarThread(aJProgressBar, (rowNum - 1) * 22);
						Thread stepperupload = new UploadThread(list, ExcelName, igon);
						stepperupload.start();
						stepper.start();
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(301, 167, 93, 23);
		getContentPane().add(button_2);
		setBounds(490, 290, 440, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		System.out.println("dddddddddddddd");
		final WelcomeWindow welcomeWindow = new WelcomeWindow();
		welcomeWindow.setVisible(true);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				welcomeWindow.setVisible(false);
				new Main();
				timer.cancel();
			}
		}, 1500);
	}

	/**
	 * 进度条线程
	 * */
	static class BarThread extends Thread {
		private int DELAY = 100;
		JProgressBar progressBar;

		public BarThread(JProgressBar bar, int DELAY) {
			progressBar = bar;
			this.DELAY = DELAY;
		}

		public void run() {
			int minimum = progressBar.getMinimum();
			int maximum = progressBar.getMaximum();
			Runnable runner = new Runnable() {
				public void run() {
					int value = progressBar.getValue();
					progressBar.setValue(value + 1);
					if (Integer.parseInt(progressBar.getValue() + "") == 100) {
						JOptionPane.showMessageDialog(null, "发送成功");
					}
				}
			};
			for (int i = minimum; i < maximum; i++) {
				try {
					SwingUtilities.invokeAndWait(runner);
					Thread.sleep(DELAY);
				} catch (InterruptedException ignoredException) {
				} catch (InvocationTargetException ignoredException) {
				}
			}
		}
	}

	/***/
	static class UploadThread extends Thread {
		List<UserEmail> list;
		String ExcelName;
		String tableName;
		String igon;

		public UploadThread(List<UserEmail> list, String ExcelName, String igon) {
			this.list = list;
			this.ExcelName = ExcelName;
			this.igon = igon;

		}

		public void run() {
			ReadExcelUtil.writeFile(list, ExcelName, igon);
		}
	}
}
