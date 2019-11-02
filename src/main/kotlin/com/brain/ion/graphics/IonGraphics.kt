package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import java.awt.*

class IonGraphics(
		private val bounds: Rectangle = Rectangle(0,0,0,0)
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
			private val queue: MutableList<Renderable> = mutableListOf()
	): RenderQueue, MutableList<Renderable> by queue {
		
		override fun newGroup(add: Boolean): Group {
			val group = GroupImpl()
			if(add){
				queue.add(group)
			}
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
	
	private class DummyGroup(
			override val index: Int,
			override val parent: Renderable?,
			private val queue: MutableList<Renderable> = mutableListOf()
	): Group, MutableList<Renderable> by queue {
		
		private val group = GroupImpl(size, this)
		
		override fun newGroup(): Group {
			return group
		}
		
		override fun render(g: IonGraphics) {
		}
		
	}

}