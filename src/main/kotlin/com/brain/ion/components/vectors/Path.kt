package com.brain.ion.components.vectors

import java.awt.geom.Path2D

class Path(
		override val shape: Path2D.Double = Path2D.Double(),
		override val style: Style = Style()
) : Vector {
	
	constructor(v: Path) : this(v.shape, v.style)
	
	override fun clone(): Vector {
		return Path(this)
	}
	
	fun moveAbs(x: Number, y: Number): Path {
		val tempPath = Path2D.Double(shape)
		tempPath.moveTo(x.toDouble(), y.toDouble())
		return Path(tempPath, style)
	}
	
	fun move(dx: Number, dy: Number): Path {
		val end = shape.currentPoint
		return moveAbs(end.x + dx.toDouble(), end.y + dy.toDouble())
	}
	
	fun lineAbs(x: Number, y: Number): Path {
		val tempPath = Path2D.Double(shape)
		tempPath.lineTo(x.toDouble(), y.toDouble())
		return Path(tempPath, style)
	}
	
	fun line(dx: Number, dy: Number): Path {
		val end = shape.currentPoint
		return lineAbs(end.x + dx.toDouble(), end.y + dy.toDouble())
	}
	
	fun lineXAbs(x: Number): Path {
		val end = shape.currentPoint
		return lineAbs(x.toDouble(), end.y)
	}
	
	fun lineX(dx: Number): Path {
		val end = shape.currentPoint
		return lineAbs(end.x + dx.toDouble(), end.y)
	}
	
	fun lineYAbs(y: Number): Path {
		val end = shape.currentPoint
		return lineAbs(y.toDouble(), end.y)
	}
	
	fun lineY(dy: Number): Path {
		val end = shape.currentPoint
		return lineAbs(end.x, end.y + dy.toDouble())
	}
	
	fun close(): Path {
		val tempPath = Path2D.Double(shape)
		tempPath.closePath()
		return Path(tempPath, style)
	}

}