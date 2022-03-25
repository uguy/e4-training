package com.bonitasoft.rental.ui.views;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.bonitasoft.rental.ui.RentalUIConstants;
import com.bonitasoft.rental.ui.palette.Palette;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	@Inject
	@Named(RentalUIConstants.RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry imageRegistry;

	@Inject
	private Palette palette;

	@Override
	public Image getImage(Object element) {
		if (element instanceof RentalTreeNode) {
			RentalTreeNode node = (RentalTreeNode) element;
			switch (node.getNodeType()) {
			case RentalTreeNode.Customers: {
				return imageRegistry.get(RentalUIConstants.IMG_CUSTOMER);
			}
			case RentalTreeNode.Rentals: {
				return imageRegistry.get(RentalUIConstants.IMG_RENTAL);
			}
			case RentalTreeNode.RentalObjects: {
				return imageRegistry.get(RentalUIConstants.IMG_RENTAL_OBJECT);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + node.getNodeType());
			}
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		}
		if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		if (element instanceof RentalTreeNode) {
			RentalTreeNode node = (RentalTreeNode) element;
			switch (node.getNodeType()) {
			case RentalTreeNode.Customers: {
				return "Customers";
			}
			case RentalTreeNode.Rentals: {
				return "Rentals";
			}
			case RentalTreeNode.RentalObjects: {
				return "Rental Objects";
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + node.getNodeType());
			}
		}
		if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
			RentalAgency agency = (RentalAgency) parentElement;
			return Arrays.asList(new RentalTreeNode(agency, RentalTreeNode.Customers),
					new RentalTreeNode(agency, RentalTreeNode.Rentals),
					new RentalTreeNode(agency, RentalTreeNode.RentalObjects)).toArray();
		}

		if (parentElement instanceof RentalTreeNode) {
			return ((RentalTreeNode) parentElement).getChildren();
		}

		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof RentalTreeNode) {
			return ((RentalTreeNode) element).getAgency().getCustomers().toArray();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof RentalAgency || element instanceof RentalTreeNode;
	}

	@Override
	public Color getForeground(Object element) {
		return palette.getColorProvider().getForeground(element);
	}

	@Override
	public Color getBackground(Object element) {
		return palette.getColorProvider().getBackground(element);
	}
}
