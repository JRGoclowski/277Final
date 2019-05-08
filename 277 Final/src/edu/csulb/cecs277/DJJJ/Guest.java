package edu.csulb.cecs277.DJJJ;

public class Guest {
	
	public static final Guest JOESEPH_JOESTAR = new Guest("1", "OHMYGAWD@hamon.com", "Joeseph Joestar",
			"Hermit... Purple", "Your next line is I need a credit card");
	public static final Guest DIO_BRANDO = new Guest("2", "BUTITWASME@DIO.com", "Dio Brando", 
			"ZA WARUDO", "I REJECT MY CREDIT CARD JOJO");
	public static final Guest JOTARO_KUJO = new Guest("3", "YareYare@Daze.com", "Jotaro Kujo", 
			"Japan", "Star Platinum Members Card");
	
	private String mPhone, mEmail, mName, mAddress, mCardInfo;
	
	public Guest (String phone, String email, String name, String address, String card) {
		mPhone = phone;
		mEmail = email;
		mName = name;
		mAddress = address;
		mCardInfo = card;
	}

	/**
	 * @return the mPhone
	 */
	public String getmPhone() {
		return mPhone;
	}

	/**
	 * @param mPhone the mPhone to set
	 */
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	/**
	 * @return the mEmail
	 */
	public String getmEmail() {
		return mEmail;
	}

	/**
	 * @param mEmail the mEmail to set
	 */
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
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
	 * @return the mAddress
	 */
	public String getmAddress() {
		return mAddress;
	}

	/**
	 * @param mAddress the mAddress to set
	 */
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	/**
	 * @return the mCardInfo
	 */
	public String getmCardInfo() {
		return mCardInfo;
	}

	/**
	 * @param mCardInfo the mCardInfo to set
	 */
	public void setmCardInfo(String mCardInfo) {
		this.mCardInfo = mCardInfo;
	}
	
	
}
