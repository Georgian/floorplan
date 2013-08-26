package com.ansis.floorplan.core.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.Polly;


public class ModelTestCreationFactory implements CreationFactory {

	// ====================== 2. Instance Fields =============================

	private Class<?> template;


	// ==================== 4. Constructors ====================

	public ModelTestCreationFactory(final Class<?> t) {
		this.template = t;
	}


	// ==================== 7. Getters & Setters ====================

	@Override
	public Object getNewObject() {
		if (template == null)
			return null;

		if (template == Polly.class) {
			final Polly srv = new Polly();
			return srv;
		}

		if (template == Canvas.class) {
			final Canvas srv = new Canvas();
			return srv;
		}

		return null;
	}

	@Override
	public Object getObjectType() {
		return template;
	}

}