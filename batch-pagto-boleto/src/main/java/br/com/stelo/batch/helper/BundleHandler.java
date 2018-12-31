package br.com.stelo.batch.helper;

import java.util.ResourceBundle;

public final class BundleHandler {

	private static BundleHandler INSTANCE;
	private final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

	private BundleHandler() {
	}

	public static BundleHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BundleHandler();
		}
		return INSTANCE;
	}

	public static String getValue(final String codigo) {
		return getInstance().getString(codigo);
	}

	public static String getValue(final String codigo, final Object... params) {
		return getInstance().getString(codigo, params);
	}

	public String getString(final String codigo) {
		try {
			return resourceBundle.getString(codigo);
		} catch (final Exception ex) {
			return null;
		}
	}

	public String getString(final String codigo, final Object... params) {
		try {

			final String bundleValue = resourceBundle.getString(codigo);
			return String.format(bundleValue, params);

		} catch (final Exception ex) {
			return null;
		}
	}
}