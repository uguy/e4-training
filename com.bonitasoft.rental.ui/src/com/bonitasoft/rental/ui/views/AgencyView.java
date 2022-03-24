package com.bonitasoft.rental.ui.views;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.bonitasoft.rental.ui.RentalUIConstants;
import com.bonitasoft.rental.ui.event.RentalEvents;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class AgencyView implements RentalUIConstants {

	private RentalAgency rentalAgency;
	private ESelectionService selectionService;
	private TreeViewer treeViewer;

	@Inject
	public AgencyView(RentalAgency rentalAgency, ESelectionService selectionService) {
		this.rentalAgency = rentalAgency;
		this.selectionService = selectionService;
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent, IEclipseContext context, EMenuService menuService) {

		// see navigatorContent
		treeViewer = new TreeViewer(parent);
		RentalProvider rentalProvider = ContextInjectionFactory.make(RentalProvider.class, context);

		treeViewer.setContentProvider(rentalProvider);
		treeViewer.setLabelProvider(rentalProvider);
		treeViewer.setInput(Collections.singleton(rentalAgency));
		treeViewer.getTree().setLinesVisible(true);

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				selectionService
						.setSelection(selection.size() == 1 ? selection.getFirstElement() : selection.toArray());
			}
		});

		GridLayoutFactory.fillDefaults().generateLayout(parent);

		menuService.registerContextMenu(treeViewer.getControl(), "com.bonitasoft.rental.ui.popupmenu.sample");

	}

	@Optional
	@Inject
	public void onNewRentalObject(@UIEventTopic(RentalEvents.RENTAL_RENTAL_OBJECT_NEW) RentalObject rentalObject) {
		if (treeViewer != null && !treeViewer.getControl().isDisposed()) {
			treeViewer.refresh();
		}
	}

	@Inject
	public void onPrefChanged(@Preference(nodePath = PLUGIN_ID, value = PREF_CUSTOMER_COLOR) String customerColor,
			@Preference(nodePath = PLUGIN_ID, value = PREF_RENTAL_COLOR) String rentalColor,
			@Preference(nodePath = PLUGIN_ID, value = PREF_RENTAL_OBJECT_COLOR) String rentalObjectColor) {
		if (treeViewer != null && !treeViewer.getControl().isDisposed()) {
			treeViewer.refresh();
		}
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}

}
