package com.brain.ion.graphics

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

	fun createRenderGroup(id: String) {
		renderGroups.putIfAbsent(id, Group(id))
	}
	
	fun addRenderGroup(group: Group) {
		renderGroups.putIfAbsent(group.id, group)
	}
	
	fun getRenderGroup(id: String): Group {
		
		var g = renderGroups[id]
		if(g == null) {
			g = Group(id)
			renderGroups[id] = g
		}
		
		return g
		
	}
	
	fun removeRenderGroup(id: String) {
		renderGroups.remove(id)
	}
	
	fun removeRenderGroup(group: Group) {
		renderGroups.remove(group.id)
	}
	
	fun addToQueue(id: String, vararg vectors: Vector) {
		renderGroups[id]?.addToQueue(vectors)
	}
	
	fun removeFromQueue(id: String, vararg vectors: Vector) {
		renderGroups[id]?.removeFromQueue(vectors)
	}
	
	fun render() {
		for ((i, g) in renderGroups)
			g.render(this)
	}

}