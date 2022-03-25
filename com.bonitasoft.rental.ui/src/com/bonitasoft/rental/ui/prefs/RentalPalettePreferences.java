package com.bonitasoft.rental.ui.prefs;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.bonitasoft.rental.ui.RentalUIConstants;
import com.bonitasoft.rental.ui.palette.Palette;

/** This preference page manage the palette value */
public class RentalPalettePreferences extends FieldEditorPreferencePage implements RentalUIConstants

{

	@Inject
	@Named(PALETTE_MANAGER)
	private Map<String, Palette> palettes;

	public RentalPalettePreferences() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		// Extract the double String array for name and color provider (value is
		// the key)

		String[][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (Palette p : palettes.values()) {
			comboValues[i][0] = p.getName(); // Displayed name
			comboValues[i][1] = p.getId(); // Returned value if selected
			i++;
		}
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette couleur :", comboValues, getFieldEditorParent()));

	}

}