
package com.bonitasoft.rental.ui.addon;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.bonitasoft.rental.ui.RentalUIConstants;
import com.bonitasoft.rental.ui.event.RentalEvents;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class RentalAgencyAddon implements RentalUIConstants {

	@PostConstruct
	public void applicationStarted(IEclipseContext context, IExtensionRegistry extensionRegistry) {

		RentalAgency rentalAgency = RentalAgencyGenerator.createSampleAgency();
		context.set(RentalAgency.class, rentalAgency);

		context.set(RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());

		IPreferenceStore prefStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		context.set(RENTAL_UI_PREF_STORE, prefStore);

		IConfigurationElement[] configurationElements = extensionRegistry
				.getConfigurationElementsFor("org.eclipse.e4.workbench.model");
		for (int i = 0; i < configurationElements.length; i++) {
			IConfigurationElement element = configurationElements[i];

			String name = element.getName();
			String namespaceIdentifier = element.getNamespaceIdentifier();
			switch (name) {
			case "processor": {
				System.out.println(String.format("Model %s class %s found in  %s", name, element.getAttribute("class"),
						namespaceIdentifier));
				break;
			}
			case "fragment": {
				System.out.println(String.format("Model %s uri %s found in  %s", name, element.getAttribute("uri"),
						namespaceIdentifier));
				break;
			}
			default:
				System.out.println(String.format("Model %s found in  %s", name, namespaceIdentifier));
			}

		}

	}

	ImageRegistry getLocalImageRegistry() {
		// Get the bundle using the universal method to get it from the current class
		Bundle b = FrameworkUtil.getBundle(getClass());

		// Create a local image registry
		ImageRegistry reg = new ImageRegistry();

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));

		return reg;
	}

	@Optional
	@Inject
	public void onNewRentalObject(@UIEventTopic(RentalEvents.RENTAL_CUSTOMER_COPY) Customer customer) {
		System.out.println("Customer copy: " + customer);
	}
}
