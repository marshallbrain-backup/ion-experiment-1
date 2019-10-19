package com.brain.ion.graphics

import com.brain.ion.components.Component

/**
 *
 *
 * @since 0.1
 */
class RenderQueue {
	
	private val componentMap = mutableMapOf<String, Component>()
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun getComponentCopy(): Map<String, Component> {
		
		val groupsCopy = mutableMapOf<String, Component>()
		
		for ((id, c) in componentMap) {
			groupsCopy[id] = c.clone()
		}
		
		return groupsCopy.toMap()
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun addComponent(component: Component): Boolean {
		val o = componentMap.putIfAbsent(component.id, component)
		return o == null
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun removeComponent(id: String): Boolean {
		val g = componentMap[id] ?: return false
		return removeComponent(g)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun removeComponent(component: Component): Boolean {
		return componentMap.remove(component.id, component)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun render(graphics: IonGraphics) {
		for ((_, c) in componentMap)
			graphics.draw(c)
	}
	
}