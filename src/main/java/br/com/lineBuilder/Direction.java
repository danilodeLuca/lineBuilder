package br.com.lineBuilder;

public enum Direction {
	ON_LEFT {
		@Override
		public String complement(String valueComplementer, String val,
				int expectedLength) {
			while (val.length() < expectedLength) {
				val = valueComplementer.concat(val);
			}
			return val;
		}
	},
	ON_RIGHT {
		@Override
		public String complement(String valueComplementer, String val,
				int expectedLength) {
			while (val.length() < expectedLength) {
				val = val.concat(valueComplementer);
			}
			return val;
		}
	};

	public abstract String complement(String valueComplementer, String val,
			int expectedLength);
}