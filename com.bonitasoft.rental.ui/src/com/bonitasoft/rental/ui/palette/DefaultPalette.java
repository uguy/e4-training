package com.bonitasoft.rental.ui.palette;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.bonitasoft.rental.ui.RentalUIConstants;
import com.bonitasoft.rental.ui.views.RentalTreeNode;

public class DefaultPalette implements IColorProvider {

	@Inject
	@Named(RentalUIConstants.RENTAL_UI_PREF_STORE)
	private IPreferenceStore prefStore;

	private Color getPrefColor(String key) {
		String rgbKey = prefStore.getString(key);

		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color result = colorRegistry.get(rgbKey);
		if (result == null) {
			// Get value in pref store
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			result = colorRegistry.get(rgbKey);
		}

		return result;

	}

	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof RentalTreeNode) {
			RentalTreeNode node = (RentalTreeNode) element;
			switch (node.getNodeType()) {
			case RentalTreeNode.Customers: {
				return getPrefColor(RentalUIConstants.PREF_CUSTOMER_COLOR);
			}
			case RentalTreeNode.Rentals: {
				return getPrefColor(RentalUIConstants.PREF_RENTAL_COLOR);
			}
			case RentalTreeNode.RentalObjects: {
				return getPrefColor(RentalUIConstants.PREF_RENTAL_OBJECT_COLOR);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + node.getNodeType());
			}
		}
		return null;
	}

}
