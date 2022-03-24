
package com.bonitasoft.rental.ui.command;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class SampleCommandHandler {

	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "Sample", "Hello Sample");
	}

}