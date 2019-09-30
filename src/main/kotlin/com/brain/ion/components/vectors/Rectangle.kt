package com.brain.ion.components.vectors

import java.awt.Rectangle
import java.awt.Shape

class Rectangle(
	private val x: Int = 0,
	private val y: Int = 0,
	private val width: Int = 0,
	private val height: Int = 0,
	override val style: Style = Style()
): Vector {

	val shape = Rectangle(x, y, width, height)

	override fun getShape(): Shape {
		return shape
	}

}