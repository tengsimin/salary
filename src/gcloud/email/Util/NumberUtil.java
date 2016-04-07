package gcloud.email.Util;
import java.util.Random;

/**产生不重复的随计数*/
public class NumberUtil {
	public static String GetNumber(){
		Random r=new Random();
		int x=r.nextInt(1000000000)+100000000;
		char[] str=new char[4];
		for(int i=0;i<str.length;i++){
			str[i]=(char) (r.nextInt(31)+65);
		}
		String str1=new String(str);
		return str1+x;
	}
	public static void main(String[] args) {
		System.out.println(GetNumber());
	}
}
