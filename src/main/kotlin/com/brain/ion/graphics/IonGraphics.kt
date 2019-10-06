package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import java.awt.*


class IonGraphics(
		private val bounds: Rectangle
) {
	
	val renderStack = RenderStack()
	
	private lateinit var graphics: Graphics2D
	
	fun setGraphics(g: Graphics2D) {
		graphics = g
		
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
	}
	
	fun setGraphics(g: Graphics) {
		setGraphics(g as Graphics2D)
	}
	
	fun draw(component: Component) {
		
		for (c in component.getComponents()) {
			if (c is Vector) draw(c)
			else draw(c)
		}
		
	}
	
	fun draw(vector: Vector) {
		
		graphics.color = vector.style.fillColor
		graphics.fill(vector.shape)
		
		graphics.color = vector.style.strokeColor
		graphics.stroke = vector.style.strokeProp
		graphics.draw(vector.shape)
		
	}
	
	fun render() {
		renderStack.render(this)
	}
	
}