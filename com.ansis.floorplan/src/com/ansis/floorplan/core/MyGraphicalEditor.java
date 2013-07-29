package com.ansis.floorplan.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ansis.floorplan.app.Activator;
import com.ansis.floorplan.app.MyTemplateTransferDropTargetListener;
import com.ansis.floorplan.factory.ModelTestCreationFactory;
import com.ansis.floorplan.factory.PartEditFactory;
import com.ansis.floorplan.model.Canvas;
import com.ansis.floorplan.model.Polly;
import com.ansis.floorplan.provider.AppContextMenuProvider;

public class MyGraphicalEditor extends GraphicalEditorWithPalette {

	// ==================== 1. Static Fields ========================

	public static final String ID = "myEditor"; //$NON-NLS-1$

	public Rectangle r,g;
	private Canvas model;

	// ==================== 4. Constructors ====================

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	// ==================== 5. Creators ====================


	public Canvas createContent() {

		final Canvas canvas = new Canvas();

		final Polly polly = new Polly();

		polly.setList(new PointList(new int[] { 50, 50, 200, 200, 150, 50 }));

		polly.setName("works!");
		polly.setEtage(1);

		Point point = null, pointmin = null, pointmax = null, pointg = new Point(0,0);
		Point pointk = new Point(0,0);

		for (int i = 0; i < polly.getList().size(); i++) {
			if (pointmin == null)
				pointmin = polly.getList().getPoint(i);

			if (pointmax == null)
				pointmax = polly.getList().getPoint(i);


			point = polly.getList().getPoint(i);

			
			if(point.x < pointmin.x)
				pointmin.x = point.x;
			if(point.x > pointmax.x)
				pointmax.x = point.x;
			if(point.y < pointmin.y)
				pointmin.y = point.y;
			if(point.y > pointmax.y)
				pointmax.y = point.y;

		}
		if(pointmin.x != 0)
			for (int i = 0; i < polly.getList().size(); i++) {
				point = polly.getList().getPoint(i);
				point.x = point.x - pointmin.x;
				polly.getList().setPoint(point, i);
				pointg.x = pointg.x + point.x;
				
			}
		if(pointmin.y != 0)
			for (int i = 0; i < polly.getList().size(); i++) {
				point = polly.getList().getPoint(i);
				point.y = point.y - pointmin.y;
				polly.getList().setPoint(point, i);
				pointg.y = pointg.y + point.y;
				
			}


		pointg.x = (pointg.x/polly.getList().size()-35);
		pointg.y = (pointg.y/polly.getList().size());
		pointk.x = pointg.x+70;
		pointk.y = pointg.y+15;


		g = new Rectangle(pointg, pointk);
		r = new Rectangle(pointmin,pointmax);
		polly.setR(r);
		polly.setG(g);

		canvas.addChild(polly);

		point = null; pointmin = null; pointmax = null; pointg = new Point(0,0);
		pointk = new Point(0,0);
		
		final Polly polly2 = new Polly();

		polly2.setList(new PointList(new int[] { 20, 20, 20, 100 , 100, 100, 100, 20}));
		polly2.setEtage(2);
		polly2.setName("also works!");

		// Find min/max points. Compute bounds for our new figure

		for (int i = 0; i < polly2.getList().size(); i++) {
			if (pointmin == null)
				pointmin = polly2.getList().getPoint(i);

			if (pointmax == null)
				pointmax = polly2.getList().getPoint(i);

			point = polly2.getList().getPoint(i);


			if(point.x < pointmin.x)
				pointmin.x = point.x;
			if(point.x > pointmax.x)
				pointmax.x = point.x;
			if(point.y < pointmin.y)
				pointmin.y = point.y;
			if(point.y > pointmax.y)
				pointmax.y = point.y;

		}
		System.out.println("pointmax"+pointmax);
		System.out.println("pointmin"+pointmin);

		if(pointmin.x != 0)
			for (int i = 0; i < polly2.getList().size(); i++) {
				point = polly2.getList().getPoint(i);
				point.x = point.x - pointmin.x;
				polly2.getList().setPoint(point, i);
				pointg.x = pointg.x + point.x;
			}
		if(pointmin.y != 0)
			for (int i = 0; i < polly2.getList().size(); i++) {
				point = polly2.getList().getPoint(i);
				point.y = point.y - pointmin.y;
				polly2.getList().setPoint(point, i);
				pointg.y = pointg.y + point.y;

			}
		
		
		
		pointg.x = (pointg.x/polly2.getList().size()-35);
		pointg.y = (pointg.y/polly2.getList().size());
		pointk.x = pointg.x+70;
		pointk.y = pointg.y+15;
		

		g = new Rectangle(pointg, pointk);
		r = new Rectangle(pointmin,pointmax);
		polly2.setR(r);
		polly2.setG(g);

		canvas.addChild(polly2);


		canvas.setName("FloorPlan");
		canvas.setAddress("Canvas Lable Test");
		canvas.setCapital(1000);

		return canvas;
	}

	// ==================== 6. Action Methods ====================

	@Override
	protected void initializeGraphicalViewer() {
		final GraphicalViewer viewer = getGraphicalViewer();
		model = createContent();
		
//		addPropertyListener(new IPropertyListener() {
//			
//			@Override
//			public void propertyChanged(final Object source, final int propId) {
//
//				viewer.setContents(model);
//				
//			}
//		});
		// Add the created model here
		viewer.setContents(model);
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(viewer));
		
		model.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
//				viewContents(model);
				
			}
		});
	}

	@Override
	protected void initializePaletteViewer() {
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		final GraphicalViewer viewer = getGraphicalViewer(); 

		viewer.setEditPartFactory(new PartEditFactory());

		final ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		
		final ContextMenuProvider provider = new AppContextMenuProvider(viewer, getActionRegistry());
				viewer.setContextMenu(provider);
	}

	@Override
	public void doSave(final IProgressMonitor monitor) {

	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		final PaletteRoot root = new PaletteRoot();
		final PaletteGroup manipGroup = new PaletteGroup("Manipulating the objects"); //$NON-NLS-1$
		root.add(manipGroup);
		final SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());

		final PaletteSeparator sep2 = new PaletteSeparator();
		root.add(sep2);
		final PaletteGroup instGroup = new PaletteGroup("Element Creation");
		root.add(instGroup);
		instGroup.add(new CombinedTemplateCreationEntry("Polly", "Creating a Polly", Polly.class, new ModelTestCreationFactory(Polly.class), AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/services-low.png"),	AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,	"icons/services-high.png")));

		root.setDefaultEntry(selectionToolEntry);
		return root;
	}
}