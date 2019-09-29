package com.brain.ion

import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.WindowEvent

class GameLoop(private val mainFrame: Frame, minorFrames: MutableList<Frame>): Runnable {
	
	private val canvas = mainFrame.canvas
	private val mainThread = Thread(this)
	
	private var running = false

	init {
		
		canvas.createBufferStrategy(2)
		
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
		
		val bs = canvas.bufferStrategy
		val g = bs.drawGraphics as Graphics2D
		
		val rec = canvas.bounds
		
		g.color = Color.DARK_GRAY
		g.setClip(rec.x, rec.y, rec.width, rec.height)
		g.fillRect(rec.x, rec.y, rec.width, rec.height)
		
		g.dispose();
		bs.show();
		
	}
	
	private fun displayUpdateInfo(tps: Int, fps: Int) {
		
		print("TPS - $tps; FPS - $fps\r")
		
	}
	
}