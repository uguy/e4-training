package com.bonitasoft.rental.ui.views;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.bonitasoft.rental.ui.RentalUIConstants;
import com.bonitasoft.rental.ui.views.RentalProvider.Node.NodeType;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	@Inject
	@Named(RentalUIConstants.RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry imageRegistry;

	@Override
	public Image getImage(Object element) {
		if (element instanceof Node) {
			Node node = (Node) element;
			switch (node.getNodeType()) {
			case Customers: {
				return imageRegistry.get(RentalUIConstants.IMG_CUSTOMER);
			}
			case Rentals: {
				return imageRegistry.get(RentalUIConstants.IMG_RENTAL);
			}
			case RentalObjects: {
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
		if (element instanceof Node) {
			Node node = (Node) element;
			switch (node.getNodeType()) {
			case Customers: {
				return "Customers";
			}
			case Rentals: {
				return "Rentals";
			}
			case RentalObjects: {
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
			return Arrays.asList(new Node(agency, NodeType.Customers), new Node(agency, NodeType.Rentals),
					new Node(agency, NodeType.RentalObjects)).toArray();
		}

		if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}

		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Node) {
			return ((Node) element).getAgency().getCustomers().toArray();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof RentalAgency || element instanceof Node;
	}

	static class Node {

		private RentalAgency agency;
		private NodeType nodeType;

		enum NodeType {
			Customers, Rentals, RentalObjects
		}

		Node(RentalAgency agency, NodeType nodeType) {
			this.agency = agency;
			this.nodeType = nodeType;
		}

		public RentalAgency getAgency() {
			return agency;
		}

		public NodeType getNodeType() {
			return nodeType;
		}

		public Object[] getChildren() {
			switch (nodeType) {
			case Customers: {
				return agency.getCustomers().toArray();
			}
			case Rentals: {
				return agency.getRentals().toArray();
			}
			case RentalObjects: {
				return agency.getObjectsToRent().toArray();
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + nodeType);
			}
		}
	}

	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof Node) {
			Node node = (Node) element;
			switch (node.getNodeType()) {
			case Customers: {
				return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
			}
			case Rentals: {
				return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			}
			case RentalObjects: {
				return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + node.getNodeType());
			}
		}
		return null;
	}
}
