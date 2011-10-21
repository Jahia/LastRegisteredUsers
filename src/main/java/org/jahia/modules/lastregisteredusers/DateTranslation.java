package org.jahia.modules.lastregisteredusers;

/**
 * The Class DateTranslation is in charge of
 * the creation of the JCR query constraint.
 * 
 * @author Juan Pablo Albuja
 */
public class DateTranslation {
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The jcr constraint. */
	private String jcrConstraint;

	/**
	 * Gets the jcr constraint.
	 *
	 * @return the jcr constraint
	 */
	public String getJcrConstraint() {
		setJcrConstraint("");
		if(!startDate.equals("") && !endDate.equals("")){
			jcrConstraint += "where u.[jcr:created] >= " + "'" + startDate + "' AND ";
			jcrConstraint += "u.[jcr:created] <= " + "'" + endDate + "'";
		} else if (!startDate.equals("") && endDate.equals("")){
			jcrConstraint += "where u.[jcr:created] >= " + "'" + startDate + "'";
		}else if (startDate.equals("") && !endDate.equals("")){
			jcrConstraint += "where u.[jcr:created] <= " + "'" + endDate + "'";
		}else{
			jcrConstraint = "";
		}
		return jcrConstraint;
	}

	/**
	 * Sets the jcr constraint.
	 *
	 * @param jcrConstraint the new jcr constraint clause
	 * built based on startDate and EndDate
	 */
	public void setJcrConstraint(String jcrConstraint) {
		this.jcrConstraint = jcrConstraint;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



}
