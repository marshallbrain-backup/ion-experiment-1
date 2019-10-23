package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Shape
import java.awt.geom.Ellipse2D

class Ellipse(
		override val id: String,
		x: Number,
		y: Number,
		width: Number,
		height: Number,
		override var style: Style = Style()
) : Vector{
	
	constructor(c: Ellipse): this(c.id, c.x, c.y, c.width, c.height, c.style)
	
	var x: Number = x
		set(value) {
			changed = true
			field = value
		}
	var y: Number = y
		set(value) {
			changed = true
			field = value
		}
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
	private var changed = false
	private var shape: Ellipse2D =
			Ellipse2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
	
	override fun getShape(g: IonGraphics): Shape {
		if (onRender != emptyFunction) {
			onRender.invoke(this)
			changed = true
		}
		
		if(changed){
			shape.setFrame(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
		}
		
		return shape
	}
	
	override fun clone(): Component {
		return Ellipse(this)
	}
	
}