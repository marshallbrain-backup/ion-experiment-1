package com.brain.ion.components

import java.awt.Point
import java.awt.geom.Area

/**
 * The interface used for all components.
 *
 * @since 0.1
 */
interface Component {
	
	/**
	 * The id of the component
	 *
	 * @since 0.1
	 */
	val id: String
	/**
	 * What should happen when the component is clicked.
	 *
	 * @since 0.1
	 */
	var onClick: () -> Unit
	
	/**
	 * Clones the component
	 *
	 * @return The clone
	 * @since 0.1
	 */
	fun clone(): Component
	
	/**
	 * Gets all component that are linked to this component
	 *
	 * @return list of components
	 * @since 0.1
	 */
	fun getComponents(): List<Component> {
		return listOf()
	}
	
	/**
	 * Runs the [onClick] function is the component is clicked
	 *
	 * @since 0.1
	 */
	fun clicked(p: Point) {
		if (isClicked(p)) {
			onClick()
		}
	}
	
	/**
	 * Gets the area that can be interacted with
	 *
	 * @return The intractable area.
	 * @since 0.1
	 */
	fun getInteractArea(): Area {
		return Area()
	}
	
	/**
	 * Checks if the component was click
	 *
	 * @return True if the component was clicked.
	 * @since 0.1
	 */
	fun isClicked(p: Point): Boolean {
		if (getInteractArea().contains(p)) {
			return true
		}
		
		return false
	}
	
}