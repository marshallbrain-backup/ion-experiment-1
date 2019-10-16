package com.brain.ion.graphics

import com.brain.ion.components.Component

data class Group (
		val id: String
) {
	
	private val queue = mutableListOf<Component>()
	
	fun addToQueue(vararg component: Component) {
		queue.addAll(component)
	}
	
	fun removeFromQueue(vararg component: Component) {
		queue.removeAll(component)
	}
	
	fun render(g: IonGraphics) {
//		for (v in queue) {
//			g.draw(v)
//		}
	}
	
}