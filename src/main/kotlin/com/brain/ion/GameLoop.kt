package com.brain.ion

import com.brain.ion.components.vectors.Rectangle
import com.brain.ion.components.vectors.Style
import com.brain.ion.graphics.Group
import com.brain.ion.graphics.IonGraphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.WindowEvent
import java.awt.image.BufferStrategy

class GameLoop(private val mainFrame: Frame, minorFrames: MutableList<Frame>): Runnable {
	
	private val canvas = mainFrame.canvas
	private val mainThread = Thread(this)
	private val bs: BufferStrategy
	val graphics = IonGraphics(canvas.bounds)
	
	private var running = false

	init {
		
		canvas.createBufferStrategy(2)
		
		bs = canvas.bufferStrategy
		graphics.setGraphics(bs.drawGraphics)
		
	}
	
	@Synchronized
	fun start() {
		
		if (running) {
			return
		}
		
		running = true
		
		mainThread.start()
		
	}
	
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
	
	private fun tick() {
		
		canvas.keyListeners
		canvas.mouseListeners
		canvas.mouseMotionListeners
		canvas.mousePosition
		canvas.mouseWheelListeners
		
	}
	
	private fun render() {
		
		graphics.setGraphics(bs.drawGraphics)
		
		graphics.render()
		
		bs.show()
		
	}
	
	private fun displayUpdateInfo(tps: Int, fps: Int) {
		
		print("TPS - $tps; FPS - $fps\r")
		
	}
	
}
