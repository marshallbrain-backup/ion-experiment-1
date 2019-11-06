package com.brain.ion.components

import com.brain.ion.components.vectors.Shape
import com.brain.ion.components.vectors.Style
import com.brain.ion.graphics.IonGraphics
import java.awt.Font
import java.awt.FontMetrics


class Text(
		override val id: String,
		override var x: Number = 0,
		override var y: Number = 0,
		var style: Style = Style()
) : Component {
	
	override var onRender: (Component) -> Unit = renderEmpty
	private var changed = false
	override var visible: Boolean = true
	var font: Font = Font("Open Sans", Font.PLAIN, 18)
	var maxTextWidth: Number = -1
	var maxTextHeight: Number = -1
	var verticalFormat: VFormat = VFormat.TOP
	var horizontalFormat: HFormat = HFormat.LEFT
	var text: String = ""
		set(value) {
			field = value
			changed = true
		}
	
	constructor(
			id: String,
			x: Number = 0,
			y: Number = 0,
			width: Number = -1,
			height: Number = -1,
			horizontalFormat: HFormat = HFormat.LEFT,
			verticalFormat: VFormat = VFormat.TOP,
			style: Style = Style()
	): this(id, x, y, style = style) {
		maxTextWidth = width
		maxTextHeight = height
		this.horizontalFormat = horizontalFormat
		this.verticalFormat = verticalFormat
	}
	
	constructor(clone: Text): this(clone.id, clone.x, clone.y, clone.style){
		onRender = clone.onRender
		changed = true
		visible = clone.visible
		font = clone.font
		maxTextWidth = clone.maxTextWidth
		maxTextHeight = clone.maxTextHeight
		verticalFormat = clone.verticalFormat
		horizontalFormat = clone.horizontalFormat
		text = clone.text
	}
	
	private fun formatText(fm: FontMetrics) {
	
		val totalLength = fm.stringWidth(text)
	
	}
	
	override fun clone(): Component {
		return Text(this)
	}
	
	override fun getCollection(graphics: IonGraphics): List<Component> {
		
		val g = graphics.graphics
		val fontMetrics = g.getFontMetrics(font)

		if (changed) {
			formatText(fontMetrics)
		}

		val glyph = font.createGlyphVector(fontMetrics.fontRenderContext, text)
		
		val dx: Number = 0
		var dy: Number = -glyph.visualBounds.y
		
		if (verticalFormat == VFormat.CENTERED) {
			dy = maxTextHeight.toDouble() / 2 + glyph.visualBounds.height / 2
		}
		
		val outline = glyph.getOutline(dx.toFloat(), dy.toFloat())
		
		return listOf(Shape(id, outline, style))
		
	}
	
	enum class VFormat {
		TOP, CENTERED, BOTTOM
	}
	
	enum class HFormat {
		LEFT, CENTERED, RIGHT
	}
	
}