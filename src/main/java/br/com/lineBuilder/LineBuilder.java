package br.com.lineBuilder;

import java.util.LinkedHashSet;

/**
 * @author Danilo P. De Luca
 *
 * https://github.com/danilodeLuca/lineBuilder
 * 
 */
public class LineBuilder {

	private Complementer columnComplementer;
	private char lineComplementer;
	private LinkedHashSet<Column> columns;
	private char[] characters;

	public LineBuilder(int length) {
		this.columns = new LinkedHashSet<>();
		this.characters = new char[length];
		this.lineComplementer = " ".charAt(0);// FIXME
	}

	public LineBuilder lineComplement(char character) {
		this.lineComplementer = character;
		return this;
	}

	public LineBuilder complementColumn(Complementer complementer) {
		this.columnComplementer = complementer;
		return this;
	}

	public Column column(String id) {
		return new Column(id);
	}

	private LineBuilder add(Column col) {
		columns.add(col);
		return this;
	}

	public String build() {
		for (Column column : columns) {
			if (column.lengthExpected() > column.getValue().length()) {
				String complemented = getColumnComplementer().complement(
						column.getValue(), column.lengthExpected());
				column.setValue(complemented);
			}
			final char[] charToReplace = column.getValue().toCharArray();
			int count = 0;
			for (int i = column.getStart(); i <= column.getEnd(); i++) {
				characters[i] = charToReplace[count];
				count++;
			}
		}
		for (int i = 0; i < characters.length; i++) {
			if (characters[i] == '\u0000') {
				characters[i] = this.lineComplementer;
			}
		}
		return String.copyValueOf(characters);
	}

	private Complementer getColumnComplementer() {
		if(this.columnComplementer != null)
			return this.columnComplementer;
		
		return Complementer.onLeft(" ");
	}

	public class Column {
		private final String id;
		private int start;
		private int end;
		private String value;

		public Column(String id) {
			this.id = id;
		}

		public Column at(int start, int end) {
			this.start = start;
			this.end = end;
			return this;
		}

		public LineBuilder value(String value) {
			this.value = value;
			return add(this);
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getId() {
			return id;
		}

		public int lengthExpected() {
			return end - start + 1;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}