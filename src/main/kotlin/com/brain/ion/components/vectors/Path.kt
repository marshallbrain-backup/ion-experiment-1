package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.*
import java.util.*

class Path(
		override val id: String,
		override var style: Style
) : Vector {
	
	companion object {
		private fun constructPath(pathString: String): ShapeCustom {
			
			val pathList = pathString.trimEnd(';').split(";").map { it.trim() }
			val tempPath = Path("", Style())
			for (s in pathList) {
				val form = s.replace("\\s+", "")
				val type = form[0]
				val par = form.removePrefix("$type(").removeSuffix(")").split(",")
				when (type) {
					'M' -> tempPath.moveTo(par[0].toDouble(), par[1].toDouble())
					'm' -> tempPath.move(par[0].toDouble(), par[1].toDouble())
					'L' -> tempPath.lineTo(par[0].toDouble(), par[1].toDouble())
					'l' -> tempPath.line(par[0].toDouble(), par[1].toDouble())
					'X' -> tempPath.lineXTo(par[0].toDouble())
					'x' -> tempPath.lineX(par[0].toDouble())
					'Y' -> tempPath.lineYTo(par[0].toDouble())
					'y' -> tempPath.lineY(par[0].toDouble())
					'z', 'Z' -> tempPath.close()
				}
			}
			return tempPath.shape
		}
	}
	
	constructor(id: String, style: Style, pathString: String): this(id, style) {
		shape.cloneCoordList(constructPath(pathString))
	}
	
	val interactionShape = Path2D.Double()
	private val shape = ShapeCustom()
	
	override var onRender: (Component) -> Unit = emptyFunction
	
	override fun getShape(g: IonGraphics): Shape {
		return shape
	}
	
	fun moveTo(x: Number, y: Number, index: Int = -1, insert: Boolean = false) {
		shape.moveTo(x.toDouble(), y.toDouble(), index, insert)
	}
	
	fun move(x: Number, y: Number, index: Int = -1, insert: Boolean = false) {
		val end = shape.currentPoint
		shape.moveTo(end.x + x.toDouble(), end.y + y.toDouble(), index, insert)
	}
	
	fun lineTo(x: Number, y: Number, index: Int = -1, insert: Boolean = false) {
		shape.lineTo(x.toDouble(), y.toDouble(), index, insert)
	}
	
	fun line(x: Number, y: Number, index: Int = -1, insert: Boolean = false) {
		val end = shape.currentPoint
		shape.lineTo(end.x + x.toDouble(), end.y + y.toDouble(), index, insert)
	}
	
	fun lineXTo(x: Number, index: Int = -1, insert: Boolean = false) {
		val end = shape.currentPoint
		shape.lineTo(x.toDouble(), end.y, index, insert)
	}
	
	fun lineX(x: Number, index: Int = -1, insert: Boolean = false) {
		val end = shape.currentPoint
		shape.lineTo(end.x + x.toDouble(), end.y, index, insert)
	}
	
	fun lineYTo(y: Number, index: Int = -1, insert: Boolean = false) {
		val end = shape.currentPoint
		shape.lineTo(end.x, y.toDouble(), index, insert)
	}
	
	fun lineY(y: Number, index: Int = -1, insert: Boolean = false) {
		val end = shape.currentPoint
		shape.lineTo(end.x, end.y + y.toDouble(), index, insert)
	}
	
	fun close(index: Int = -1, insert: Boolean = false) {
		shape.close(index, insert)
	}
	
	override fun clone() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	private class ShapeCustom(): ShapeImpl(){
		
		private val segMoveTo = PathIterator.SEG_MOVETO.toByte()
		private val segLineTo = PathIterator.SEG_LINETO.toByte()
		private val segQuadTo = PathIterator.SEG_QUADTO.toByte()
		private val segCubicTo = PathIterator.SEG_CUBICTO.toByte()
		private val segClose = PathIterator.SEG_CLOSE.toByte()
		
		private val coordList: LinkedList<CoordEntry> = LinkedList()
		val currentPoint = Point2D.Double()
		
		init {
			coordList.add(CoordEntry(segMoveTo, doubleArrayOf(0.0, 0.0)))
		}
		
		fun cloneCoordList(clone: ShapeCustom) {
			coordList.clear()
			coordList.addAll(clone.coordList)
		}
		
		fun moveTo(x: Double, y: Double, index: Int, insert: Boolean) {
			when {
				coordList[coordList.size-1].type == segMoveTo -> {
					coordList[coordList.size-1] = CoordEntry(segMoveTo, doubleArrayOf(x, y))
				}
				index != -1 -> coordList[index] = CoordEntry(segMoveTo, doubleArrayOf(x, y))
				insert -> coordList.add(index, CoordEntry(segMoveTo, doubleArrayOf(x, y)))
				else -> coordList.add(CoordEntry(segMoveTo, doubleArrayOf(x, y)))
			}
			currentPoint.setLocation(x, y)
		}
		
		fun lineTo(x: Double, y: Double, index: Int, insert: Boolean) {
			when {
				index != -1 -> coordList[index] = CoordEntry(segLineTo, doubleArrayOf(x, y))
				insert -> coordList.add(index, CoordEntry(segLineTo, doubleArrayOf(x, y)))
				else -> coordList.add(CoordEntry(segLineTo, doubleArrayOf(x, y)))
			}
			currentPoint.setLocation(x, y)
		}
		
		fun quadTo(x1: Double, y1: Double,
		           x2: Double, y2: Double,
		           index: Int, insert: Boolean
		) {
			when {
				index != -1 -> coordList[index] = CoordEntry(segQuadTo, doubleArrayOf(x1, y1, x2, y2))
				insert -> coordList.add(index, CoordEntry(segQuadTo, doubleArrayOf(x1, y1, x2, y2)))
				else -> coordList.add(CoordEntry(segQuadTo, doubleArrayOf(x1, y1, x2, y2)))
			}
		}
		
		fun curveTo(
				x1: Double, y1: Double,
				x2: Double, y2: Double,
				x3: Double, y3: Double,
				index: Int, insert: Boolean
		) {
			when {
				index != -1 -> coordList[index] = CoordEntry(segCubicTo, doubleArrayOf(x1, y1, x2, y2, x3, y3))
				insert -> coordList.add(index, CoordEntry(segCubicTo, doubleArrayOf(x1, y1, x2, y2, x3, y3)))
				else -> coordList.add(CoordEntry(segCubicTo, doubleArrayOf(x1, y1, x2, y2, x3, y3)))
			}
		}
		
		fun close(index: Int, insert: Boolean){
			when {
				index != -1 -> coordList[index] = CoordEntry(segClose, doubleArrayOf())
				insert -> coordList.add(index, CoordEntry(segClose, doubleArrayOf()))
				else -> coordList.add(CoordEntry(segClose, doubleArrayOf()))
			}
		}
		
		override fun getPathIterator(at: AffineTransform?): PathIterator {
			return PathIteratorCustom(coordList.toList())
		}
		
		class CoordEntry(
				val type: Byte,
				val coords: DoubleArray
		)
		
	}
	
	private class PathIteratorCustom(
			private val coordList: List<ShapeCustom.CoordEntry>
	): PathIterator{
		
		private val curvecoords = intArrayOf(2, 2, 4, 6, 0)
		
		private var typeIdx: Int = 0
		
		override fun next() {
			typeIdx++
		}
		
		override fun getWindingRule(): Int {
			return PathIterator.WIND_NON_ZERO
		}
		
		override fun currentSegment(coords: FloatArray): Int {
			val type = coordList[typeIdx].type.toInt()
			val numCoords = curvecoords[type]
			if (numCoords > 0) {
				for (i in 0 until numCoords) {
					coords[i] = coordList[typeIdx].coords[i].toFloat()
					
//					affine.transform(doubleCoords, pointIdx,
//							coords, 0, numCoords / 2)
				}
			}
			return type
		}
		
		override fun currentSegment(coords: DoubleArray): Int {
			val type = coordList[typeIdx].type.toInt()
			val numCoords = curvecoords[type]
			if (numCoords > 0) {
				for (i in 0 until numCoords) {
					coords[i] = coordList[typeIdx].coords[i]
				}
			}
			return type
		}
		
		override fun isDone(): Boolean {
			return (typeIdx >= coordList.size)
		}
		
	}
	
	private open class ShapeImpl(): Shape{
		
		override fun getPathIterator(at: AffineTransform?): PathIterator {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun getPathIterator(at: AffineTransform?, flatness: Double): PathIterator {
			return FlatteningPathIterator(getPathIterator(at), flatness)
		}
		
		override fun contains(x: Double, y: Double): Boolean {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun contains(p: Point2D?): Boolean {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun contains(x: Double, y: Double, w: Double, h: Double): Boolean {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun contains(r: Rectangle2D?): Boolean {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun getBounds2D(): Rectangle2D {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun intersects(x: Double, y: Double, w: Double, h: Double): Boolean {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun intersects(r: Rectangle2D?): Boolean {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun getBounds(): Rectangle {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
	}
	
}
