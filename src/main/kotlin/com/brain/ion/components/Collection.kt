package com.brain.ion.components

import java.awt.geom.Area

/**
 * A collection of [Components][Component]
 *
 * @since 0.1
 *
 * @constructor Creates a new collection of [Components][Component]
 * @param component The list of components that should be initially added to the collection
 */
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
	
	/**
	 * Adds [Components][Component] to the collection
	 *
	 * @param component The [Components][Component] to add
	 * @since 0.1
	 */
	fun addComponents(vararg component: Component) {
		components.addAll(component)
		interactArea.reset()
	}
	
	/**
	 * Removes [Components][Component] from the collection
	 *
	 * @param component The [Components][Component] to remove
	 * @since 0.1
	 */
	fun removeComponents(vararg component: Component) {
		components.addAll(component)
		interactArea.reset()
	}
	
	/**
	 * Removes all [Components][Component] form the collection
	 *
	 * @since 0.1
	 */
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