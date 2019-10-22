package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.components.properties.Property
import java.awt.geom.Ellipse2D

data class Circle(
		override val id: String,
		private val x: Number,
		private val y: Number,
		private val radius: Number,
		override val style: Style = Style()
): Vector {
	
	override val properties = mutableListOf<Property>()
	override val shape = Ellipse2D.Double(x.toDouble(), y.toDouble(), radius.toDouble(), radius.toDouble())
	
	constructor(id: String, radius: Number) :
			this(id,0, 0, radius)
	
	constructor(id: String, radius: Number, style: Style) :
			this(id,0, 0, radius, style)
	
	constructor(v: Circle) :
			this(v.id, v.x, v.y, v.radius, Style(v.style))
	
	override fun clone(): Vector {
		return Circle(this)
	}
	
	override fun clone(properties: Map<String, () -> Any>): Component {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}