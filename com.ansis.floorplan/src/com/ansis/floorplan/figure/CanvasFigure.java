package com.ansis.floorplan.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import com.ansis.floorplan.app.Activator;


public class CanvasFigure extends Figure {

	// ==================== 4. Constructors ====================

	public CanvasFigure() {
		final XYLayout layout = new XYLayout();

		setLayoutManager(layout);
	}


	// ==================== 7. Getters & Setters ====================

	public void setLayout(final Rectangle rect) {
		setBounds(rect);
	}

	@Override
	protected void paintFigure(final Graphics graphics) {
		final Image newImg = Activator.getImageDescriptor("icons/floorplan.png").createImage(); //$NON-NLS-1$

		final Rectangle rect = getBounds().getCopy();
		final org.eclipse.swt.graphics.Rectangle imgBox = newImg.getBounds();
		graphics.drawImage(newImg, 0, 0, imgBox.width, imgBox.height, rect.x, rect.y, rect.width, rect.height);

		super.paintFigure(graphics);
		newImg.dispose();

//		super.paintFigure(graphics);
	}

}