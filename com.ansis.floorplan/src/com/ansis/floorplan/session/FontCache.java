package com.ansis.floorplan.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.util.font.FPFont;

/**
 * 
 * @author ggrec
 *
 */
public class FontCache 
{

	// ====================== 2. Instance Fields =============================

	final private Map<FPFont, Font> fontMap = new HashMap<FPFont, Font>();

	private Font monospacedFont;


	// ==================== 6. Action Methods ====================

	/**
	 * Get an font by style. Caches the font. 
	 */
	public Font getFont(final FPFont fontStyle) 
	{
		if (fontStyle == null)
			return null;

		Font font = fontMap.get(fontStyle);

		if (font == null) 
		{
			font = Display.getDefault().getSystemFont();

			// Make a font accordingly to user specifications
			final int sizePercent = fontStyle.getSizePercent();
			font = getCustomisedFont(font, sizePercent, fontStyle.getSwtStyle());
			fontMap.put(fontStyle, font);
		}
		return font;
	}

	/**
	 * Overridden to dispose all fonts
	 *
	 */
	public void dispose() 
	{
		final Iterator<Font> iter = fontMap.values().iterator();
		while (iter.hasNext())
			(iter.next()).dispose();
		fontMap.clear();

		if (monospacedFont != null)
			monospacedFont.dispose();
	}


	// ==================== 13. Utility Methods ====================

	/**
	 * This gets a customized font, the customized items are
	 * <code>sizeParent</code> and <code>style</code>.
	 * 
	 * @param referenceFont
	 *            is the font which is used as reference for the new font (the
	 *            returned font is relative to this one)
	 * @param sizePercent
	 *            is a percent of the reference font size
	 * @param style
	 *            can be SWT.BOLD, SWT.NONE, SWT.ITALIC, etc. or combinations of
	 *            them.
	 * @return
	 */
	public Font getCustomisedFont(final Font referenceFont, final int sizePercent, final int style) 
	{
		final FontData fontData = referenceFont.getFontData()[0];
		fontData.setHeight((int) (fontData.getHeight() * sizePercent / 100.0 + 0.5));
		fontData.setStyle(style);

		return new Font(Display.getCurrent(), fontData);
	}

	/**
	 * Creates a courier font because it is as a monospace font.
	 * It is disposed automatically with the cache.
	 * 
	 * @return
	 */
	public Font getMonospaceFont() 
	{
		if (monospacedFont == null) 
		{
			final Font font = Display.getDefault().getSystemFont();
			monospacedFont = new Font(font.getDevice(), "courier", font.getFontData()[0].getHeight(), font.getFontData()[0].getStyle()); //$NON-NLS-1$
		}		
		return monospacedFont;
	}
}
