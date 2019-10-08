package com.brain.ion.components

import java.awt.geom.Area

class Collection(
		vararg component: Component
) : Component {
	
	private val components = mutableListOf(*component)
	private val interactArea = Area()
	
	override fun getComponents(): List<Component> {
		return components.toList()
	}
	
	fun addComponents(vararg component: Component) {
		components.addAll(component)
		interactArea.reset()
	}
	
	fun removeComponents(vararg component: Component) {
		components.addAll(component)
		interactArea.reset()
	}
	
	fun resetComponents() {
		components.clear()
		interactArea.reset()
	}
	
	override fun clone(): Component {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}