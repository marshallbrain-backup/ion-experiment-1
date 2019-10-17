package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.properties.RenderableCollection

data class Group (
		val id: String
) {
	
	private val queue = mutableListOf<RenderableCollection>()
	
	fun addToQueue(vararg component: RenderableCollection) {
		queue.addAll(component)
	}
	
	fun removeFromQueue(vararg component: RenderableCollection) {
		queue.removeAll(component)
	}
	
	fun render(g: IonGraphics) {
		for (v in queue) {
			g.draw(v)
		}
	}
	
}