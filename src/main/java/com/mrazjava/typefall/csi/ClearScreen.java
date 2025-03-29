package com.mrazjava.typefall.csi;

/**
 * Definition for clearing the console screen in various ways. The string value 
 * of this enum should be sent to System.out and prefixed by ESC character: \033. 
 */
public enum ClearScreen implements CSI {

	fromCursorToEndScreen(1),
	entirely(2),
	andDeleteScrollBuffer(3);
	
	final int code;
	
	final String SEQUENCE = "[%dJ";
	
	private ClearScreen(int code) {
		this.code = code;
	}

	@Override
	public String value() {
		return esc + sequence();
	}
	
	public String sequence() {
		return String.format(SEQUENCE, code);
	}

	@Override
	public String toString() {
		return asString();
	}
}
