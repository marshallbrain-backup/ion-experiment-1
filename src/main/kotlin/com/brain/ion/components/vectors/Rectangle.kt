package com.brain.ion.components.vectors

import java.awt.Rectangle
import java.awt.Shape

class Rectangle(
	private val x: Int = 0,
	private val y: Int = 0,
	private val width: Int = 0,
	private val height: Int = 0,
	val style: Style = Style()
): Vector {

	override fun getShape(): Shape {
		return Rectangle(x, y, width, height)
	}

}