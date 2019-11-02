package com.brain.ion.handler

import com.brain.ion.graphics.Group
import com.brain.ion.properties.Clickable
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseHandler(private val renderQueue: Group) : MouseListener {
	
	private fun iterateGroup(group: Group, e: MouseEvent) {
		for (c in group) {
			if (c is Group) iterateGroup(c, e)
			else if (c is Clickable && c.onClick != {} && c.area.contains(e.point)) {
				c.onClick.invoke(e.point)
			}
		}
	}
	
	override fun mouseClicked(e: MouseEvent) {
		iterateGroup(renderQueue, e)
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