package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.vectors.Vector
import java.awt.*

class IonGraphics(
		private val bounds: Rectangle = Rectangle(0, 0, 0, 0)
) {
	
	private val dummyGroup: Group = DummyGroup(0, null)
	val renderQueue: Group = dummyGroup.newGroup()
	
	private lateinit var graphics: Graphics2D
	
	fun setGraphics(g: Graphics) {
		graphics = g.create() as Graphics2D
		
		graphics.color = Color.DARK_GRAY
		graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
	}
	
	private operator fun Number.plus(i: Number): Number {
		return this.toDouble() + i.toDouble()
	}
	
	fun draw(component: Component, baseX: Number = 0, baseY: Number = 0) {
		
		val x = baseX + component.x
		val y = baseY + component.y
		
		if(component is Vector) {
			draw(component)
		} else {
			
			for (c in component.getCollection(this)) {
				when(c) {
					is Vector -> draw(c, x, y)
					else -> draw(c, x, y)
				}
			}
			
		}
		
	}
	
	private fun draw(vector: Vector, x: Number = 0, y: Number = 0) {
		
		val g = graphics.create() as Graphics2D
		g.translate(x.toDouble() + vector.x.toDouble(), y.toDouble() + vector.y.toDouble())

		g.color = vector.style.fillColor
		g.fill(vector.getShape())

		g.color = vector.style.strokeColor
		g.stroke = vector.style.strokeProp

		if(vector.style.strokeProp.lineWidth % 2 == 0f) {
			g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		}
		g.draw(vector.getShape())
		
	}
	
	fun render() {
		renderQueue.render(this)
	}
	
	private class GroupImpl(
			override val index: Int,
			override val parent: Renderable,
			private val queue: MutableList<Renderable> = mutableListOf()
	): Group, MutableList<Renderable> by queue {
		
		override fun newGroup(): Group {
			val group = GroupImpl(size, this)
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