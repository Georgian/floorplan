package com.ansis.floorplan.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.util.color.FPColor;

/**
 * Creates and caches colors. Call dispose in the end.
 * 
 * @author akozma
 *
 */
public class ColorCache
{

	// ====================== 2. Instance Fields =============================

	protected Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

	protected Map<FPColor, Color> colorMap = new HashMap<FPColor, Color>(10);


	// ==================== 6. Action Methods ====================

	public Color getColor(final RGB rgb) 
	{
		Color color = fColorTable.get(rgb);
		if (color == null) 
		{
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}

	public Color getColor(final FPColor color) 
	{
		if (colorMap.containsKey(color))
			return colorMap.get(color);

		// Create color and return:
		// Color c = new Color(PlatformUI.getWorkbench().getDisplay(), colorID.red(), colorID.green(), colorID.blue());
		// TODO is there a reason, why we should use workbench???
		// changed, to be able to run TotalRowPOTest
		final Color c = new Color(Display.getCurrent(), color.red(), color.green(), color.blue());
		colorMap.put(color, c);
		return c;
	}

	public void dispose() 
	{
		final Iterator<Color> e = fColorTable.values().iterator();
		while (e.hasNext())
			e.next().dispose();

		destroyColors();
	}

	/**
	 * Destroy all colors in map and sets colorMap to null. Null-safe.
	 */
	private void destroyColors() 
	{
		if (colorMap == null)
			return;

		for (final Color c : colorMap.values()) 
		{
			if (c != null)
				c.dispose();
		}

		colorMap.clear();
		fColorTable.clear();
	}
}
