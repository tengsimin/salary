package gcloud.email.Util;

public class SubStringUserId {
	public static String getSubStringUserId(String userId){
		int userIdlength=userId.length();
		return userId.substring(userIdlength-6, userIdlength);
	}
}
