package org.jahia.modules.lastregisteredusers;

import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import org.jahia.services.usermanager.JahiaUser;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.services.usermanager.UserProperties;
import org.slf4j.Logger;


/**
 * The Class LastRegisteredUsers represents a Class that returns an String formatted as html with
 * the information of the Last Registered users sorted by creation date on descending order.
 * 
 * @author Juan Pablo Albuja
 */
public class LastRegisteredUsers {
	
	/** The logger. */
	private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(LastRegisteredUsers.class);
	
	/** The user manager service. Instantiated in the constructor of this class*/
	private JahiaUserManagerService userManagerService;
	
	/** The number users. */
	private int numberUsers;
	
	/** The user names. */
	private String userNames;
	
	/**
	 * Instantiates a new last registered users sync.
	 */
	public LastRegisteredUsers(){
		userNames = "";
		userManagerService = ((JahiaUserManagerService) org.jahia.services.SpringContextSingleton.getBean("JahiaUserManagerService"));
	}

	/**
	 * Gets the number users.
	 * 
	 * @return the number users
	 */
	public int getNumberUsers() {
		return numberUsers;
	}

	/**
	 * Sets the number users.
	 * 
	 * @param numberUsers the new number users
	 */
	public void setNumberUsers(int numberUsers) {
		this.numberUsers = numberUsers;
	}

	/**
	 * Gets the user names.
	 * 
	 * @return the user names
	 */
	public String getUserNames() {
		List <String> userList = userManagerService.getUserList();
		Iterator<String> iterator = userList.iterator();
		//Create the sorted map by sorted by creation date (ascending)
		TreeMap<String, String> sortedMap = new TreeMap<String,String>();
		while ( iterator.hasNext() ){
			String key = iterator.next();
			JahiaUser currentUser = userManagerService.lookupUserByKey(key);
			//Get User properties
			UserProperties userproperties = currentUser.getUserProperties();
			String dateProperty = userproperties.getProperty("jcr:created");
			String userName = currentUser.getUsername();
			//Put on the ordered Map
			sortedMap.put(dateProperty, userName);
		}
		int counter = 0;
		NavigableSet<String> mapKeys =  sortedMap.descendingKeySet();
		Iterator<String> iter = mapKeys.iterator();
		//Fill the JSON Object
		while (iter.hasNext()){
			if(numberUsers > counter){
				String key = iter.next();
				userNames += sortedMap.get(key) + "<br/>";
			}else{
				break;
			}
			counter++;
		}			
		return userNames;
	}

	/**
	 * Sets the user names.
	 * 
	 * @param userNames the new user names
	 */
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
}
