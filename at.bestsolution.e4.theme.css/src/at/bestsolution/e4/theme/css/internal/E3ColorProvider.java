package at.bestsolution.e4.theme.css.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import at.bestsolution.e4.theme.css.ColorProvider;
import at.bestsolution.e4.theme.css.ColorUtil;

public class E3ColorProvider implements ColorProvider {

	@Override
	public Map<String, RGB> getColors(Display d) {
		Map<String, RGB> map = new HashMap<String, RGB>();
//		map.put("ACTIVE_TAB_TEXT_COLOR", ColorUtil.getLightenedColor(d, SWT.COLOR_TITLE_FOREGROUND));
		
		RGB endColor = ColorUtil.getLightenedColor(d, SWT.COLOR_TITLE_BACKGROUND);
		RGB startColor = ColorUtil.blend(ColorUtil.white, endColor, 75);
		
		map.put("activeTabBgEnd", endColor);
		map.put("activeTabBgStart", startColor);
		
		return map;
	}

}
