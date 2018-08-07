package com.alkatv.app.responses;

public class LoginResponse extends APIResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7493510873104292254L;
	
	private String userName; 
	private String userAlias;
	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
