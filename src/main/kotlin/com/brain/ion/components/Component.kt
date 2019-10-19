package com.brain.ion.components

import java.awt.Point
import java.awt.geom.Area

interface Component {
	
	val id: String
	var onClick: () -> Unit
	
	fun clone(): Component
	
	fun getComponents(): List<Component> {
		return listOf()
	}
	
	fun clicked(p: Point) {
		if (isClicked(p)) {
			onClick()
		}
	}
	
	fun getInteractArea(): Area {
		return Area()
	}
	
	fun isClicked(p: Point): Boolean {
		if (getInteractArea().contains(p)) {
			return true
		}
		
		return false
	}
	
}