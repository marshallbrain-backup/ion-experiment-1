package com.brain.ion

import com.brain.ion.components.vectors.Vector
import java.awt.*

class ComGraphics(
		private val graphics: Graphics2D,
		bounds: Rectangle
) {

	val renderQueues = mutableListOf<Queue>()
	
	init {
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
	}
	
	constructor(g: Graphics, bounds: Rectangle) : this(g as Graphics2D, bounds)

	fun draw(vector: Vector) {

		graphics.color = vector.style.fillColor
		graphics.fill(vector.getShape())

		graphics.color = vector.style.StrokeColor
		graphics.stroke = vector.style.StrokeProp
		graphics.draw(vector.getShape())

	}

	fun createRenderGroup() {}

	fun deleteRenderGroup() {}

	fun getRenderGroup() {}

	fun addToQueue() {}

	fun removeFromQueue() {}

	fun render() {}


}

class Queue {}