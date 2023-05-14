package com.commons.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6352001837451571892L;
	
	private boolean correcto;

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
	
	

}
