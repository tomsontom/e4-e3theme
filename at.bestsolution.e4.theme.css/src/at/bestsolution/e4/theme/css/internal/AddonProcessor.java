package at.bestsolution.e4.theme.css.internal;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationFactory;

@SuppressWarnings("restriction")
public class AddonProcessor {
	@Execute
	void addAddon(MApplication application) {
		for( MAddon a : application.getAddons() ) {
			if( a.getContributionURI().equals("bundleclass://at.bestsolution.e4.theme.css/at.bestsolution.e4.theme.css.internal.ColorRegisterAddon") ) {
				return;
			}
		}
		
		MAddon a = MApplicationFactory.INSTANCE.createAddon();
		a.setContributionURI("bundleclass://at.bestsolution.e4.theme.css/at.bestsolution.e4.theme.css.internal.ColorRegisterAddon");
		application.getAddons().add(a);
	}
}
