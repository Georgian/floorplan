package com.ansis.floorplan.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;


public class PollyFigure extends PolygonShape implements IFigure{

	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();

	private Label labelName = new Label();

	private Label labelEtage = new Label();

	Rectangle r;

	Rectangle g2;
	
	public static final int POLLY_FIGURE_DEFWIDTH = 250;

	public static final int POLLY_FIGURE_DEFHEIGHT = 150;


	// ==================== 4. Constructors ====================

	public PollyFigure(final Rectangle g) {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		
//		g2 = new Rectangle(0,0,100,100);
//		
//		if (g == null)
//			g = g2;
		
		g2 = new Rectangle(g);
		
		g2.y = g2.y + 15;
		
		g2.height = g2.height +20;
		
		labelName.setForegroundColor(ColorConstants.black);
		labelName.setOpaque(true);
		labelName.setBackgroundColor(ColorConstants.lightBlue);
//		final Font classFont = new Font(null, "Arial", 12, SWT.BOLD);
		labelName.setFont(new Font(null, "Lucida Handwriting", 7, SWT.BOLD));
		
		add(labelName, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelName, g);

		
		labelEtage.setForegroundColor(ColorConstants.black);
		labelEtage.setOpaque(true);
		labelEtage.setFont(new Font(null, "Lucida Handwriting", 10, SWT.NORMAL));
		labelEtage.setBackgroundColor(ColorConstants.lightBlue);
		
		add(labelEtage, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelEtage, g2);
		
		
	
		
		setBackgroundColor(ColorConstants.yellow);
		  setForegroundColor(ColorConstants.green);
		  setLineStyle(2);
		  setLineWidth(3);
		
		
		
		
		
		System.out.println("LOOK HERE");
		System.out.println(g);
		System.out.println(g2);
		
		setPoints(list);
	}


	// ==================== 7. Getters & Setters ====================

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
	}

	public void setName(final String name) {
		labelName.setText(name);
	}

	public void setEtage(final int etage) {
		labelEtage.setText("Etage:"+etage);
	}

	public void setLayout(final Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

	public void setList(final PointList list) {
		setPoints(list);
		this.list = list;
	}

}