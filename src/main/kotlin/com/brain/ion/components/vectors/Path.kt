package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Shape
import java.awt.geom.Path2D
import java.awt.geom.PathIterator
import java.util.*

class Path(
		override val id: String,
		override var style: Style
) : Vector {
	
	val shape = Path2D.Double()
	
	override var onRender: (Component) -> Unit = emptyFunction
	
	override fun getShape(g: IonGraphics): Shape {
		shape.reset()
		shape.append(PathIteratorEdit(), false)
		return shape
	}
	
	override fun clone() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	private class PathIteratorEdit(): PathIterator{
		
		private val segMoveTo = PathIterator.SEG_MOVETO.toByte()
		private val segLineTo = PathIterator.SEG_LINETO.toByte()
		private val segQuadTo = PathIterator.SEG_QUADTO.toByte()
		private val segCubicTo = PathIterator.SEG_CUBICTO.toByte()
		private val segClose = PathIterator.SEG_CLOSE.toByte()
		
		private val coordList: LinkedList<CoordEntry> = LinkedList()
		private val curvecoords = intArrayOf(2, 2, 4, 6, 0)
		
		private var typeIdx: Int = 0
		
		init {
			coordList.add(CoordEntry(segMoveTo, doubleArrayOf(0.0, 0.0)))
			coordList.add(CoordEntry(segLineTo, doubleArrayOf(500.0, 500.0)))
		}
		
		override fun next() {
			val type = coordList[typeIdx++].type.toInt()
		}
		
		override fun getWindingRule(): Int {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//			return PathIterator.WIND_NON_ZERO
		}
		
		override fun currentSegment(coords: FloatArray): Int {
			val type = coordList[typeIdx].type.toInt()
			val numCoords = curvecoords[type]
			if (numCoords > 0) {
				for (i in 0 until numCoords) {
					coords[i] = coordList[typeIdx].coords[i].toFloat()
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
		
		private class CoordEntry(
				val type: Byte,
				val coords: DoubleArray
		)
		
	}
	
}
