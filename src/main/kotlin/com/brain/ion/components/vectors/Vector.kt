package com.brain.ion.components.vectors

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape

interface Vector {
	
	fun clone(): Vector
	
	val style: Style
	val shape: Shape
	
}

data class Style(
		val fillColor: Color,
		val strokeColor: Color,
		val strokeProp: BasicStroke
) {
	
	private constructor(
			c: Converter
	) : this(c.fillColor, c.strokeColor, c.strokeProp)
	
	constructor(
			strokeColorString: String,
			fillColorString: String,
			strokeOpacity: Float,
			fillOpacity: Float,
			strokeWidth: Int
	) : this(Converter(strokeColorString, fillColorString, strokeOpacity, fillOpacity, strokeWidth))
	
	constructor(
			fillColorString: String,
			fillOpacity: Float
	) : this("000000", fillColorString, 0f, fillOpacity, 0)
	
	constructor(
			strokeColorString: String,
			strokeOpacity: Float,
			strokeWidth: Int
	) : this(strokeColorString, "000000", strokeOpacity, 0f, strokeWidth)
	
	constructor() : this("000000", "000000", 0f, 0f, 0)
	
	constructor(s: Style) : this(s.fillColor, s.strokeColor, s.strokeProp)
	
	private class Converter(
			strokeColorString: String,
			fillColorString: String,
			strokeOpacity: Float,
			fillOpacity: Float,
			strokeWidth: Int
	) {
		val fillColor: Color
		val strokeColor: Color
		val strokeProp: BasicStroke
		
		init {
			
			val fillColorFormatted = fillColorString.removePrefix("#").toUpperCase()
			val strokeColorFormatted = strokeColorString.removePrefix("#").toUpperCase()
			val fc = Color.decode("#$fillColorFormatted")
			val sc = Color.decode("#$strokeColorFormatted")
			
			fillColor = Color(fc.red, fc.green, fc.blue, (fillOpacity*255).toInt())
			strokeColor = Color(sc.red, sc.green, sc.blue, (strokeOpacity*255).toInt())
			strokeProp = BasicStroke(strokeWidth.toFloat())
			
		}
	}
	
}