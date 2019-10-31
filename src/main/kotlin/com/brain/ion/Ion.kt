package com.brain.ion

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import com.brain.ion.graphics.IonGraphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.geom.Area
import java.awt.geom.Point2D

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
	private val mouse = Mouse()
	
	val ionGraphics = gameLoop.graphics
	
	init {
		
		mainFrame.canvas.addMouseListener(mouse)
		
		gameLoop.start()
		
	}
	
	fun registerMouseEvent(area: Vector, function: (Point2D) -> Unit){
		mouse.addCall(MouseCall(Area(area.getShape()), function))
	}
	
	private data class MouseCall(val area: Area, val function: (Point2D) -> Unit)
	
	private class Mouse() : MouseListener {
		
		val mouseCallList = mutableListOf<MouseCall>()
		
		fun addCall(mouseCall: MouseCall) {
			mouseCallList.add(mouseCall)
		}
		
		override fun mouseClicked(e: MouseEvent) {
			for (m in mouseCallList) {
				if (m.area.contains(e.point)) {
					m.function.invoke(e.point)
				}
			}
		}
		
		override fun mousePressed(p0: MouseEvent?) {
		}
		
		override fun mouseReleased(p0: MouseEvent?) {
		}
		
		override fun mouseEntered(p0: MouseEvent?) {
		}
		
		override fun mouseExited(p0: MouseEvent?) {
		}
		
	}

}