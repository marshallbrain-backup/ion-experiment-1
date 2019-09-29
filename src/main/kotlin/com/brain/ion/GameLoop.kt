package com.brain.ion

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
		
		val TARGET_FPS: Long = 60
		val TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS
		val GAME_HERTZ: Long = 60
		val TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ
		
		var tps = 0
		var fps = 0
		var lastSecond = System.nanoTime()
		var lastUpdateTime = lastSecond
		var lastRenderTime: Long
		
		while (running) {
			
			var now = System.nanoTime()
			
			while (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				
				//tickClass.tick()
				tps++
				lastUpdateTime += TIME_BETWEEN_UPDATES
				
			}
			
			//renderClass.render()
			fps++
			lastRenderTime = now
			
			// Is called once a second
			if (lastUpdateTime / 1000000 - lastSecond / 1000000 >= 1000) {
				
				if (tps < 60 || fps < 60) {
					
					println("TPS - $tps")
					println("FPS - $fps")
					println()
					
				}
				
				tps = 0
				fps = 0
				lastSecond = lastUpdateTime
				
			}
			
			// Idles until a frame or game tick needs to happen
			while (now - lastRenderTime < TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
				
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

}