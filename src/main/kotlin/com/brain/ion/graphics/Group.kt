package com.brain.ion.graphics

import com.brain.ion.components.Component

data class Group(
		val id: String
) {
	
	private val queue = mutableListOf<Component>()
	
	constructor(parent: Group) : this(parent.id) {
		queue.addAll(getQueueCopy())
	}
	
	fun getQueueCopy(): List<Component> {
		
		val queueCopy = mutableListOf<Component>()
		
		for (c in queue) {
			queueCopy.add(c.clone())
		}
		
		return queueCopy.toList()
	}
	
	fun addToQueue(vararg component: Component) {
		queue.addAll(component)
	}
	
	fun removeFromQueue(vararg component: Component) {
		queue.removeAll(component)
	}
	
	fun render(g: IonGraphics) {
		for (v in queue) {
			g.draw(v)
		}
	}
	
}