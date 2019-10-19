package com.brain.ion

/**
 * The primary class for Ion
 *
 * @since 0.1
 *
 * @constructor Initializes and starts the game loop
 * @param name The name of the main frame
 * @since 0.1
 */
class Ion(name: String = "ion") {
	
	private val mainFrame = Frame(name = name, x = 50, y = 50, width = 1600, height = 900)
	private val gameLoop = GameLoop(mainFrame)
	
	/**
	 * The graphics used to draw to the frames
	 *
	 * @since 0.1
	 */
	val ionGraphics = gameLoop.graphics
	
	init {
		gameLoop.start()
	}
	
}