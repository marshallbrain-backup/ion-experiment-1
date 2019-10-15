package com.brain.ion.components

import com.brain.ion.components.properties.Property
import java.awt.geom.Area

class Collection(
		override val id: String,
		vararg component: Component
) : Component {
	
	override val properties = mutableListOf<Property>()
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
	override fun clone(properties: Map<String, () -> Any>): Component {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}