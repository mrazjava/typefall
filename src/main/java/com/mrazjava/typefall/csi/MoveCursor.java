package com.mrazjava.typefall.csi;

public enum MoveCursor implements CSI {
	to(0, 0),
	toUpperLeftCorner(1, 1);
	
	int row;
	
	int column;
	
	final String SEQUENCE = "[%d;%dH";
	
	private MoveCursor(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Moves cursor to the position of the screen. Left upper corner is represented 
	 * by row = 1, column = 1.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public MoveCursor specificPosition(int row, int column) {
		this.row = row;
		this.column = column;
		return this;
	}
		
	public String sequence() {
		return String.format(SEQUENCE, row, column);
	}

	@Override
	public String value() {
		return esc + sequence();
	}

	@Override
	public String toString() {
		return asString();
	}
}
