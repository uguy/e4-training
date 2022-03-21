package com.bonitasoft.rental.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalCoreActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.bonitasoft.rental.core"; //$NON-NLS-1$

	// The shared instance
	private static RentalCoreActivator plugin;

	private static RentalAgency rentalAgency = RentalAgencyGenerator.createSampleAgency();

	public static RentalAgency getAgency() {
		return rentalAgency;
	}

	/**
	 * The constructor
	 */
	public RentalCoreActivator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalCoreActivator getDefault() {
		return plugin;
	}

}
