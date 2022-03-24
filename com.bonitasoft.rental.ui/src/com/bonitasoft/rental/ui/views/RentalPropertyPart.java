
package com.bonitasoft.rental.ui.views;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalPropertyPart {

	private Label infoRentedObjectValue;
	private Label infoRenterValue;
	private Label periodFromValue;
	private Label periodToValue;
	private RentalAgency rentalAgency;

	@Inject
	public RentalPropertyPart(RentalAgency rentalAgency) {
		this.rentalAgency = rentalAgency;
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		this.createContent(parent);
	}

	private void createContent(Composite parent) {
		Composite mainGroup = new Composite(parent, SWT.NONE);
		GridLayout mainLayout = new GridLayout(2, false);
		mainLayout.marginLeft = 10;
		mainLayout.marginRight = 10;
		mainGroup.setLayout(mainLayout);

		Group infoGroup = new Group(mainGroup, SWT.SHADOW_OUT);
		GridLayout infoLayout = new GridLayout(2, true);
		infoGroup.setLayout(infoLayout);
		infoGroup.setText("Information");
		Label infoObjectLabel = new Label(infoGroup, SWT.NONE);
		infoObjectLabel.setText("Object: ");
		infoRentedObjectValue = new Label(infoGroup, SWT.NONE);
		Label infoRenterLabel = new Label(infoGroup, SWT.NONE);
		infoRenterLabel.setText("Renter: ");
		infoRenterValue = new Label(infoGroup, SWT.NONE);

		Group periodGroup = new Group(mainGroup, SWT.SHADOW_OUT);
		GridLayout periodLayout = new GridLayout(2, true);
		periodGroup.setLayout(periodLayout);
		periodGroup.setText("Dates de location");
		Label periodFromLabel = new Label(periodGroup, SWT.NONE);
		periodFromLabel.setText("Du: ");
		periodFromValue = new Label(periodGroup, SWT.NONE);
		Label periodToLabel = new Label(periodGroup, SWT.NONE);
		periodToLabel.setText("Au: ");
		periodToValue = new Label(periodGroup, SWT.NONE);

		this.setRental(rentalAgency.getRentals().get(0));
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