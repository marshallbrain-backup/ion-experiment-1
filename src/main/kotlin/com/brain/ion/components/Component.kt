package com.brain.ion.components

import java.awt.Point
import java.awt.geom.Area

//TODO text
//TODO animations
interface Component {
	
	fun clone(): Component
	
	fun getComponents(): List<Component> {
		return listOf()
	}

}