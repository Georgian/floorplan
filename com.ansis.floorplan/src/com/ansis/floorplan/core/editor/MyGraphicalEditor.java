package com.ansis.floorplan.core.editor;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.ui.actions.ActionFactory;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.app.MyTemplateTransferDropTargetListener;
import com.ansis.floorplan.core.action.FigureColorAction;
import com.ansis.floorplan.core.action.LabelColorAction;
import com.ansis.floorplan.core.action.RenameAction;
import com.ansis.floorplan.core.action.defaults.DefaultFigureColorAction;
import com.ansis.floorplan.core.action.defaults.DefaultFontColorAction;
import com.ansis.floorplan.core.action.defaults.DefaultFontSizeAction;
import com.ansis.floorplan.core.action.defaults.DefaultFontStyleAction;
import com.ansis.floorplan.core.action.defaults.DefaultLabelColorAction;
import com.ansis.floorplan.core.action.defaults.DefaultOpacityAction;
import com.ansis.floorplan.core.action.font.FontColorAction;
import com.ansis.floorplan.core.action.font.size.FontSizeNineAction;
import com.ansis.floorplan.core.action.font.size.FontSizeSevenAction;
import com.ansis.floorplan.core.action.font.size.FontSizeSixteenAction;
import com.ansis.floorplan.core.action.font.size.FontSizeTenAction;
import com.ansis.floorplan.core.action.font.size.FontSizeThirteenAction;
import com.ansis.floorplan.core.action.font.size.FontSizeTwentyAction;
import com.ansis.floorplan.core.action.font.size.FontSizeTwentyfiveAction;
import com.ansis.floorplan.core.action.font.style.FontStyleBoldAction;
import com.ansis.floorplan.core.action.font.style.FontStyleBoldItalicAction;
import com.ansis.floorplan.core.action.font.style.FontStyleItalicAction;
import com.ansis.floorplan.core.action.font.style.FontStyleNormalAction;
import com.ansis.floorplan.core.action.opacity.OpacityEightyAction;
import com.ansis.floorplan.core.action.opacity.OpacityFourtyAction;
import com.ansis.floorplan.core.action.opacity.OpacityHundredAction;
import com.ansis.floorplan.core.action.opacity.OpacitySixtyAction;
import com.ansis.floorplan.core.action.opacity.OpacityTenAction;
import com.ansis.floorplan.core.action.opacity.OpacityTwentyAction;
import com.ansis.floorplan.core.factory.PartEditFactory;
import com.ansis.floorplan.core.factory.PollyLineCreationFactory;
import com.ansis.floorplan.core.factory.RectangleCreationFactory;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.provider.AppContextMenuProvider;


public class MyGraphicalEditor extends GraphicalEditorWithPalette {

	// ==================== 1. Static Fields ========================

	public static final String ID = "myGraphicalEditor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private CanvasModel model;


