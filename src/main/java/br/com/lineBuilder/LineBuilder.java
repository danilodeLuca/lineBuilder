package br.com.lineBuilder;

import java.util.LinkedHashSet;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Danilo P. De Luca
 *
 *         https://github.com/danilodeLuca/lineBuilder
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
		this.lineComplementer = ' ';
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

	LineBuilder add(Column col) {
		columns.add(col);
		return this;
	}

	public String build() {
		for (Column column : columns) {
			if (column.lengthExpected() > column.getValueAsNotNull().length()) {
				String complemented = column.getColumnComplementer().complement(column.getValueAsNotNull(), column.lengthExpected());
				column.setValue(complemented);
			}
			final char[] charToReplace = column.getValueAsNotNull().toCharArray();
			int count = 0;
			for (int i = column.getStart() - 1; i < column.getEnd(); i++) {
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

	public Complementer getColumnComplementer() {
		return columnComplementer;
	}

	public class Column {
		private final String id;
		private int start;
		private int end;
		private String value;
		private boolean numeric = false;

		public Column(String id) {
			this.id = id;
		}

		public Column at(int start, int end) {
			this.start = start;
			this.end = end;
			return this;
		}

		public Column numeric() {
			this.numeric = true;
			return this;
		}

		public LineBuilder value(String value) {
			this.value = value;
			return LineBuilder.this.add(this);
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

		public String getValueAsNotNull() {
			return value != null ? value : StringUtils.EMPTY;
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

		public boolean isNumeric() {
			return numeric;
		}

		public void setNumeric(boolean numeric) {
			this.numeric = numeric;
		}

		public Complementer getColumnComplementer() {
			if (LineBuilder.this.getColumnComplementer() != null)
				return LineBuilder.this.getColumnComplementer();

			if (isNumeric())
				return Complementer.onLeft("0");

			return Complementer.onLeft(" ");
		}
	}
}