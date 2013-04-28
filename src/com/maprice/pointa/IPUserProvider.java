package com.maprice.pointa;

public interface IPUserProvider extends IPProvider {

	public void login(String pUsername, String pPassword);
	
	public void loginWithFacebook(String pUsername);
	
	public void unlinkWithFacebook();
	public void linkWithFacebook();
	
	
	public void create(String pUsername, String pPassword);
	public void create(String pUsername, String pPassword, String pEmail);
	
	
	
	public void userLogout();
	
	public void setEmail(String pEmail);
	public void setUsername(String pUsername);
	public void resetPassword();
	
}
