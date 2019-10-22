package com.brain.ion.components

import com.brain.ion.components.properties.Property
import java.awt.Point
import java.awt.geom.Area

//TODO text
//TODO animations
interface Component {
	
	val id: String
	val properties: MutableList<Property>
	
	fun clone(): Component
	
	fun getComponents(): List<Component> {
		return listOf()
	}
	fun clone(properties: Map<String, () -> Any>): Component

}