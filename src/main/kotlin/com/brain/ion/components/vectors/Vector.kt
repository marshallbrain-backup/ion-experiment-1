package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape
import java.awt.geom.Area

/**
 * Interface for vector components
 *
 * @since 0.1
 */
interface Vector : Component {
	
	/**
	 * The style of the vector
	 *
	 * @since 0.1
	 */
	val style: Style
	/**
	 * The shape used for rendering
	 *
	 * @since 0.1
	 */
	val shape: Shape
	
	override fun getInteractArea(): Area {
		return Area(shape)
	}
	
}

/**
 * Holds style information for rendering
 *
 * @param fillColor The color used to fill the [Vector]
 * @param strokeColor The color used for the outline of the [Vector]
 * @param strokeProp The properties used for the outline of the [Vector]
 * @since 0.1
 */
data class Style(
		val fillColor: Color,
		val strokeColor: Color,
		val strokeProp: BasicStroke
) {
	
	private constructor(
			c: Converter
	) : this(c.fillColor, c.strokeColor, c.strokeProp)
	
	/**
	 * Creates a new style.
	 *
	 * @param strokeColorString A hex string representing the color of the outline
	 * @param fillColorString A hex string representing the color of the fill
	 * @param strokeOpacity The opacity of the outline
	 * @param fillOpacity The opacity of the fill
	 * @param strokeWidth The width of the outline
	 * @since 0.1
	 */
	constructor(
			strokeColorString: String,
			fillColorString: String,
			strokeOpacity: Number,
			fillOpacity: Number,
			strokeWidth: Number
	) : this(Converter(strokeColorString, fillColorString, strokeOpacity, fillOpacity, strokeWidth))
	
	/**
	 * Creates a new style.
	 *
	 * @param fillColorString A hex string representing the color of the fill
	 * @param fillOpacity The opacity of the fill
	 * @since 0.1
	 */
	constructor(
			fillColorString: String,
			fillOpacity: Number
	) : this("000000", fillColorString, 0f, fillOpacity, 0)
	
	/**
	 *
	 * Creates a new style.
	 *
	 * @param strokeColorString A hex string representing the color of the outline
	 * @param strokeOpacity The opacity of the outline
	 * @param strokeWidth The width of the outline
	 * @since 0.1
	 */
	constructor(
			strokeColorString: String,
			strokeOpacity: Number,
			strokeWidth: Number
	) : this(strokeColorString, "000000", strokeOpacity, 0f, strokeWidth)
	
	/**
	 * Creates a default style. Transparent fill and stroke with the stroke having a width of 0
	 *
	 * @since 0.1
	 */
	constructor() : this("000000", "000000", 0f, 0f, 0)
	
	/**
	 * Clones the given style
	 *
	 * @param s The style to clone
	 * @since 0.1
	 */
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
			
			fillColor = Color(fc.red, fc.green, fc.blue, (fillOpacity.toFloat() * 255).toInt())
			strokeColor = Color(sc.red, sc.green, sc.blue, (strokeOpacity.toFloat() * 255).toInt())
			strokeProp = BasicStroke(strokeWidth.toFloat())
			
		}
	}
	
}