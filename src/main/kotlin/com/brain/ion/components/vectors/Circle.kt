package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.geom.Ellipse2D

/**
 * A circle vector
 *
 * @since 0.1
 *
 * @constructor Default constructor
 * @param id The [id]
 * @param x The x coordinate of the upper left connor
 * @param y The y coordinate of the upper left connor
 * @param radius The radius of the circle
 * @param style The [style]
 */
data class Circle(
		override val id: String,
		private val x: Number,
		private val y: Number,
		private val radius: Number,
		override val style: Style = Style()
) : Vector {
	
	override val shape = Ellipse2D.Double(x.toDouble(), y.toDouble(), radius.toDouble(), radius.toDouble())
	
	override var onClick: () -> Unit = {}
	
	/**
	 * Creates a new circle
	 *
	 * @param id The [id]
	 * @param radius The radius of the circle
	 * @since 0.1
	 */
	constructor(id: String, radius: Number) :
			this(id, 0, 0, radius)
	
	/**
	 * Creates a new circle
	 *
	 * @param id The [id]
	 * @param radius The radius of the circle
	 * @param style The [style]
	 * @since 0.1
	 */
	constructor(id: String, radius: Number, style: Style) :
			this(id, 0, 0, radius, style)
	
	/**
	 * Clones the given circle
	 *
	 * @param v The circle to clone
	 * @since 0.1
	 */
	constructor(v: Circle) :
			this(v.id, v.x, v.y, v.radius, Style(v.style))
	
	override fun getComponents(): List<Component> {
		return listOf(this)
	}
	
	override fun clone(): Vector {
		return Circle(this)
	}
	
}