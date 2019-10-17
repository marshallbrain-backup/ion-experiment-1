package com.brain.ion.components.vectors

import com.brain.ion.properties.Style
import java.awt.Shape
import java.awt.geom.Rectangle2D

class Rectangle(
		var x: Number,
		var y: Number,
		var width: Number,
		var height: Number,
		override val id: String,
		override var style: Style = Style()
) : Vector{
	
	override var shape: Shape = Rectangle2D.Double(
			x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble()
	)
	
	override fun clone() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}