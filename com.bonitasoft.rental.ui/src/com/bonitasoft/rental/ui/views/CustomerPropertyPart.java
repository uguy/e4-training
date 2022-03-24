
package com.bonitasoft.rental.ui.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;

public class CustomerPropertyPart {

	private Label customerFullNameLabel;

	@PostConstruct
	public void postConstruct(Composite parent) {

		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setLayout(new GridLayout(1, false));

		customerFullNameLabel = new Label(group, SWT.NONE);
		customerFullNameLabel.setText("Customer name");
		customerFullNameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Inject
	@Optional
	public void onCustomerSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Customer customer) {
		if (customer != null) {
			customerFullNameLabel.setText(customer.getDisplayName());
		}
	}

	@Inject
	@Optional
	public void onRentalSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection, Adapter adapter) {
		if (selection != null) {
			Customer customer = adapter.adapt(selection, Customer.class);
			if (customer != null) {
				customerFullNameLabel.setText(customer.getDisplayName());
			}
		}
	}

	@Inject
	@Optional
	public void onRentalE3Selection(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection,
			Adapter adapter) {
		if (selection != null) {
			onRentalSelection(selection.getFirstElement(), adapter);
		}
	}

}