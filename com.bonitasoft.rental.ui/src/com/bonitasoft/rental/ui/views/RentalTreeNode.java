package com.bonitasoft.rental.ui.views;

import java.util.Objects;

import com.opcoach.training.rental.RentalAgency;

public class RentalTreeNode {

	private RentalAgency agency;
	private String nodeType;

	public static final String Customers = "Customers";
	public static final String Rentals = "Rental";
	public static final String RentalObjects = "RentalObjects";

	@Override
	public int hashCode() {
		return Objects.hash(agency, nodeType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentalTreeNode other = (RentalTreeNode) obj;
		return Objects.equals(agency, other.agency) && nodeType == other.nodeType;
	}

	enum NodeType {
		Customers, Rentals, RentalObjects
	}

	RentalTreeNode(RentalAgency agency, String nodeType) {
		this.agency = agency;
		this.nodeType = nodeType;
	}

	public RentalAgency getAgency() {
		return agency;
	}

	public String getNodeType() {
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