	// ==================== 4. Constructors ====================

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
		model = new CanvasModel(); 

	}

	// ==================== 5. Creators ====================

	@SuppressWarnings("unchecked")
	@Override
	public void createActions() {
		super.createActions(); 

		final ActionRegistry registry = getActionRegistry();

		final IAction action = new RenameAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// Figure Color
		final FigureColorAction changeColorAction = new FigureColorAction(this, model);
		registry.registerAction(changeColorAction);
		getSelectionActions().add(changeColorAction.getId());

		// Opacity
		final OpacityTenAction tenOpacityAction = new OpacityTenAction(this, model);
		registry.registerAction(tenOpacityAction);
		getSelectionActions().add(tenOpacityAction.getId());

		final OpacityTwentyAction twentyOpacityAction = new OpacityTwentyAction(this, model);
		registry.registerAction(twentyOpacityAction);
		getSelectionActions().add(twentyOpacityAction.getId());

		final OpacityFourtyAction fourtyOpacityAction = new OpacityFourtyAction(this, model);
		registry.registerAction(fourtyOpacityAction);
		getSelectionActions().add(fourtyOpacityAction.getId());

		final OpacitySixtyAction sixyOpacityAction = new OpacitySixtyAction(this, model);
		registry.registerAction(sixyOpacityAction);
		getSelectionActions().add(sixyOpacityAction.getId());

		final OpacityEightyAction eightyOpacityAction = new OpacityEightyAction(this, model);
		registry.registerAction(eightyOpacityAction);
		getSelectionActions().add(eightyOpacityAction.getId());

		final OpacityHundredAction hundredOpacityAction = new OpacityHundredAction(this, model);
		registry.registerAction(hundredOpacityAction);
		getSelectionActions().add(hundredOpacityAction.getId());

		// Label Color
		final LabelColorAction labelColorAction = new LabelColorAction(this, model);
		registry.registerAction(labelColorAction);
		getSelectionActions().add(labelColorAction.getId());

		// Font Color
		final FontColorAction fontColorAction = new FontColorAction(this, model);
		registry.registerAction(fontColorAction);
		getSelectionActions().add(fontColorAction.getId());

		// Font Style
		final FontStyleNormalAction normalFontStyleAction = new FontStyleNormalAction(this, model);
		registry.registerAction(normalFontStyleAction);
		getSelectionActions().add(normalFontStyleAction.getId());

		final FontStyleBoldAction boldFontStyleAction = new FontStyleBoldAction(this, model);
		registry.registerAction(boldFontStyleAction);
		getSelectionActions().add(boldFontStyleAction.getId());

		final FontStyleItalicAction italicFontStyleAction = new FontStyleItalicAction(this, model);
		registry.registerAction(italicFontStyleAction);
		getSelectionActions().add(italicFontStyleAction.getId());

		final FontStyleBoldItalicAction boldItalicFontStyleAction = new FontStyleBoldItalicAction(this, model);
		registry.registerAction(boldItalicFontStyleAction);
		getSelectionActions().add(boldItalicFontStyleAction.getId());

		// Font Size
		final FontSizeSevenAction sevenFontSizeAction = new FontSizeSevenAction(this, model);
		registry.registerAction(sevenFontSizeAction);
		getSelectionActions().add(sevenFontSizeAction.getId());

		final FontSizeNineAction nineFontSizeAction = new FontSizeNineAction(this, model);
		registry.registerAction(nineFontSizeAction);
		getSelectionActions().add(nineFontSizeAction.getId());

		final FontSizeTenAction tenFontSizeAction = new FontSizeTenAction(this, model);
		registry.registerAction(tenFontSizeAction);
		getSelectionActions().add(tenFontSizeAction.getId());

		final FontSizeThirteenAction thirteenFontSizeAction = new FontSizeThirteenAction(this, model);
		registry.registerAction(thirteenFontSizeAction);
		getSelectionActions().add(thirteenFontSizeAction.getId());

		final FontSizeSixteenAction sixteenFontSizeAction = new FontSizeSixteenAction(this, model);
		registry.registerAction(sixteenFontSizeAction);
		getSelectionActions().add(sixteenFontSizeAction.getId());

		final FontSizeTwentyAction twentyFontSizeAction = new FontSizeTwentyAction(this, model);
		registry.registerAction(twentyFontSizeAction);
		getSelectionActions().add(twentyFontSizeAction.getId());

		final FontSizeTwentyfiveAction twentyfiveFontSizeAction = new FontSizeTwentyfiveAction(this, model);
		registry.registerAction(twentyfiveFontSizeAction);
		getSelectionActions().add(twentyfiveFontSizeAction.getId());

		// Default Figure Color
		final DefaultFigureColorAction defaultFigureColorAction = new DefaultFigureColorAction(this, model);
		registry.registerAction(defaultFigureColorAction);
		getSelectionActions().add(defaultFigureColorAction.getId());

		// Default Opacity
		final DefaultOpacityAction defaultOpacityAction = new DefaultOpacityAction(this, model);
		registry.registerAction(defaultOpacityAction);
		getSelectionActions().add(defaultOpacityAction.getId());

		// Default Label Color
		final DefaultLabelColorAction defaultLabelColorAction = new DefaultLabelColorAction(this, model);
		registry.registerAction(defaultLabelColorAction);
		getSelectionActions().add(defaultLabelColorAction.getId());

		// Default Font Color
		final DefaultFontColorAction defaultFontColorAction = new DefaultFontColorAction(this, model);
		registry.registerAction(defaultFontColorAction);
		getSelectionActions().add(defaultFontColorAction.getId());

		// Default Font Size
		final DefaultFontSizeAction defaultFontSizeAction = new DefaultFontSizeAction(this, model);
		registry.registerAction(defaultFontSizeAction);
		getSelectionActions().add(defaultFontSizeAction.getId());

		// Default Font Style
		final DefaultFontStyleAction defaultFontStyleAction = new DefaultFontStyleAction(this, model);
		registry.registerAction(defaultFontStyleAction);
		getSelectionActions().add(defaultFontStyleAction.getId());

	}


	// ==================== 6. Action Methods ====================

	@Override
	protected void initializeGraphicalViewer() {
		final GraphicalViewer viewer = getGraphicalViewer();


		viewer.setContents(model);
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(viewer));


	}

	@Override
	protected void initializePaletteViewer() {
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	@Override
	protected void configureGraphicalViewer() {
		final double[] zoomLevels;
		final ArrayList<String> zoomContributions;

		super.configureGraphicalViewer();

		final GraphicalViewer viewer = getGraphicalViewer(); 

		viewer.setEditPartFactory(new PartEditFactory());

		final ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);

		// Zooming START
		final ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25, 2.5 };
		manager.setZoomLevels(zoomLevels);
		zoomContributions = new ArrayList<String>();
		//		zoomContributions.add(ZoomManager.FIT_ALL); 
		//		zoomContributions.add(ZoomManager.FIT_HEIGHT); 
		//		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);
		//Zooming END

		//Keyboard shortcuts for delete, undo, redo & zoom (mouse scroll also works with zooming now)
		final KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(
				KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));

		//		keyHandler.put(
		//				KeyStroke.getPressed(SWT.CTRL+'Z', 0),
		//				getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		//
		//		keyHandler.put(
		//				KeyStroke.getPressed(SWT.CTRL+'Y', 0),
		//				getActionRegistry().getAction(ActionFactory.REDO.getId()));

		keyHandler.put(
				KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));

		keyHandler.put(
				KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		viewer.setProperty(
				MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);

		viewer.setKeyHandler(keyHandler);


		final ContextMenuProvider provider = new AppContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
	}

	@Override
	public void doSave(final IProgressMonitor monitor) {

	}


	// ==================== 7. Getters & Setters ====================

	@Override
	protected PaletteRoot getPaletteRoot() {
		final PaletteRoot root = new PaletteRoot();
		final PaletteGroup manipGroup = new PaletteGroup("Manipulating the objects");
		root.add(manipGroup);
		final SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());

		final PaletteSeparator sep2 = new PaletteSeparator();
		root.add(sep2);
		final PaletteGroup instGroup = new PaletteGroup("Element Creation");
		root.add(instGroup);

		// Create the Polygon creation tool
		final CreationToolEntry drawEntry = new CreationToolEntry("Draw",
				"Create Polygon object",
				new PollyLineCreationFactory(), 
				FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_DRAW_16), 
				FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_DRAW_24)); 

		instGroup.add(drawEntry);

		// Create the Rectangle creation tool
		final CreationToolEntry creationEntry = new CreationToolEntry("Rectangle",
				"Create Rectangle object",
				new RectangleCreationFactory(), 
				FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_RECT_16), 
				FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_RECT_24)); 

		instGroup.add(creationEntry);

		root.setDefaultEntry(drawEntry);
		return root;
	}

	// For zooming
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(final Class type) {
		if (type == ZoomManager.class)
			return ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager();
		else
			return super.getAdapter(type);
	}

	public CanvasModel getModel() {
		return model;
	}

}