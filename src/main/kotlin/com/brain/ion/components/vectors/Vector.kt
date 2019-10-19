package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Shape

interface Vector : Component{
	
	var style: Style
	
	fun getShape(g: IonGraphics): Shape
	
	override fun getCollection(): List<Component> {
		TODO("not implemented")
	}
	
}