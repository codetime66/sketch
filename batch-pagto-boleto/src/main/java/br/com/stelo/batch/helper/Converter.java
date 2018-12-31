package br.com.stelo.batch.helper;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class Converter {
	public static BigDecimal convertToBigDecimal(final Double valor, final int scale) {
		if (valor == null) {
			return BigDecimal.ZERO;
		}
		return convertDoubleToBigDecimal(valor);
	}

	public static BigDecimal convertDoubleToBigDecimal(final Double n) {
		if (n != null) {
			return BigDecimal.valueOf(n);
		}
		return null;
	}

	public static BigDecimal convertToBigDecimal(final String valor, final int scale) {
		if (StringUtils.isEmpty(valor)) {
			return BigDecimal.ZERO;
		}

		return new BigDecimal(
			String.format("%s.%s",
				valor.substring(0, valor.length() - scale),
				valor.substring(valor.length() - scale, valor.length()))).
				setScale(scale);
	}

}
