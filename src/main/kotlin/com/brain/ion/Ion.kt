package com.brain.ion

import com.brain.ion.handler.EventHandler
import com.brain.ion.handler.MouseHandler

class Ion (
		name: String = "ion",
		x: Int = 50,
		y: Int = 50,
		width: Int = 1280,
		height: Int = 720
) {
	
	private val mainFrame = Frame(name, x, y, width, height)
	private val minorFrames = mutableListOf<Frame>()
	private val gameLoop = GameLoop(mainFrame, minorFrames)
	
	val ionGraphics = gameLoop.graphics
	val eventHandler = EventHandler()
	
	private val mouse = MouseHandler(ionGraphics.renderQueue)
	
	init {
		
		mainFrame.canvas.addMouseListener(mouse)
		
		gameLoop.start()
		
	}
	
}