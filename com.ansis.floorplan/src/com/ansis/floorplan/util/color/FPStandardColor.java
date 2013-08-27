package com.ansis.floorplan.util.color;


/**
 * The central FP Color Manager. Get all your colors from here.
 * 
 * @author ggrec
 *
 */
public enum FPStandardColor implements FPColor 
{
	PEPITA_STRONG(172, 255, 181),
	PEPITA_LIGHT(220, 255, 223),
	PEPITA_VERY_LIGHT(235, 235, 255), 
	RED(255, 0, 0),
	RED_DARK(204, 0, 0),
	RED_LIGHT(255, 51, 51),
	RED_VERY_LIGHT(255, 102, 102),
	RED_VERY_VERY_LIGHT(255, 200, 200),
	BLUE_DARK(0, 0, 205),
	BLUE(0, 0, 255),
	GREEN(34, 139, 34),
	ORANGE(255, 165, 0),
	ORANGE_YELLOW(255, 146, 0),
	BROWN(175, 45, 5),
	YELLOW_BROWN(108, 65, 0),
	WHITE (255, 255, 255),
	BLACK (0, 0, 0),
	YELLOW_LIGHT (247, 245, 198),
	GREEN_LIGHT (192, 255, 192),
	GREEN_DARK (0, 100, 0),
	GREEN_OLIVE_DARK(36, 96, 2),
	BLUE_LIGHT (194, 248, 250),
	BLUE_MEDIUM1(112, 100, 200),
	BLUE_MEDIUM2(45, 80, 241),
	BLUE_MEDIUM3(176, 224, 230),
	BLUE_MEDIUM4(175, 238, 238),
	BLUE_NAVY (0, 0, 192),
	BLUE_NAVY2 (173, 216, 230),
	BLUE_GRAY_DARK (76, 109, 124),
	VERY_LIGHT_GRAY(244, 244, 244),
	LIGHT_GRAY(220, 220, 220),
	GRAY(128, 128, 128),
	DARK_GRAY(40, 40, 40),

	ERROR(RED),
	DEBUG(BLUE),
	INFO(GREEN),
	WARNING(ORANGE),
	STATUS(BROWN),

	IREMS_VIOLET(119, 119, 183),
	IREMS_LIGHT_VIOLET(207, 207, 255),
	IREMS_NAVY(61, 61, 126),
	IREMS_DARK_NAVY(37, 34, 88),
	IREMS_GREEN(117, 218, 179),
	IREMS_LIGHT_GREEN(222, 255, 242),
	IREMS_DARK_GREEN(147, 190, 142);


	// ==================== 3. Static Methods ====================

	private static final String COLOR_SEP = "_"; //$NON-NLS-1$

	/**
	 * @return the proper font color (black or white) for the given background color
	 */
	public static FPStandardColor getFontColorForBackground(final FPColor color) 
	{
		final int min = Math.min(color.red(), Math.min(color.green(), color.blue()));
		final int max = Math.max(color.red(), Math.max(color.green(), color.blue()));

		// If the max + min is less than 256 or green does not exceed 191 then the color is darker and the font color should be white
		if (min + max < 256  &&  color.green() < 191)
			return WHITE;

		// Otherwise black
		return BLACK;
	}


	// ====================== 2. Instance Fields =============================

	private int red;
	private int green;
	private int blue;

	private FPStandardColor(final int r, final int g, final int b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	private FPStandardColor(final FPStandardColor color) {
		this.red = color.red;
		this.green = color.green;
		this.blue = color.blue;
	}

	@Override
	public int red() {
		return red;
	}

	@Override
	public int green() {
		return green;
	}

	@Override
	public int blue() {
		return blue;
	}

	public int[] getIntArray() {
		return new int[] {red, green, blue};
	}

	public static FPColor colorFromString(final String value) 
	{
		if (value == null)
			return null;

		final String[] components = value.split(COLOR_SEP);
		final int red = Integer.parseInt(components[0]);
		final int green = Integer.parseInt(components[1]);
		final int blue = Integer.parseInt(components[2]);

		return FPStandardColor.fromRGB(red, green, blue);
	}

	public static String colorToString(final FPColor color)
	{
		if (color == null)
			return null;

		return color.red() + COLOR_SEP + color.green() + COLOR_SEP + color.blue();
	}

	public static FPColor fromRGB(final int red2, final int green2, final int blue2) 
	{
		// First, see whether it was a standard color:
		for (final FPStandardColor c : FPStandardColor.values())
			if (c.red == red2 && c.green == green2 && c.blue == blue2)
				return c;

		// Create a custom color:
		return new FPCustomColor(red2, green2, blue2);
	}
}
