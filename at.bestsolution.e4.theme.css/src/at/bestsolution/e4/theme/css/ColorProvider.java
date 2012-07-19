package at.bestsolution.e4.theme.css;

import java.util.Map;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public interface ColorProvider {
	public Map<String, RGB> getColors(Display d); 
}
