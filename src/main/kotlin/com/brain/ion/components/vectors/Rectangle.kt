package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Shape
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D

class Rectangle(
		override val id: String,
		x: Number = 0,
		y: Number = 0,
		width: Number,
		height: Number,
		override var style: Style = Style()
) : Vector{
	
	constructor(c: Rectangle): this(c.id, c.x, c.y, c.width, c.height, c.style)
	
	override var x: Number = x
	override var y: Number = y
	var width: Number = width
		set(value) {
			changed = true
			field = value
		}
	var height: Number = height
		set(value) {
			changed = true
			field = value
		}
	
	override var onRender: (Component) -> Unit = emptyFunction
	override var onClick: (Point2D) -> Unit = {}
	private var changed = false
	private var shape: Rectangle2D =
			Rectangle2D.Double(0.0, 0.0, width.toDouble(), height.toDouble())
	
	override fun getShape(): Shape {
		if (onRender != emptyFunction) {
			onRender.invoke(this)
			changed = true
		}
		
		if(changed){
			shape.setFrame(0.0, 0.0, width.toDouble(), height.toDouble())
		}
		
		return shape
	}
	
	override fun clone(): Component {
		return Rectangle(this)
	}
	
}