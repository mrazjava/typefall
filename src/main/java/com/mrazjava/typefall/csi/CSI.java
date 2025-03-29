package com.mrazjava.typefall.csi;

public interface CSI {
	
	char esc = '\033';
	
	/**
	 * @return actual prefix char value indicating CSI operation; for example 
	 * 	'\033' would return {@code CSI#esc} indicating screen cursor operations
	 */
	String value();
	
	/**
	 * @return human readable representatin of {@code #value()) that can be used 
	 * 	for logging, etc.
	 */
	default String prefix() {
		return "ESC";
	};
	
	String sequence();
	
	default String asString() {
		return ((Enum<?>)this).name() + ": " + prefix() + sequence(); 
	}
}
