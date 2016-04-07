package gcloud.email.Util;

import gcloud.email.entity.UserEmail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class ReadCSVUtil {

	public static List<UserEmail> GetAllEmailFromCSV(String CSVFIleName) {
		List<UserEmail>list=new ArrayList<UserEmail>();
		try {
			File csv = new File(CSVFIleName); // CSV文件
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(csv),"gbk")); 
			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				// 把一行数据分割成多个字段
				StringTokenizer st = new StringTokenizer(line, ",");
				int i=0;
				UserEmail e=new UserEmail();
				while (st.hasMoreTokens()) {
					// 每一行的多个字段用TAB隔开表示
					if(i==0){
						e.setName(st.nextToken());
					}else if (i==1){
						e.setEmail(st.nextToken());
					}else{
						e.setFoxaddrID(st.nextToken());
					}
					i++;
				}
				list.add(e);
			}
			System.out.println(list.size());
			System.out.println(list.toString());
			br.close();

		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获BufferedReader对象关闭时的异常
			e.printStackTrace();
		}
		return list;
	}
}
