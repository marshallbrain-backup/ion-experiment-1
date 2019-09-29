package com.brain.ion

class Ion (name: String = "ion") {
	
	val mainFrame = Frame(name = name, x = 50, y = 50, width = 1600, height = 900)
	val minorFrames = mutableListOf<Frame>()
	val gameLoop = GameLoop(mainFrame, minorFrames)

}