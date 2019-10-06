package com.brain.ion

import java.awt.Canvas
import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame

class Frame(
		name: String = "Ion",
		x: Int = 0,
		y: Int = 0,
		width: Int = 0,
		height: Int = 0,
		decorated: Boolean = true
) {
	
	val frame = JFrame()
	val canvas = Canvas()
	
	init {
		
		frame.title = name
		frame.isUndecorated = !decorated
		frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		frame.isResizable = false
		frame.isVisible = true
		frame.location = Point(x, y)
		
		canvas.preferredSize = Dimension(width, height)
		frame.add(canvas)
		
		frame.pack()
		
	}
	
}