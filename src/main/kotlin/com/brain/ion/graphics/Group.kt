package com.brain.ion.graphics

import com.brain.ion.components.vectors.Vector

data class Group (
		val id: String
) {
	
	private val queue = mutableListOf<Vector>()
	
	constructor(parent: Group) : this(parent.id) {
		queue.addAll(getQueueCopy())
	}
	
	fun getQueueCopy() : List<Vector> {
		
		val queueCopy = mutableListOf<Vector>()
		
		for (v in queue) {
			queueCopy.add(v.clone())
		}
		
		return queueCopy.toList()
	}
	
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