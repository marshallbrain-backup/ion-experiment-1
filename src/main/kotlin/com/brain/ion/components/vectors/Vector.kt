package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape
import java.awt.geom.Area

interface Vector : Component {
	
	fun clone(): Vector
	
	val style: Style
	val shape: Shape
	
	override fun getInteractArea(): Area {
		return Area(shape)
	}
	
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
			strokeOpacity: Number,
			fillOpacity: Number,
			strokeWidth: Number
	) : this(Converter(strokeColorString, fillColorString, strokeOpacity, fillOpacity, strokeWidth))
	
	constructor(
			fillColorString: String,
			fillOpacity: Number
	) : this("000000", fillColorString, 0f, fillOpacity, 0)
	
	constructor(
			strokeColorString: String,
			strokeOpacity: Number,
			strokeWidth: Number
	) : this(strokeColorString, "000000", strokeOpacity, 0f, strokeWidth)
	
	constructor() : this("000000", "000000", 0f, 0f, 0)
	
	constructor(s: Style) : this(s.fillColor, s.strokeColor, s.strokeProp)
	
	private class Converter(
			strokeColorString: String,
			fillColorString: String,
			strokeOpacity: Number,
			fillOpacity: Number,
			strokeWidth: Number
	) {
		val fillColor: Color
		val strokeColor: Color
		val strokeProp: BasicStroke
		
		init {
			
			val fillColorFormatted = fillColorString.removePrefix("#").toUpperCase()
			val strokeColorFormatted = strokeColorString.removePrefix("#").toUpperCase()
			val fc = Color.decode("#$fillColorFormatted")
			val sc = Color.decode("#$strokeColorFormatted")
			
			fillColor = Color(fc.red, fc.green, fc.blue, (fillOpacity.toFloat()*255).toInt())
			strokeColor = Color(sc.red, sc.green, sc.blue, (strokeOpacity.toFloat()*255).toInt())
			strokeProp = BasicStroke(strokeWidth.toFloat())
			
		}
	}
	
}