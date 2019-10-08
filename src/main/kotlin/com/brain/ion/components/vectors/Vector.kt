package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape
import java.awt.geom.Area

interface Vector : Component {
	
	val style: Style
	val shape: Shape
	
	override fun getInteractArea(): Area {
		return Area(shape)
	override fun getComponents(): List<Component> {
		return listOf(this)
	}
	
}