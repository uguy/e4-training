package com.bonitasoft.rental;

import org.eclipse.core.runtime.IAdapterFactory;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class RentalToCustomerAdapter implements IAdapterFactory {

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject != null && Customer.class.equals(adapterType)) {
			Rental rental = (Rental) adaptableObject;
			return (T) rental.getCustomer();
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { Customer.class };
	}

}
