package com.brain.ion.graphics

import com.brain.ion.components.Component
import com.brain.ion.components.Text
import com.brain.ion.components.vectors.Path
import com.brain.ion.components.vectors.Style
import com.brain.ion.components.vectors.Vector
import java.awt.*
import java.awt.font.TextLayout
import java.awt.geom.AffineTransform


class IonGraphics(
		private val bounds: Rectangle = Rectangle(0, 0, 0, 0),
		g: Graphics2D = DummyGraphics2D()
) {
	
	val renderQueue: Group = GroupImpl(0, null)
	val graphics: Graphics2D
		get() = graphicsPrivate.create() as Graphics2D
	
	private var graphicsPrivate: Graphics2D = g
	
	fun setGraphics(g: Graphics) {
		graphicsPrivate = g.create() as Graphics2D
		
		graphicsPrivate.color = Color.DARK_GRAY
		graphicsPrivate.setClip(bounds.x, bounds.y, bounds.width, bounds.height)
		graphicsPrivate.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		
		graphicsPrivate.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
		graphicsPrivate.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
		
//		val text = Text("Test")
//		graphicsPrivate.color = Color.white
//		val frc = graphicsPrivate.getFontRenderContext()
//		val font = Font("Open Sans", Font.BOLD, 18);
//		val layout = TextLayout("This is a string", font, frc)
//		val at = AffineTransform()
//		at.setToTranslation(100.0, 100.0)
//		graphics.fill(Path("", Style(), layout.getOutline(at)).getShape())
		
	}
	
	private operator fun Number.plus(i: Number): Number {
		return this.toDouble() + i.toDouble()
	}
	
	fun draw(component: Component, baseX: Number = 0, baseY: Number = 0) {
		
		if (!component.visible) {
			return
		}
		
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
		
		if(!vector.visible) {
			return
		}
		
		val graphics = graphics
		graphics.translate(x.toDouble(), y.toDouble())

		graphics.color = vector.style.fillColor
		graphics.fill(vector.getShape())
		
		if (vector.style.strokeColor.alpha != 0) {
			graphics.color = vector.style.strokeColor
			graphics.stroke = vector.style.strokeProp

//			if(vector.style.strokeProp.lineWidth % 2 == 0f) {
//				graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//			}
			graphics.draw(vector.getShape())

		}
		
	}
	
	fun render() {
		renderQueue.render(this)
	}
	
	private class GroupImpl(
			override val index: Int,
			override val parent: Renderable?,
			private val queue: MutableList<Renderable> = mutableListOf()
	): Group, MutableList<Renderable> by queue {
		
		override fun newGroup(): Group {
			val group = GroupImpl(size, this)
			queue.add(group)
			return group
		}
		
		override fun render(g: IonGraphics) {
			for (c in queue.toList()) {
				when(c) {
					is Group -> c.render(g)
					is Component -> g.draw(c)
				}
			}
		}
		
	}

}