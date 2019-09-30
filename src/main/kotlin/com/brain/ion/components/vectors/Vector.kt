package com.brain.ion.components.vectors

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape
import java.awt.Stroke

interface Vector {

	val style: Style
	
	fun getShape(): Shape
	
}

class Style(
		strokeColorString: String = "#000000",
		fillColorString: String = "#000000",
		strokeOpacity: Float = 0f,
		fillOpacity: Float = 0f,
		strokeWidth: Int = 0
) {

	val fillColor: Color
	val StrokeColor: Color
	val StrokeProp: BasicStroke

	init {

		val fillColorFormated = fillColorString.removePrefix("#").toUpperCase()
		val strokeColorFormated = strokeColorString.removePrefix("#").toUpperCase()
	    var fc = Color.decode("#$fillColorFormated")
		var sc = Color.decode("#$strokeColorFormated")

		fillColor = Color(fc.red, fc.green, fc.blue, (fillOpacity*255).toInt())
		StrokeColor = Color(sc.red, sc.green, sc.blue, (strokeOpacity*255).toInt())
		StrokeProp = BasicStroke(strokeWidth.toFloat())

	}

}