package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import com.brain.ion.properties.Renderable
import com.brain.ion.properties.RenderableCollection
import java.awt.*

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
	
	fun draw(component: RenderableCollection) {
		
		for (c in component.getComponents()) {
			when(c) {
				is Renderable -> draw(c)
				is RenderableCollection -> draw(c)
			}
		}
		
	}
	
	private fun draw(vector: Renderable, x: Number = 0, y: Number = 0) {
		
		val g = graphics.create() as Graphics2D
		g.translate(x.toInt(), y.toInt())

		g.color = vector.style.fillColor
		g.fill(vector.getShape(this))

		g.color = vector.style.strokeColor
		g.stroke = vector.style.strokeProp

		if(vector.style.strokeProp.lineWidth % 2 == 0f) {
			g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		}
		g.draw(vector.getShape(this))
		
	}
	
	fun render() {
		renderStack.render(this)
	}

}