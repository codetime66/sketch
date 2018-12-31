package br.com.stelo.batch.helper;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FixedLengthField implements Serializable {

	public enum Behaviour {
		LEFT_ZERO,
		LEFT_BLANK,
		RIGHT_ZERO,
		RIGHT_BLANK;
	}

	public static FixedLengthField to(final int size, final Behaviour behaviour) {
		return new FixedLengthField(size, behaviour);
	}

	public static FixedLengthField to(final int size) {
		return new FixedLengthField(size);
	}

	private static final long serialVersionUID = 5649440687786613392L;

	private static final char BLANK = ' ';
	private static final char ZERO = '0';

	private final int size;
	private final Behaviour behaviour;
	private StringBuffer word;

	private FixedLengthField(final int size) {
		this(size, Behaviour.RIGHT_BLANK);
	}

	private FixedLengthField(final int size, final Behaviour behaviour) {
		this.size = size;
		this.behaviour = behaviour;
	}

	public FixedLengthField setDouble(final Double value, final int scale) {
		if (value != null) {
			return setString(BigDecimal.valueOf(value).setScale(scale, BigDecimal.ROUND_DOWN).toString().replaceAll("\\.", ""));
		}

		return this;
	}

	public FixedLengthField setInt(final Integer value) {
		if (value == null) {
			return this;
		}

		return setString(String.valueOf(value));
	}

	public FixedLengthField setDate(final Date value, final String format) {
		if (value != null) {
			return setString(new SimpleDateFormat(format).format(value));
		}

		return this;
	}

	public FixedLengthField setLong(final Long value) {
		if (value != null) {
			return setString(String.valueOf(value));
		}

		return this;
	}

	public FixedLengthField setString(String s) {
		if (s == null) {
			return this;
		}

		int ssize = s.length();

		if (ssize > size) {
			s = s.trim();
			ssize = s.length();
		}

		if (ssize > size) {
			return this;
		} else if (word != null && s.equals(word)) {
			return this;
		} else if (ssize == size) {
			emptyWord();
			word.append(s);
			return this;
		}

		emptyWord();

		switch (behaviour) {
			case LEFT_ZERO:
				fillWord(ZERO, size - ssize);
				word.append(s);
				break;
			case LEFT_BLANK:
				fillWord(BLANK, size - ssize);
				word.append(s);
				break;
			case RIGHT_ZERO:
				word.append(s);
				fillWord(ZERO, size - ssize);
				break;
			case RIGHT_BLANK:
				word.append(s);
				fillWord(BLANK, size - ssize);
				break;
		}

		return this;
	}

	@Override
	public String toString() {
		if (word == null) {
			setString("");
		}

		return word.toString();
	}

	public int capacity() {
		return size;
	}

	public Behaviour getBehaviour() {
		return behaviour;
	}

	private void fillWord(final char c, final int length) {
		for (int i = 0; i < length; i++) {
			word.append(c);
		}
	}

	private void emptyWord() {
		word = word != null ? word.delete(0, size) : new StringBuffer(size);
	}

	public static void main(final String[] args) {
		System.out.println(FixedLengthField.to(14, Behaviour.LEFT_ZERO).setDouble(new Double("890776.5466"), 2).toString());
	}

}
