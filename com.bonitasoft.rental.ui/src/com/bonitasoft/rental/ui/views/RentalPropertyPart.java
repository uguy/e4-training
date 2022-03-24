
package com.bonitasoft.rental.ui.views;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class RentalPropertyPart {

	private Label infoRentedObjectValue;
	private Label infoRenterValue;
	private Label periodFromValue;
	private Label periodToValue;

	@PostConstruct
	public void postConstruct(Composite parent) {
		this.createContent(parent);
	}

	private void createContent(Composite parent) {

		Composite rentalGroup = new Composite(parent, SWT.NONE);
		GridLayout mainLayout = new GridLayout(2, false);
		mainLayout.marginLeft = 10;
		mainLayout.marginRight = 10;
		rentalGroup.setLayout(mainLayout);

		Group infoGroup = new Group(rentalGroup, SWT.SHADOW_OUT);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout infoLayout = new GridLayout(2, false);
		infoGroup.setLayout(infoLayout);
		infoGroup.setText("Information");
		Label infoObjectLabel = new Label(infoGroup, SWT.NONE);
		infoObjectLabel.setText("Object: ");
		infoRentedObjectValue = new Label(infoGroup, SWT.NONE);
		infoRentedObjectValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Label infoRenterLabel = new Label(infoGroup, SWT.NONE);
		infoRenterLabel.setText("Renter: ");
		infoRenterValue = new Label(infoGroup, SWT.NONE);
		infoRenterValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group periodGroup = new Group(rentalGroup, SWT.SHADOW_OUT);
		periodGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout periodLayout = new GridLayout(2, false);
		periodGroup.setLayout(periodLayout);
		periodGroup.setText("Dates de location");
		Label periodFromLabel = new Label(periodGroup, SWT.NONE);
		periodFromLabel.setText("Du: ");
		periodFromValue = new Label(periodGroup, SWT.NONE);
		periodFromValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Label periodToLabel = new Label(periodGroup, SWT.NONE);
		periodToLabel.setText("Au: ");
		periodToValue = new Label(periodGroup, SWT.NONE);
		periodToValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Inject
	@Optional
	public void onRentalSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental rental) {
		setRental(rental);
	}

	public void setRental(Rental rental) {
		if (rental != null) {
			RentalObject rentedObject = rental.getRentedObject();
			Customer customer = rental.getCustomer();

			this.infoRentedObjectValue.setText(rentedObject.getName());
			this.infoRenterValue.setText(customer.getDisplayName());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.periodFromValue.setText(sdf.format(rental.getStartDate()));
			this.periodToValue.setText(sdf.format(rental.getEndDate()));
		}
	}

	@Focus
	public void onFocus() {

	}

}