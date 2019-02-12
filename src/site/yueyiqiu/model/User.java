package site.yueyiqiu.model;

import java.util.Calendar;

public class User {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String userSex;
	private int userAge;
	private Calendar userLogintime;
	
	
	
	public User() {
		
	}



	public User(String usetId, String userPwd, String userName, String userSex,
			int userAge, Calendar userLogintime) {
		super();
		this.userId = usetId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userLogintime = userLogintime;
	}



	/**
	 * @return the usetId
	 */
	public String getUserId() {
		return userId;
	}



	/**
	 * @param usetId the usetId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}



	/**
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}



	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	/**
	 * @return the userSex
	 */
	public String getUserSex() {
		return userSex;
	}



	/**
	 * @param userSex the userSex to set
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}



	/**
	 * @return the userAge
	 */
	public int getUserAge() {
		return userAge;
	}



	/**
	 * @param userAge the userAge to set
	 */
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}



	/**
	 * @return the userLogintime
	 */
	public Calendar getUserLogintime() {
		return userLogintime;
	}



	/**
	 * @param userLogintime the userLogintime to set
	 */
	public void setUserLogintime(Calendar userLogintime) {
		this.userLogintime = userLogintime;
	}
	
	
	
	
}
