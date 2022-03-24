package com.bonitasoft.rental.ui;

/**
 * Constant definitions for plug-in preferences, keys for colors, and to
 * identify objects nature.
 */
public interface RentalUIConstants {

	public static final String PLUGIN_ID = "com.bonitasoft.rental.ui";
	public static final String RENTAL_UI_PREF_STORE = PLUGIN_ID + ".preferenceStore";
	public static final String RENTAL_UI_IMG_REGISTRY = PLUGIN_ID + ".imageRegistry";

	// The perspective ID as define in the snippet fragment.
	public static final String RENTAL_UI_PERSPECTIVE = PLUGIN_ID + ".perspective";

	// Constants to manage object images in registry. Constant values are path to
	// icons
	public static final String IMG_AGENCY = "icons/Agency.png";
	public static final String IMG_CUSTOMER = "icons/Customers.png";
	public static final String IMG_RENTAL = "icons/Rentals.png";
	public static final String IMG_RENTAL_OBJECT = "icons/RentalObjects.png";
	public static final String IMG_COLLAPSE_ALL = "icons/collapseall.gif";
	public static final String IMG_EXPAND_ALL = "icons/expandall.gif";

	// Nodes for providers
	public static final String CUSTOMERS_NODE = "Clients";
	public static final String RENTALS_NODE = "Locations";
	public static final String OBJECTS_NODE = "Objets à louer";

	// Preferences constants
	public static final String PALETTE_MANAGER = PLUGIN_ID + ".paletteManager";
	public static final String PREF_PALETTE = "prefPalette";
	public static final String PREF_CUSTOMER_COLOR = "CustomerColor";
	public static final String PREF_RENTAL_COLOR = "RentalColor";
	public static final String PREF_RENTAL_OBJECT_COLOR = "RentalObjectColor";

	// See this value in the plugin.xml palette extension
	public static final String DEFAULT_PALETTE = PLUGIN_ID + ".DefaultPalette";

	public static final String PREF_DISPLAY_COUNT = "displayCounterPref";

}