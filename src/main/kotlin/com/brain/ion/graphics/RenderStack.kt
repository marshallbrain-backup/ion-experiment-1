package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.properties.RenderableCollection

class RenderStack {
	
	private val groups = mutableMapOf<String, Group>()
	
	fun addGroup(group: Group): Boolean {
		val o = groups.putIfAbsent(group.id, group)
		return o == null
	}
	
	fun removeRenderGroup(id: String): Boolean {
		val g = groups[id] ?: return false
		return removeRenderGroup(g)
	}
	
	fun removeRenderGroup(group: Group): Boolean {
		return groups.remove(group.id, group)
	}
	
	fun addToQueue(id: String, vararg components: RenderableCollection) {
		groups[id]?.addToQueue(*components)
	}
	
	fun removeFromQueue(id: String, vararg components: RenderableCollection) {
		groups[id]?.removeFromQueue(*components)
	}
	
	fun render(graphics: IonGraphics) {
		for ((i, g) in groups)
			g.render(graphics)
	}
	
}