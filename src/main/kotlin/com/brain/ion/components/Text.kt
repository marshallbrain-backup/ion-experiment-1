package com.brain.ion.components

import com.brain.ion.components.vectors.Path
import com.brain.ion.components.vectors.Style
import com.brain.ion.graphics.IonGraphics
import java.awt.Font

class Text(
		override val id: String,
		t: String = "",
		override var x: Number = 0,
		override var y: Number = 0,
		var style: Style = Style(),
		override var visible: Boolean = true
) : Component {
	
	override var onRender: (Component) -> Unit = renderEmpty
	private var changed = false
	var text = t.trim()
		set(value) {
			changed = true
			field = value
		}
	
	override fun clone(): Component {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun getCollection(graphics: IonGraphics): List<Component> {
		
		val g = graphics.graphics
		val font = Font("Open Sans", Font.PLAIN, 18)
		val fontMetrics = g.getFontMetrics(font)
		val glyph = font.createGlyphVector(fontMetrics.fontRenderContext, text)
		
		val outline = glyph.getOutline(0f, -glyph.visualBounds.y.toFloat())
		
		return listOf(Path("id", style, outline))
		
	}
	
}