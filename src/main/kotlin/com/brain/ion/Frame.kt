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
	
	private val mainFrame = JFrame();
	private val canvas = Canvas()
	
	init {
		
		mainFrame.title = "Pulsar";
		mainFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
		mainFrame.isResizable = false;
		mainFrame.isVisible = true;
		mainFrame.location = Point(x, y)
		
		canvas.preferredSize = Dimension(width, height)
		mainFrame.add(canvas)
		
		mainFrame.pack();
		
	}
	
}