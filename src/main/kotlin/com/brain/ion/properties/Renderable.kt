package com.brain.ion.properties

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Shape

interface Renderable : Styleable, RenderableCollection{
	
	fun getShape(g: IonGraphics): Shape
	
}