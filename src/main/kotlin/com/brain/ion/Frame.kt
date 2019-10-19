package com.brain.ion

import java.awt.Canvas
import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame

/**
 * A [JFrame] manager
 * @since 0.1
 *
 * @constructor Initializes a new [JFrame]
 * @param x
 * @param y
 * @param width
 * @param height
 * @param name The name of the [JFrame]. Defaults to Ion
 * @since 0.1
 */
class Frame(
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		name: String = "Ion",
		decorated: Boolean = true
) {
	
	/**
	 * The [JFrame] that this manages
	 *
	 * @since 0.1
	 */
	val frame = JFrame()
	/**
	 * The [Canvas] that is created for the [JFrame]
	 *
	 * @since 0.1
	 */
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