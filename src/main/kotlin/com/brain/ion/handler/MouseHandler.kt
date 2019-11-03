package com.brain.ion.handler

import com.brain.ion.components.Collection
import com.brain.ion.components.Component
import com.brain.ion.graphics.Group
import com.brain.ion.properties.Clickable
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.geom.Point2D

class MouseHandler(private val renderQueue: Group) : MouseListener {
	
	private fun iterateGroup(group: List<*>, p: Point2D, e: MouseEvent) : Boolean {
		
		for (c in group) {
			if (c is Group) {
				if (iterateGroup(c, p, e)) return true
			} else if (c is Collection) {
				if (iterateGroup(c, Point2D.Double(p.x - c.x.toDouble(), p.y - c.y.toDouble()), e)) return true
			} else if (c is Clickable) {
				
				if (c.onClick != c.clickEmpty) {
					if (c.area.contains(p)){
						if ((c as Component).visible) {
							
							c.onClick.invoke(p)
							return true
							
						}
					}
				}
				
			}
			
		}
		
		return false
		
	}
	
	override fun mouseClicked(e: MouseEvent) {
		iterateGroup(renderQueue, e.point, e)
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