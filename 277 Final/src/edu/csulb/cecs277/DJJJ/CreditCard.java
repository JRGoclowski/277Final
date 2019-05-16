package edu.csulb.cecs277.DJJJ;

public class CreditCard {
	
	private String mName;
	private String mNumber;
	private String mSecurityCode;
	private String mExpiration;
	
	
	/**
	 * Constructor
	 * @param mName - the name
	 * @param mNumber - the card number
	 * @param mSecurityCode - the code
	 * @param mExpiration - the expiration date
	 */
	public CreditCard(String mName, String mNumber, String mSecurityCode, String mExpiration) {
		this.mName = mName;
		this.mNumber = mNumber;
		this.mSecurityCode = mSecurityCode;
		this.mExpiration = mExpiration;
	}
	
	/**
	 * Default Constructor
	 */
	public CreditCard() {
		this.mName = "";
		this.mNumber = "";
		this.mSecurityCode = "";
		this.mExpiration = "";
	}
	
	/**
	 * @return the mName
	 */
	public String getmName() {
		return mName;
	}
	/**
	 * @param mName the mName to set
	 */
	public void setmName(String mName) {
		this.mName = mName;
	}
	/**
	 * @return the mNumber
	 */
	public String getmNumber() {
		return mNumber;
	}
	/**
	 * @param mNumber the mNumber to set
	 */
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	/**
	 * @return the mSecurityCode
	 */
	public String getmSecurityCode() {
		return mSecurityCode;
	}
	/**
	 * @param mSecurityCode the mSecurityCode to set
	 */
	public void setmSecurityCode(String mSecurityCode) {
		this.mSecurityCode = mSecurityCode;
	}
	/**
	 * @return the mExpiration
	 */
	public String getmExpiration() {
		return mExpiration;
	}
	/**
	 * @param mExpiration the mExpiration to set
	 */
	public void setmExpiration(String mExpiration) {
		this.mExpiration = mExpiration;
	}
	
}
