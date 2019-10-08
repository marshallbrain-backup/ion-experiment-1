package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import java.awt.*
import java.awt.RenderingHints

class IonGraphics(
		private val bounds: Rectangle
) {
	
	val renderStack = RenderStack()
	
	private lateinit var graphics: Graphics2D
	
	fun setGraphics(g: Graphics) {
		graphics = g.create() as Graphics2D
		
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
	}
	
	fun draw(component: Component) {
		
		for (c in component.getComponents()) {
			if (c is Vector) {
				draw(c)
			} else {
				draw(c)
			}
		}
		
	}
	
	fun draw(vector: Vector) {
		
		val g = graphics.create() as Graphics2D
		
		g.color = vector.style.fillColor
		g.fill(vector.shape)
		
		g.color = vector.style.strokeColor
		g.stroke = vector.style.strokeProp
		
		if(vector.style.strokeProp.lineWidth % 2 == 0f) {
			g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		}
		g.draw(vector.shape)
		
	}
	
	fun render() {
		renderStack.render(this)
	}

}