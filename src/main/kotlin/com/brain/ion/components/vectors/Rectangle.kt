package com.brain.ion.components.vectors

class Rectangle(
	private val x: Int = 0,
	private val y: Int = 0,
	private val width: Int,
	private val height: Int,
	override val style: Style = Style()
): Vector {
	
	constructor(v: Rectangle) :
			this(v.x, v.y, v.width, v.height, Style(v.style))
	
	override val shape = java.awt.Rectangle(x, y, width, height)
	
	override fun clone(): Vector {
		return Rectangle(this)
	}

}