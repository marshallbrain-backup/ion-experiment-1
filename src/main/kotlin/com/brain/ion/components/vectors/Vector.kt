package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import com.brain.ion.properties.Clickable
import java.awt.Shape
import java.awt.geom.Area

interface Vector : Component, Clickable{
	
	override val area: Area
		get() = Area(getShape())
	
	var style: Style
	
	fun getShape(): Shape
	
	override fun getCollection(g: IonGraphics): List<Component> {
		TODO("not implemented")
	}
	
}