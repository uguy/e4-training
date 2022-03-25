package com.bonitasoft.rental.ui.palette;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {
	private String id;
	private String name;
	private String paletteClass;

	private IColorProvider colorProvider;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaletteClass() {
		return paletteClass;
	}

	public void setPaletteClass(String paletteClass) {
		this.paletteClass = paletteClass;
	}

	public IColorProvider getColorProvider() {
		return colorProvider;
	}

	public void setColorProvider(IColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}

}
