package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.components.properties.Property
import java.awt.geom.Rectangle2D

data class Rectangle(
		override val id: String,
		private val x: Number,
		private val y: Number,
		private val width: Number,
		private val height: Number,
		override val style: Style = Style()
): Vector {
	
	override val properties = mutableListOf<Property>()
	override val shape = Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
	
	constructor(id: String, width: Number, height: Number) :
			this(id,0f, 0f, width, height)
	
	constructor(id: String, width: Number, height: Number, style: Style) :
			this(id,0f, 0f, width, height, style)
	
	constructor(v: Rectangle) :
			this(v.id, v.x, v.y, v.width, v.height, Style(v.style))
	
	override fun clone(): Vector {
		return Rectangle(this)
	}
	
	override fun clone(properties: Map<String, () -> Any>): Component {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}