package com.gqlauth.security;

import java.util.List;

public interface Account {
	
	/**
     * Returns the id of this Account. The id field corresponds to the database
     * column securerest.securerest.account.id.
     * 
     * @return the id of this Account
     */
    public String getId();
    
    /**
     * Returns the username of this Account. The username field corresponds to
     * the database column securerest.securerest.account.username.
     * 
     * @return the username of this Account
     */
    public String getUsername();
    
    /**
     * Returns the password of this Account. The password field corresponds to
     * the database column securerest.securerest.account.password.
     * 
     * @return the password of this Account
     */
    public String getPassword();
    
    /**
     * Returns the role of this Account. The role field corresponds to the
     * database column securerest.securerest.account.role.
     * 
     * @return the role of this Account
     */
    public List<String> getRoles();
    
    /**
     * Returns the status of this account.  An inactive account should now be 
     * allowed to authenticate.
     * 
     * @return	the active status of this account
     */
    public boolean getActive();
    
    /**
     * Sets the id of this Account. The id field corresponds to the database
     * column securerest.securerest.account.id.
     * 
     * @param id to set of this Account
     * @return   this Account instance
     */
    public Account setId(String id);
    
    /**
     * Sets the username of this Account. The username field corresponds to the
     * database column securerest.securerest.account.username.
     * 
     * @param username to set of this Account
     * @return         this Account instance
     */
    public Account setUsername(String username);
    
    /**
     * Sets the password of this Account. The password field corresponds to the
     * database column securerest.securerest.account.password.
     * 
     * @param password to set of this Account
     * @return         this Account instance
     */
    public Account setPassword(String password);
    
    /**
     * Sets the role of this Account. The role field corresponds to the database
     * column securerest.securerest.account.role.
     * 
     * @param role to set of this Account
     * @return     this Account instance
     */
    public Account setRoles(String...roles);
}

