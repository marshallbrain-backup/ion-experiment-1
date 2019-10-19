package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import java.awt.*

/**
 * Extension of [Graphics2D] that simplifies the drawing of [Components][Component]
 *
 * @since 0.1
 *
 * @constructor
 * @param bounds The bounds of the [Frame]. Used to set the clip for the [Graphics2D] object
 * @since 0.1
 */
class IonGraphics(
		private val bounds: Rectangle
) {
	
	/**
	 * The queue that is used when rendering [Components][Component] to the screen.
	 *
	 * @since 0.1
	 */
	val renderQueue = RenderQueue()
	
	private lateinit var graphics: Graphics2D
	
	/**
	 * Sets the [Graphics2D] that should be used to draw [Components][Component] to the screen
	 *
	 * @param g the [Graphics2D] instance to be set
	 * @since 0.1
	 */
	fun setGraphics(g: Graphics2D) {
		graphics = g
		
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
	}
	
	/**
	 * Converts a [Graphics] to [Graphics2D] and calls [setGraphics]
	 *
	 * @see setGraphics
	 * @since 0.1
	 */
	fun setGraphics(g: Graphics) {
		setGraphics(g as Graphics2D)
	}
	
	/**
	 * Draws a [Component] using the given [Graphics2D] instance
	 *
	 * @param component The [Component] to draw
	 * @since 0.1
	 */
	fun draw(component: Component) {
		
		for (c in component.getComponents()) {
			if (c is Vector) draw(c)
			else draw(c)
		}
		
	}
	
	/**
	 * Draws a [Vector] using the given [Graphics2D] instance
	 *
	 * @param vector The [Vector] to draw
	 * @since 0.1
	 */
	fun draw(vector: Vector) {
		
		graphics.color = vector.style.fillColor
		graphics.fill(vector.shape)
		
		graphics.color = vector.style.strokeColor
		graphics.stroke = vector.style.strokeProp
		graphics.draw(vector.shape)
		
	}
	
	/**
	 * Renders whatever is in the [renderQueue]
	 *
	 * @see [renderQueue]
	 * @since 0.1
	 */
	fun render() {
		renderQueue.render(this)
	}
	
}