package com.alkatv.app.responses;

import java.util.ArrayList;
import java.util.List;



public class SearchResponse extends APIResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7493510873104292254L;

	private List<User> userList = new ArrayList<>();

	public List<User> getUserList() {
		return userList;
	}

	public void addUser(User user) {
		getUserList().add(user);
	}

	public void addUserList(List<User> userList) {
		if (userList!=null && !userList.isEmpty()) {
			getUserList().addAll(userList);
		}
	}

}
