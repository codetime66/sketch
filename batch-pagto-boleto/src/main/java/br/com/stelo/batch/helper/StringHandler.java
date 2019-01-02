package br.com.stelo.batch.helper;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

public class StringHandler {

	private static Charset charset = Charset.forName("UTF-8");

	public static String substringSafe(final String valor, final Integer tamanho) {
		String strResult = valor;

		if (StringUtils.isNotBlank(valor)) {
			if (valor.getBytes().length >= tamanho) {
				strResult = valor.substring(0, tamanho);
			} else {
				strResult = ajustaTamanho(valor, tamanho);
			}
		}
		return strResult;
	}

	public static String ajustaTamanho(final String valor, final Integer tamanho) {

		if (calculaTamanho(valor) > tamanho) {
			return trim(valor, tamanho);
		}
		return valor;
	}

	public static int calculaTamanho(final String texto) {
		final int tamanho = texto.getBytes(java.nio.charset.StandardCharsets.UTF_8).length;
		return tamanho;
	}

	public static String trim(final String string, final int byteLength) {
		for (int index = 0, len = string.length(), bytes = 0; index < len; ++index) {
			bytes += String.valueOf(string.charAt(index)).getBytes(charset).length;
			if (bytes > byteLength) {
				return string.substring(0, index);
			}
		}
		return string;
	}

}
