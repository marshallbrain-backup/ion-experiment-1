package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.Shape
import java.awt.geom.Ellipse2D
import java.awt.geom.Point2D

class Ellipse(
		override val id: String,
		x: Number,
		y: Number,
		width: Number,
		height: Number,
		override var style: Style = Style(),
		override var visible: Boolean = true
) : Vector{
	
	constructor(c: Ellipse): this(c.id, c.x, c.y, c.width, c.height, c.style)
	
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
	
	override var onRender: (Component) -> Unit = renderEmpty
	override var onClick: (Point2D) -> Unit = clickEmpty
	private var changed = false
	private var shape: Ellipse2D =
			Ellipse2D.Double(0.0, 0.0, width.toDouble(), height.toDouble())
	
	override fun getShape(): Shape {
		if (onRender != renderEmpty) {
			onRender.invoke(this)
			changed = true
		}
		
		if(changed){
			shape.setFrame(0.0, 0.0, width.toDouble(), height.toDouble())
		}
		
		return shape
	}
	
	override fun clone(): Component {
		return Ellipse(this)
	}
	
}