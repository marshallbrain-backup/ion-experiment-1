package com.brain.ion

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle

class ComGraphics(
	val graphics: Graphics2D,
	bounds: Rectangle
) {
	
	init {
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
	}
	
	constructor(g: Graphics, bounds: Rectangle) : this(g as Graphics2D, bounds)
	
	fun render() {
		TODO("not implemented")
	}
	
}