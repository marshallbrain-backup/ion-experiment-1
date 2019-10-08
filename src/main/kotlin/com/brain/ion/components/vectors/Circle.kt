package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.geom.Ellipse2D

data class Circle(
		private val x: Number,
		private val y: Number,
		private val radius: Number,
		override val style: Style = Style()
): Vector {
	
	override val shape = Ellipse2D.Double(x.toDouble(), y.toDouble(), radius.toDouble(), radius.toDouble())
	
	constructor(radius: Number) :
			this(0, 0, radius)
	
	constructor(radius: Number, style: Style) :
			this(0, 0, radius, style)
	
	constructor(v: Circle) :
			this(v.x, v.y, v.radius, Style(v.style))
	
	override fun clone(): Vector {
		return Circle(this)
	}
	
}