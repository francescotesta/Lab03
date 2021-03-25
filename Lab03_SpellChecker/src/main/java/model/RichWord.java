package model;

public class RichWord {
	
	private String parola;
	private boolean corretto;
	
	public RichWord(String parola) {
		super();
		this.parola = parola;
		corretto = false;
	}

	public void setCorretto() {
		// TODO Auto-generated method stub
		corretto = true;
	}

	/**
	 * @return the parola
	 */
	public String getParola() {
		return parola;
	}

	/**
	 * @param parola the parola to set
	 */
	public void setParola(String parola) {
		this.parola = parola;
	}

	/**
	 * @return the corretto
	 */
	public boolean isCorretto() {
		return corretto;
	}
	
	

}
