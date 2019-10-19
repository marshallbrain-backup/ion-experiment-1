package com.brain.ion.graphics

import com.brain.ion.components.Component

/**
 * The queue that the [IonGraphics] uses to draw [Components][Component] to the screen
 *
 * @since 0.1
 */
class RenderQueue {
	
	private val componentMap = mutableMapOf<String, Component>()
	
	/**
	 * Copies all [Components][Component] that are in the queue.
	 *
	 * @return The list of cloned [Components][Component]
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
	 * Adds a [Component] to the queue
	 *
	 * @since 0.1
	 */
	fun addComponent(component: Component): Boolean {
		val o = componentMap.putIfAbsent(component.id, component)
		return o == null
	}
	
	/**
	 * Removes a [Component] from the queue.
	 *
	 * @param id The id of the [Component] to remove.
	 * @return Whether the [Component] was removed or not.
	 * @since 0.1
	 */
	fun removeComponent(id: String): Boolean {
		val g = componentMap[id] ?: return false
		return removeComponent(g)
	}
	
	/**
	 * Removes a component from the queue.
	 *
	 * @param component The [Component] object to remove.
	 * @return Whether the [Component] was removed or not.
	 * @see removeComponent
	 * @since 0.1
	 */
	fun removeComponent(component: Component): Boolean {
		return componentMap.remove(component.id, component)
	}
	
	/**
	 * Renders the queue to the screen
	 *
	 * @param graphics The [IonGraphics] instance to use for rendering
	 * @since 0.1
	 */
	fun render(graphics: IonGraphics) {
		for ((_, c) in componentMap)
			graphics.draw(c)
	}
	
}