package br.com.lineBuilder;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	@Test
	public void simpleColumn() {
		String build = new LineBuilder(4).column("test").at(1, 4).value("test")
				.build();
		Assert.assertEquals("test", build);
	}

	@Test
	public void simpleColumnLongValueLength() {
		String build = new LineBuilder(4).column("test").at(1, 4).value("testasdasd").build();
		Assert.assertEquals("test", build);
	}

	@Test
	public void twoColumn() {
		String build = new LineBuilder(9).column("test").at(1, 4).value("test").column("test2").at(5, 9).value("test2").build();
		Assert.assertEquals("testtest2", build);
	}

	@Test
	public void complementerOnLeftWithLengthExpectedOk() {
		String build = new LineBuilder(4)
				.complementColumn(Complementer.onLeft("|")).column("test")
				.at(1, 4).value("test").build();
		Assert.assertEquals("test", build);
	}

	@Test
	public void complementerOnLeftWithLengthExpectedNotOk() {
		String build = new LineBuilder(5)
				.complementColumn(Complementer.onLeft("|")).column("test")
				.at(1, 5).value("test").build();
		Assert.assertEquals("|test", build);
	}

	@Test
	public void complementerOnRightWithLengthExpectedOk() {
		String build = new LineBuilder(4)
				.complementColumn(Complementer.onRight("|")).column("test")
				.at(1, 4).value("test").build();
		Assert.assertEquals("test", build);
	}

	@Test
	public void complementerOnRightWithLengthExpectedNotOk() {
		String build = new LineBuilder(5)
				.complementColumn(Complementer.onRight("|")).column("test")
				.at(1, 5).value("test").build();
		Assert.assertEquals("test|", build);
	}

	@Test
	public void notSettedValuesForSomeRangeShouldBeBackBar() {
		String build = new LineBuilder(5)
				.complementColumn(Complementer.onRight("|")).column("test")
				.at(1, 5).value("test").build();
		Assert.assertEquals("test|", build);
	}

	@Test
	public void notSettedValuesForSomeRangeShouldBeBackSpace2() {
		String build = new LineBuilder(6)
				.complementColumn(Complementer.onLeft("|")).column("test")
				.at(1, 5).value("test").build();
		Assert.assertEquals("|test ", build);
	}

	@Test
	public void notSettedValuesForSomeRangeShouldSpecificComplementer() {
		String build = new LineBuilder(6).lineComplement("R".charAt(0))
				.complementColumn(Complementer.onLeft("|")).column("test")
				.at(1, 5).value("test").build();
		Assert.assertEquals("|testR", build);
	}
}
