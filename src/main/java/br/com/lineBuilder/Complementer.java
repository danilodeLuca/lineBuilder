package br.com.lineBuilder;


public class Complementer {

	private String valueComplementer;
	private Direction direction;

	public Complementer(String value, Direction direction) {
		this.valueComplementer = value;
		this.direction = direction;
	}

	public String complement(String val, int expectedLength) {
		return direction.complement(valueComplementer, val, expectedLength);
	}

	public static Complementer onLeft(String value) {
		return new Complementer(value, Direction.ON_LEFT);
	}

	public static Complementer onRight(String value) {
		return new Complementer(value, Direction.ON_RIGHT);
	}

}