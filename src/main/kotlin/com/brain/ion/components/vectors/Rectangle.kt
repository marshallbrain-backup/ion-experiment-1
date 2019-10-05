package com.brain.ion.components.vectors

import java.awt.geom.Rectangle2D

data class Rectangle(
	private val x: Number,
	private val y: Number,
	private val width: Number,
	private val height: Number,
	override val style: Style = Style(),
	override var onClick: () -> Unit = {}
): Vector {
	
	constructor(width: Number, height: Number) :
			this(0f, 0f, width, height)
	
	constructor(width: Number, height: Number, style: Style) :
			this(0f, 0f, width, height, style)
	
	constructor(v: Rectangle) :
			this(v.x, v.y, v.width, v.height, Style(v.style))
	
	override val shape = Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
	
	override fun clone(): Vector {
		return Rectangle(this)
	}

}