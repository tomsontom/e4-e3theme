package at.bestsolution.e4.theme.css.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.css.core.css2.CSS2ColorHelper;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import at.bestsolution.e4.theme.css.ColorProvider;


@SuppressWarnings("restriction")
public class ColorRegisterAddon {

	@PostConstruct
	void init() {
		Display d = Display.getCurrent();
		System.err.println("==================> Initing with Display: " + d );
		try {
			Field f = CSS2ColorHelper.class.getDeclaredField("colorNamesMap");
			f.setAccessible(true);
			@SuppressWarnings("unchecked")
			Map<String,String> colorNamesMap = (Map<String, String>) f.get(null);
			
			f = CSS2ColorHelper.class.getDeclaredField("colorHexasMap");
			f.setAccessible(true);
			@SuppressWarnings("unchecked")
			Map<String,String> colorHexasMap = (Map<String, String>) f.get(null);
			
			
			for( ColorProvider p : getProviders() ) {
				for( Entry<String, RGB> e : p.getColors(d).entrySet() ) {
					String name = e.getKey().toLowerCase();
					String hexColor = toHex(e.getValue()).toUpperCase();
					colorNamesMap.put(name, hexColor);
					colorHexasMap.put(hexColor, name);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	private static String toHex(RGB rgb) {
		return "#" + hexValue(rgb.red) + hexValue(rgb.green) + hexValue(rgb.blue);
	}
	
	private static String hexValue(int i) {
		String s = Integer.toHexString(i);
		return s.length() == 1 ? "0"+s : s;
	}
	
	private List<ColorProvider> getProviders() {
		List<ColorProvider> l = new ArrayList<ColorProvider>();
		Bundle b = FrameworkUtil.getBundle(ColorRegisterAddon.class);
		BundleContext ctx = b.getBundleContext();
		try {
			for( ServiceReference<ColorProvider> s : ctx.getServiceReferences(ColorProvider.class,null) ) {
				l.add(ctx.getService(s));
			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}
}
