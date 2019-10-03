package com.brain.ion.components.vectors

data class Rectangle(
	private val x: Int,
	private val y: Int,
	private val width: Int,
	private val height: Int,
	override val style: Style = Style()
): Vector {
	
	constructor(width: Int, height: Int) :
			this(0, 0, width, height)
	
	constructor(width: Int, height: Int, style: Style) :
			this(0, 0, width, height, style)
	
	constructor(v: Rectangle) :
			this(v.x, v.y, v.width, v.height, Style(v.style))
	
	override val shape = java.awt.Rectangle(x, y, width, height)
	
	override fun clone(): Vector {
		return Rectangle(this)
	}

}