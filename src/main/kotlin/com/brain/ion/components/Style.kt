package com.brain.ion.components

class Style(
	strokeColorUnchecked: String = "#FFFFFF",
	fillColorUnchecked: String = "#FFFFFF",
	val strokeOpacity: Float = 0f,
	val fillOpacity: Float = 0f,
	val strokeWidth: Int = 1
) {
	
	val strokeColor = strokeColorUnchecked.removePrefix("#")
	val fillColor = fillColorUnchecked.removePrefix("#")
	//g.color = Color.decode("#FFFF00")
	
}