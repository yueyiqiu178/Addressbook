package site.yueyiqiu.model;

public class PersonInfo {
	
	private int pId;
	private String pName;
	private String pSex;
	private int pAge;
	private String pMobile;
	private String pAddress;
	private String pEmail;
	private User user;
	private PersonType type;
	
	
	
	public PersonInfo() {
		
	}



	


	public PersonInfo(int pId, String pName, String pSex, int pAge,
			String pMobile, String pAddress, String pEmail, User user,
			PersonType type) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pSex = pSex;
		this.pAge = pAge;
		this.pMobile = pMobile;
		this.pAddress = pAddress;
		this.pEmail = pEmail;
		this.user = user;
		this.type = type;
	}






	/**
	 * @return the pId
	 */
	public int getpId() {
		return pId;
	}



	/**
	 * @param pId the pId to set
	 */
	public void setpId(int pId) {
		this.pId = pId;
	}



	/**
	 * @return the pName
	 */
	public String getpName() {
		return pName;
	}



	/**
	 * @param pName the pName to set
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}



	/**
	 * @return the pSex
	 */
	public String getpSex() {
		return pSex;
	}



	/**
	 * @param pSex the pSex to set
	 */
	public void setpSex(String pSex) {
		this.pSex = pSex;
	}



	/**
	 * @return the pAge
	 */
	public int getpAge() {
		return pAge;
	}



	/**
	 * @param pAge the pAge to set
	 */
	public void setpAge(int pAge) {
		this.pAge = pAge;
	}



	/**
	 * @return the pMobile
	 */
	public String getpMobile() {
		return pMobile;
	}



	/**
	 * @param pMobile the pMobile to set
	 */
	public void setpMobile(String pMobile) {
		this.pMobile = pMobile;
	}



	/**
	 * @return the pAddress
	 */
	public String getpAddress() {
		return pAddress;
	}



	/**
	 * @param pAddress the pAddress to set
	 */
	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}



	/**
	 * @return the pEmail
	 */
	public String getpEmail() {
		return pEmail;
	}



	/**
	 * @param pEmail the pEmail to set
	 */
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}






	public User getUser() {
		return user;
	}






	public void setUser(User user) {
		this.user = user;
	}






	public PersonType getType() {
		return type;
	}






	public void setType(PersonType type) {
		this.type = type;
	}


}
