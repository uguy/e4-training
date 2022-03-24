package com.bonitasoft.rental.ui.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;
import org.osgi.service.prefs.BackingStoreException;

import com.bonitasoft.rental.ui.RentalUIConstants;

public class RentalPreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants {

	public RentalPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences node = DefaultScope.INSTANCE.getNode(PLUGIN_ID);
		if (node != null) {
			node.put(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(246, 224, 224)));
			node.put(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(224, 224, 246)));
			node.put(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(new RGB(224, 246, 224)));

			try {
				node.flush();
			} catch (BackingStoreException e) {
				// FIXME: use logger, e.printStackTrace();
			}
		}
	}

}
