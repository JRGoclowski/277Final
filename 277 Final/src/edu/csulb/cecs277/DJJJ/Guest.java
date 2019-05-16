package edu.csulb.cecs277.DJJJ;

public class Guest {
	
	public static final Guest JOESEPH_JOESTAR = new Guest("1", "OHMYGAWD@hamon.com", "Joeseph Joestar",
			"Hermit... Purple",  new CreditCard());
	public static final Guest DIO_BRANDO = new Guest("2", "BUTITWASME@DIO.com", "Dio Brando", 
			"ZA WARUDO",  new CreditCard());
	public static final Guest JOTARO_KUJO = new Guest("3", "YareYare@Daze.com", "Jotaro Kujo", 
			"Japan", new CreditCard());
	
	private String mPhone, mEmail, mName, mAddress;
	private CreditCard mCard;
	private boolean notifyPhone, notifyEmail;
	
	/**
	 * @return the notifyPhone
	 */
	public boolean isNotifyPhone() {
		return notifyPhone;
	}

	/**
	 * @param notifyPhone the notifyPhone to set
	 */
	public void setNotifyPhone(boolean notifyPhone) {
		this.notifyPhone = notifyPhone;
	}

	/**
	 * @return the notifyEmail
	 */
	public boolean isNotifyEmail() {
		return notifyEmail;
	}

	/**
	 * @param notifyEmail the notifyEmail to set
	 */
	public void setNotifyEmail(boolean notifyEmail) {
		this.notifyEmail = notifyEmail;
	}

	public Guest (String phone, String email, String name, String address, CreditCard card) {
		mPhone = phone;
		mEmail = email;
		mName = name;
		mAddress = address;
		mCard = card;
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
	 * @return the mCard
	 */
	public CreditCard getmCard() {
		return mCard;
	}

	/**
	 * @param mCard the mCard to set
	 */
	public void setmCard(CreditCard mCard) {
		this.mCard = mCard;
	}

	
	
	
}
