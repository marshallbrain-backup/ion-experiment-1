package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.geom.Path2D

data class Path(
		override val shape: Path2D.Double = Path2D.Double(),
		override val style: Style = Style()
) : Vector {
	
	override var onClick: () -> Unit = {}
	
	init {
		if (shape.currentPoint == null){
			shape.moveTo(0.0, 0.0)
		}
	}
	
	constructor(pathString: String, style: Style = Style()) : this(constructPath(pathString), style)
	
	constructor(v: Path) : this(v.shape, v.style)
	
	override fun getComponents(): List<Component> {
		return listOf(this)
	}
	
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

private fun constructPath(pathString: String): Path2D.Double {
	
	//TODO also split after ')' without a ';' after it
	val pathList = pathString.trimEnd(';').split(";").map { it.trim() }
	var tempPath = Path()
	for (s in pathList){
		val form = s.replace("\\s+","")
		val type = form[0]
		val par = form.removePrefix("$type(").removeSuffix(")").split(",")
		tempPath = when (type){
			'M' -> tempPath.moveAbs(par[0].toDouble(), par[1].toDouble())
			'm' -> tempPath.move(par[0].toDouble(), par[1].toDouble())
			'L' -> tempPath.lineAbs(par[0].toDouble(), par[1].toDouble())
			'l' -> tempPath.line(par[0].toDouble(), par[1].toDouble())
			'X' -> tempPath.lineXAbs(par[0].toDouble())
			'x' -> tempPath.lineX(par[0].toDouble())
			'Y' -> tempPath.lineYAbs(par[0].toDouble())
			'y' -> tempPath.lineY(par[0].toDouble())
			'z', 'Z' -> tempPath.close()
			else -> TODO("Add invalid character reporting")
		}
	}
	
	return tempPath.shape
	
}