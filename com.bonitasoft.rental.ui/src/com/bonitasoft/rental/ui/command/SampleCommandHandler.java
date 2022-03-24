
package com.bonitasoft.rental.ui.command;

import java.util.UUID;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Shell;

import com.bonitasoft.rental.ui.event.RentalEvents;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalFactory;
import com.opcoach.training.rental.RentalObject;

public class SampleCommandHandler {

	@Execute
	public void execute(Shell shell, IEventBroker broker, RentalAgency agency) {

		RentalObject rentalObject = RentalFactory.eINSTANCE.createRentalObject();
		rentalObject.setName("Pelle à neige " + UUID.randomUUID().toString());
		agency.getObjectsToRent().add(rentalObject);

		broker.post(RentalEvents.RENTAL_RENTAL_OBJECT_NEW, rentalObject);

//		MessageDialog.openInformation(shell, "Objet à louer ajouté", rentalObject.getName());
	}

}