package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Shape
import java.awt.geom.Rectangle2D

class Rectangle(
		override val id: String,
		var x: Number,
		var y: Number,
		var width: Number,
		var height: Number,
		override var style: Style = Style()
) : Vector{
	
	override var onRender: (Component) -> Unit = emptyFunction
	
	private var shape: Rectangle2D =
			Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
	
	override fun getShape(g: IonGraphics): Shape {
		if (onRender != emptyFunction) {
			onRender.invoke(this)
		}
		
		shape.setFrame(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
		
		return shape
	}
	
	override fun clone() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}