package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.geom.Path2D

/**
 * Creates a new vector path
 *
 * @since 0.1
 *
 * @constructor Default constructor
 * @param id The [id]
 * @param shape The [shape]. Defaults to an empty [Path2D]
 * @param style The [style]. Defaults to the default style
 * @since 0.1
 */
data class Path(
		override val id: String,
		override val shape: Path2D.Double = Path2D.Double(),
		override val style: Style = Style()
) : Vector {
	
	override var onClick: () -> Unit = {}
	
	init {
		if (shape.currentPoint == null) {
			shape.moveTo(0.0, 0.0)
		}
	}
	
	/**
	 * Creates a path from a string.
	 *
	 * @param id The [id]
	 * @param pathString The string used to construct the path
	 * @param style The style of the path
	 * @since 0.1
	 */
	constructor(id: String, pathString: String, style: Style = Style()) : this(id, constructPath(id, pathString), style)
	
	/**
	 * Cloning constructor
	 *
	 * @param v The path to clone
	 * @since 0.1
	 */
	constructor(v: Path) : this(v.id, v.shape, v.style)
	
	override fun getComponents(): List<Component> {
		return listOf(this)
	}
	
	override fun clone(): Vector {
		return Path(this)
	}
	
	/**
	 *
	 * @since 0.1
	 */
	fun moveAbs(x: Number, y: Number): Path {
		val tempPath = Path2D.Double(shape)
		tempPath.moveTo(x.toDouble(), y.toDouble())
		return Path(id, tempPath, style)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun move(dx: Number, dy: Number): Path {
		val end = shape.currentPoint
		return moveAbs(end.x + dx.toDouble(), end.y + dy.toDouble())
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun lineAbs(x: Number, y: Number): Path {
		val tempPath = Path2D.Double(shape)
		tempPath.lineTo(x.toDouble(), y.toDouble())
		return Path(id, tempPath, style)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun line(dx: Number, dy: Number): Path {
		val end = shape.currentPoint
		return lineAbs(end.x + dx.toDouble(), end.y + dy.toDouble())
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun lineXAbs(x: Number): Path {
		val end = shape.currentPoint
		return lineAbs(x.toDouble(), end.y)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun lineX(dx: Number): Path {
		val end = shape.currentPoint
		return lineAbs(end.x + dx.toDouble(), end.y)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun lineYAbs(y: Number): Path {
		val end = shape.currentPoint
		return lineAbs(y.toDouble(), end.y)
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun lineY(dy: Number): Path {
		val end = shape.currentPoint
		return lineAbs(end.x, end.y + dy.toDouble())
	}
	
	/**
	 *
	 *
	 * @since 0.1
	 */
	fun close(): Path {
		val tempPath = Path2D.Double(shape)
		tempPath.closePath()
		return Path(id, tempPath, style)
	}
	
}

private fun constructPath(id: String, pathString: String): Path2D.Double {
	
	val pathList = pathString.trimEnd(';').split(";").map { it.trim() }
	var tempPath = Path(id)
	for (s in pathList) {
		val form = s.replace("\\s+", "")
		val type = form[0]
		val par = form.removePrefix("$type(").removeSuffix(")").split(",")
		when (type) {
			'M' -> tempPath = tempPath.moveAbs(par[0].toDouble(), par[1].toDouble())
			'm' -> tempPath = tempPath.move(par[0].toDouble(), par[1].toDouble())
			'L' -> tempPath = tempPath.lineAbs(par[0].toDouble(), par[1].toDouble())
			'l' -> tempPath = tempPath.line(par[0].toDouble(), par[1].toDouble())
			'X' -> tempPath = tempPath.lineXAbs(par[0].toDouble())
			'x' -> tempPath = tempPath.lineX(par[0].toDouble())
			'Y' -> tempPath = tempPath.lineYAbs(par[0].toDouble())
			'y' -> tempPath = tempPath.lineY(par[0].toDouble())
			'z', 'Z' -> tempPath = tempPath.close()
		}
	}
	
	return tempPath.shape
	
}