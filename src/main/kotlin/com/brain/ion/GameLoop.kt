package com.brain.ion

import com.brain.ion.graphics.IonGraphics
import java.awt.event.WindowEvent
import java.awt.image.BufferStrategy

/**
 * Creates a new loop that is limited to 60 ticks a second and 60 frames per second. The loop is ran in a separate
 * thread to prevent the thread used to create the loop from stalling.
 *
 * @since 0.1
 *
 * @constructor Creates but does not start a new instance of the GameLoop class. To start the thread use the [start]
 * function rather then the [run] function as the [run] function will do nothing
 * @param mainFrame The main [Frame] that the program should use to display
 * [Components][com.brain.ion.components.Component] to the screen
 * @since 0.1
 */
class GameLoop(private val mainFrame: Frame) : Runnable {
	
	private val canvas = mainFrame.canvas
	private val mainThread = Thread(this)
	private val bs: BufferStrategy
	/**
	 * The graphics object used to draw to the [mainFrame]
	 *
	 * @since 0.1
	 */
	val graphics = IonGraphics(canvas.bounds)
	
	private var running = false
	
	init {
		
		canvas.createBufferStrategy(2)
		
		bs = canvas.bufferStrategy
		graphics.setGraphics(bs.drawGraphics)
		
	}
	
	/**
	 * Used to start the loop in a new thread
	 *
	 * @since 0.1
	 */
	@Synchronized
	fun start() {
		
		if (running) {
			return
		}
		
		running = true
		
		mainThread.start()
		
	}
	
	/**
	 * Used to stop the loop
	 *
	 * @since 0.1
	 */
	@Synchronized
	fun stop() {
		
		if (!running) {
			mainFrame.frame.dispatchEvent(WindowEvent(mainFrame.frame, WindowEvent.WINDOW_CLOSING))
			return
		}
		
		running = false
		
		mainThread.interrupt()
		
		stop()
		
	}
	
	/**
	 * Do not ues to start the loop as it will lock the rest of the program. Instead use the [start] function inorder
	 * to start the loop as that will start the loop in a separate thread.
	 *
	 * @since 0.1
	 * @suppress
	 */
	override fun run() {
		
		val targetFps: Long = 60 //TARGET_FPS
		val timeBetweenRenders = 1000000000 / targetFps //TIME_BETWEEN_RENDERS
		val gameHertz: Long = 60 //GAME_HERTZ
		val timeBetweenUpdates = 1000000000 / gameHertz //TIME_BETWEEN_UPDATES
		
		var tps = 0
		var fps = 0
		var lastSecond = System.nanoTime()
		var lastUpdateTime = lastSecond
		var lastRenderTime: Long
		
		while (running) {
			
			var now = System.nanoTime()
			
			while (now - lastUpdateTime > timeBetweenUpdates) {
				
				tick()
				tps++
				lastUpdateTime += timeBetweenUpdates
				
			}
			
			render()
			fps++
			lastRenderTime = now
			
			// Is called once a second
			if (lastUpdateTime / 1000000 - lastSecond / 1000000 >= 1000) {
				
				displayUpdateInfo(tps, fps)
				
				tps = 0
				fps = 0
				lastSecond = lastUpdateTime
				
			}
			
			// Idles until a frame or game tick needs to happen
			while (now - lastRenderTime < timeBetweenRenders && now - lastUpdateTime < timeBetweenUpdates) {
				
				Thread.yield()
				try {
					Thread.sleep(1)
				} catch (e: Exception) {
					e.printStackTrace()
				}
				
				now = System.nanoTime()
				
			}
			
		}
		
	}
	
	/**
	 * Called every time the program needs to update.
	 *
	 * @since 0.1
	 */
	private fun tick() {
		
		canvas.keyListeners
		canvas.mouseListeners
		canvas.mouseMotionListeners
		canvas.mousePosition
		canvas.mouseWheelListeners
		
	}
	
	/**
	 * Called every time the program needs to render.
	 *
	 * @since 0.1
	 */
	private fun render() {
		
		graphics.setGraphics(bs.drawGraphics)
		
		graphics.render()
		
		bs.show()
		
	}
	
	/**
	 * Displays the FPS and TPS information for the last second
	 *
	 * @since 0.1
	 */
	private fun displayUpdateInfo(tps: Int, fps: Int) {
		
		print("TPS - $tps; FPS - $fps\r")
		
	}
	
}
