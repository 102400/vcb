package entity;

public class User {
	
	private int userId;
	private String userName;
	private String userEmail;
	private String userPasswordMd5;
	private String userNickname;
	private int following;
	private int followers;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPasswordMd5() {
		return userPasswordMd5;
	}
	public void setUserPasswordMd5(String userPasswordMd5) {
		this.userPasswordMd5 = userPasswordMd5;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public int getFollowing() {
		return following;
	}
	public void setFollowing(int following) {
		this.following = following;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}

}
