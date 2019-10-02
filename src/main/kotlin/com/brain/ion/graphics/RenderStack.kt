package com.brain.ion.graphics

import com.brain.ion.components.vectors.Vector

class RenderStack {
	
	private val groups = mutableMapOf<String, Group>()
	
	fun getGroupsCopy() : Map<String, Group> {
		
		val groupsCopy = mutableMapOf<String, Group>()
		
		for ((id, g) in groups) {
			groupsCopy[id] = Group(g)
		}
		
		return groupsCopy.toMap()
	}
	
	fun createRenderGroup(id: String): Boolean {
		var group = groups.putIfAbsent(id, Group(id))
		return group == null
	}
	
	fun removeRenderGroup(id: String) {
		groups.remove(id)
	}
	
	fun containsGroup(id: String) : Boolean {
		return groups.containsKey(id)
	}
	
	fun addToQueue(id: String, vararg vectors: Vector) {
		groups[id]?.addToQueue(vectors)
	}
	
	fun removeFromQueue(id: String, vararg vectors: Vector) {
		groups[id]?.removeFromQueue(vectors)
	}
	
	fun render(graphics: IonGraphics) {
		for ((i, g) in groups)
			g.render(graphics)
	}
	
}