package com.brain.ion.components

import java.awt.geom.Area

class Collection(
		override val id: String,
		vararg component: Component
) : Component {
	
	private val components = mutableListOf(*component)
	private val interactArea = Area()
	
	override var onClick: () -> Unit = {}
	
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
	
	override fun getInteractArea(): Area {
		if (interactArea.isEmpty) {
			for (c in components) {
				interactArea.add(c.getInteractArea())
			}
		}
		return interactArea
	}
	
	override fun clone(): Component {
		
		val componentsListClone = mutableListOf<Component>()
		
		for (c in components) {
			componentsListClone.add(c.clone())
		}
		
		return Collection(id, *componentsListClone.toTypedArray())
		
	}
	
}