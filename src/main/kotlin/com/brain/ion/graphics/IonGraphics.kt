package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import java.awt.*

class IonGraphics(
		private val bounds: Rectangle
) {
	
	val renderQueue: RenderQueue = RenderQueueImpl()
	
	private lateinit var graphics: Graphics2D
	
	fun setGraphics(g: Graphics) {
		graphics = g.create() as Graphics2D
		
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
	}
	
	fun draw(component: Component) {
		
		if(component is Vector) {
			draw(component)
		} else {
			
			for (c in component.getCollection()) {
				when(c) {
					is Vector -> draw(c)
					else -> draw(c)
				}
			}
			
		}
		
	}
	
	private fun draw(vector: Vector, x: Number = 0, y: Number = 0) {
		
		val g = graphics.create() as Graphics2D
		g.translate(x.toInt(), y.toInt())

		g.color = vector.style.fillColor
		g.fill(vector.getShape(this))

		g.color = vector.style.strokeColor
		g.stroke = vector.style.strokeProp

		if(vector.style.strokeProp.lineWidth % 2 == 0f) {
			g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		}
		g.draw(vector.getShape(this))
		
	}
	
	fun render() {
		renderQueue.render(this)
	}
	
	private class RenderQueueImpl(
			private val queue: MutableList<Group> = mutableListOf()
	): RenderQueue, List<Group> by queue {
		
		override fun newGroup(): Group {
			val group = GroupImpl(queue.size)
			queue.add(group)
			return group
		}
		
		override fun render(graphics: IonGraphics) {
			for (g in queue) {
				g.render(graphics)
			}
		}
		
	}
	
	private class GroupImpl(
			private var pos: Int,
			private val queue: MutableList<Renderable> = mutableListOf()
	): Group, List<Renderable> by queue {
		
		override fun getPosition(): Int {
			return pos
		}
		
		override fun add(element: Component) {
			queue.add(element)
		}
		
		override fun add(index: Int, element: Component) {
			queue.add(index, element)
		}
		
		override fun newGroup(): Group {
			val group = GroupImpl(queue.size)
			queue.add(group)
			return group
		}
		
		override fun render(g: IonGraphics) {
			for (c in queue) {
				when(c) {
					is Group -> c.render(g)
					is Component -> g.draw(c)
				}
			}
		}
		
	}

}