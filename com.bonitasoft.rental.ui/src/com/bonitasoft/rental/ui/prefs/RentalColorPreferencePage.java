package com.bonitasoft.rental.ui.prefs;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.bonitasoft.rental.ui.RentalUIConstants;

public class RentalColorPreferencePage extends FieldEditorPreferencePage implements RentalUIConstants {

	public RentalColorPreferencePage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Couleur pour client", getFieldEditorParent()));

	}

}
