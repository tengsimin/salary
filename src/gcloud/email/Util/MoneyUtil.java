package gcloud.email.Util;

public class MoneyUtil {
	public static String getMoney(String money){
		int index=money.indexOf(".");
		System.out.println(index);
		String indexString="";
		String indexString2="";
		if(index>0){
			indexString=money.substring(0, index);
			indexString2=money.substring(index,money.length());
			money=indexString;
		}
		System.out.println(indexString);
		System.out.println(indexString2);
		char a[]=money.toCharArray();
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<a.length;i++){
			if((a.length-1-i)%3==0&&((a.length-1-i!=0))&&!((i==(a.length-1))&&(a.length%3==1))){
				buffer.append(a[i]+",");
			}else if((i==(a.length-1))&&(a.length%3==1)){
				buffer.append(a[i]);
			}else{
				buffer.append(a[i]);
			}
		}
		if(indexString2.length()==0){
			buffer.append(".00");
		}else if(indexString2.length()==2){
			buffer.append(indexString2+"0");
		}else if(indexString2.length()>3){
			buffer.append(indexString2.substring(0, 3));
		}else{
			buffer.append(indexString2);
		}
		return buffer.toString();
	}
public static void main(String[] args) {
		System.out.println(getMoney("11131222335.883"));
	}
}
