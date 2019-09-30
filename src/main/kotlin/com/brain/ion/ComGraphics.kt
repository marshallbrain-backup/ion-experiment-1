package com.brain.ion

import com.brain.ion.components.vectors.Vector
import java.awt.*

class ComGraphics(
		val bounds: Rectangle
) {
	
	private val renderGroups = mutableMapOf<String, Group>()
	
	private lateinit var graphics: Graphics2D
	
	fun setGraphics(g: Graphics2D) {
		graphics = g
		
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
	}
	
	fun setGraphics(g: Graphics) {
		setGraphics(g as Graphics2D)
	}

	fun draw(vector: Vector) {

		graphics.color = vector.style.fillColor
		graphics.fill(vector.shape)

		graphics.color = vector.style.StrokeColor
		graphics.stroke = vector.style.StrokeProp
		graphics.draw(vector.shape)

	}

	fun createRenderGroup() {}

	fun deleteRenderGroup() {}

	fun getRenderGroup() {}

	fun addToQueue() {}

	fun removeFromQueue() {}

	fun render() {}


	class Group (
			val id: String
	) {
		
		private val queue = mutableListOf<Vector>()
		
		fun addToQueue(vectors: Array<out Vector>) {
			queue.addAll(vectors)
		}
		
		fun removeFromQueue(vectors: Array<out Vector>) {
			queue.removeAll(vectors)
		}
		
		fun render(g: ComGraphics) {
			for (v in queue) {
				g.draw(v)
			}
		}
		
	}

}