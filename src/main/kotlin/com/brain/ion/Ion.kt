package com.brain.ion

class Ion(name: String = "ion") {
	
	private val mainFrame = Frame(name = name, x = 50, y = 50, width = 1600, height = 900)
	private val minorFrames = mutableListOf<Frame>()
	private val gameLoop = GameLoop(mainFrame, minorFrames)
	
	val ionGraphics = gameLoop.graphics
	
	init {
		gameLoop.start()
	}
	
}