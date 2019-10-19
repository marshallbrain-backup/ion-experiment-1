package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.geom.Rectangle2D

/**
 * A Rectangle component
 *
 * @since 0.1
 *
 * @constructor Creates a new rectangle
 * @param id The id
 * @param x The x coordinate of the upper left corner
 * @param y The x coordinate of the upper left corner
 * @param width The width
 * @param height The height
 * @param style The style used for rendering
 * @since 0.1
 */
data class Rectangle(
		override val id: String,
		private val x: Number,
		private val y: Number,
		private val width: Number,
		private val height: Number,
		override val style: Style = Style()
) : Vector {
	
	override val shape = Rectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
	
	override var onClick: () -> Unit = {}
	
	/**
	 * Creates a new rectangle with the default [Style]
	 *
	 * @param id The id
	 * @param width The width
	 * @param height The height
	 * @since 0.1
	 */
	constructor(id: String, width: Number, height: Number) :
			this(id, 0f, 0f, width, height)
	
	/**
	 * Creates a new rectangle
	 *
	 * @param id The id
	 * @param width The width
	 * @param height The height
	 * @param style The style used for rendering
	 * @since 0.1
	 */
	constructor(id: String, width: Number, height: Number, style: Style) :
			this(id,0f, 0f, width, height, style)
	
	/**
	 * Clones the rectangle
	 *
	 * @param v The rectangle to clone
	 * @since 0.1
	 */
	constructor(v: Rectangle) :
			this(v.id, v.x, v.y, v.width, v.height, Style(v.style))
	
	override fun getComponents(): List<Component> {
		return listOf(this)
	}
	
	override fun clone(): Vector {
		return Rectangle(this)
	}
	
}