package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import com.brain.ion.graphics.IonGraphics
import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.*
import java.util.*

class Path(
		override val id: String,
		override var style: Style = Style(),
		override var visible: Boolean = true
) : Vector {
	
	private val shape = ShapeCustom()
	private val prevPoint = Point2D.Double()
	
	override var x: Number = 0
	override var y: Number = 0
	override var onRender: (Component) -> Unit = emptyFunction
	override var onClick: (Point2D) -> Unit = {}
	
	constructor(id: String, style: Style, pathString: String): this(id, style) {
		shape.cloneCoordList(constructPath(pathString))
	}
	
	constructor(id: String, style: Style, path: Shape) : this(id, style){
		shape.cloneCoordList(path.getPathIterator(null))
	}
	
	constructor(c: Path): this(c.id, c.style) {
		shape.cloneCoordList(c.shape)
		onRender = c.onRender
	}
	
	override fun getShape(): Shape {
		return shape
	}
	
	fun moveTo(x: Number, y: Number, type: Int = ADD, index: Int = shape.length){
		shape.moveTo(x.toDouble(), y.toDouble(), type, index)
	}
	
	fun move(x: Number, y: Number, type: Int = ADD, index: Int = shape.length){
		val end = shape.currentPoint
		shape.moveTo(end.x + x.toDouble(), end.y + y.toDouble(), type, index)
	}

	fun lineTo(
			x: Number = shape.currentPoint.x, y: Number = shape.currentPoint.y,
			type: Int = ADD, index: Int = shape.length
	) {
		shape.lineTo(x.toDouble(), y.toDouble(), type, index)
		prevPoint.setLocation(x.toDouble(), y.toDouble())
	}

	fun line(x: Number = 0, y: Number = 0, type: Int = ADD, index: Int = shape.length) {
		val end = shape.currentPoint
		shape.lineTo(end.x + x.toDouble(), end.y + y.toDouble(), type, index)
		prevPoint.setLocation(x.toDouble(), y.toDouble())
	}
	
	fun bezierCurveTo(
			x1: Number = shape.currentPoint.x * 2 - prevPoint.x, y1: Number = shape.currentPoint.y * 2 - prevPoint.y,
			x2: Number, y2: Number, x3: Number, y3: Number, type: Int = ADD, index: Int = shape.length
	){
		shape.curveTo(
				x1.toDouble(), y1.toDouble(),
				x2.toDouble(), y2.toDouble(),
				x3.toDouble(), y3.toDouble(),
				type, index
		)
		prevPoint.setLocation(x2.toDouble(), y2.toDouble())
	}
	
	fun bezierCurve(
			x1: Number = shape.currentPoint.x - prevPoint.x, y1: Number = shape.currentPoint.y - prevPoint.y,
			x2: Number, y2: Number, x3: Number, y3: Number,
			type: Int = ADD, index: Int = shape.length
	){
		val end = shape.currentPoint
		shape.curveTo(
				end.x + x1.toDouble(), end.y + y1.toDouble(),
				end.x + x2.toDouble(), end.y + y2.toDouble(),
				end.x + x3.toDouble(), end.y + y3.toDouble(),
				type, index
		)
		prevPoint.setLocation(x2.toDouble(), y2.toDouble())
	}
	
	fun quadCurve(){
	
	}

	fun close(type: Int = ADD, index: Int = shape.length) {
		shape.close(type, index)
	}
	
	override fun clone(): Component {
		return Path(this)
	}
	
	companion object {
		const val ADD: Int = 0
		const val SET: Int = 1
		const val REMOVE: Int = 2
		
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
					'X' -> tempPath.lineTo(x = par[0].toDouble())
					'x' -> tempPath.line(x = par[0].toDouble())
					'Y' -> tempPath.lineTo(y = par[0].toDouble())
					'y' -> tempPath.line(y = par[0].toDouble())
					'C' -> {
						tempPath.bezierCurveTo(
								par[0].toDouble(), par[1].toDouble(),
								par[2].toDouble(), par[3].toDouble(),
								par[4].toDouble(), par[5].toDouble()
						)
					}
					'c' -> {
						tempPath.bezierCurve(
								par[0].toDouble(), par[1].toDouble(),
								par[2].toDouble(), par[3].toDouble(),
								par[4].toDouble(), par[5].toDouble()
						)
					}
					'S' -> {
						tempPath.bezierCurveTo(
								x2 = par[0].toDouble(), y2 = par[1].toDouble(),
								x3 = par[2].toDouble(), y3 = par[3].toDouble()
						)
					}
					's' -> {
						tempPath.bezierCurve(
								x2 = par[0].toDouble(), y2 = par[1].toDouble(),
								x3 = par[2].toDouble(), y3 = par[3].toDouble()
						)
					}
					'z', 'Z' -> tempPath.close()
				}
			}
			return tempPath.shape
		}
	}
	
	private class ShapeCustom(): ShapeImpl(){
		
		private val segMoveTo = PathIterator.SEG_MOVETO.toByte()
		private val segLineTo = PathIterator.SEG_LINETO.toByte()
		private val segQuadTo = PathIterator.SEG_QUADTO.toByte()
		private val segCubicTo = PathIterator.SEG_CUBICTO.toByte()
		private val segClose = PathIterator.SEG_CLOSE.toByte()
		
		private val coordList: LinkedList<CoordEntry> = LinkedList()
		val currentPoint = Point2D.Double()
		val length: Int
			get() = coordList.size
		
		init {
			coordList.add(CoordEntry(segMoveTo, doubleArrayOf(0.0, 0.0)))
		}
		
		fun cloneCoordList(clone: ShapeCustom) {
			coordList.clear()
			coordList.addAll(clone.coordList)
			currentPoint.setLocation(getPointFromLast(coordList.last.coords))
		}
		
		fun cloneCoordList(clone: PathIterator) {
			coordList.clear()
			
			do {
				val array = DoubleArray(6)
				coordList.add(CoordEntry(clone.currentSegment(array).toByte(), array))
				clone.next()
			} while (!clone.isDone)
			
			currentPoint.setLocation(getPointFromLast(coordList.last.coords))
		}
		
		private fun getPointFromLast(l: DoubleArray): Point2D {
			return Point2D.Double(l[l.size-2], l[l.size-1])
		}
		
		fun moveTo(x: Double, y: Double, type: Int, index: Int) {
			val array = doubleArrayOf(x, y)
			when {
				coordList[coordList.size-1].type == segMoveTo -> {
					coordList[coordList.size-1] = CoordEntry(segMoveTo, array)
				}
				type == 0 -> coordList.add(index, CoordEntry(segMoveTo, array))
				type == 1 -> coordList[index] = CoordEntry(segMoveTo, array)
				type == 2 -> coordList.removeAt(index)
			}
			currentPoint.setLocation(x, y)
			update = true
		}
		
		fun lineTo(x: Double, y: Double, type: Int, index: Int) {
			val array = doubleArrayOf(x, y)
			when(type) {
				0 -> coordList.add(index, CoordEntry(segLineTo, array))
				1 -> coordList[index] = CoordEntry(segLineTo, array)
				2 -> coordList.removeAt(index)
			}
			currentPoint.setLocation(x, y)
			update = true
		}
		
		fun quadTo(x1: Double, y1: Double,
		           x2: Double, y2: Double,
		           type: Int, index: Int
		) {
			val array = doubleArrayOf(x1, y1, x2, y2)
			when(type) {
				0 -> coordList.add(index, CoordEntry(segQuadTo, array))
				1 -> coordList[index] = CoordEntry(segQuadTo, array)
				2 -> coordList.removeAt(index)
			}
			currentPoint.setLocation(x2, y2)
			update = true
		}
		
		fun curveTo(
				x1: Double, y1: Double,
				x2: Double, y2: Double,
				x3: Double, y3: Double,
				type: Int, index: Int
		) {
			val array = doubleArrayOf(x1, y1, x2, y2, x3, y3)
			when(type) {
				0 -> coordList.add(index, CoordEntry(segCubicTo, array))
				1 -> coordList[index] = CoordEntry(segCubicTo, array)
				2 -> coordList.removeAt(index)
			}
			currentPoint.setLocation(x3, y3)
			update = true
		}
		
		fun close(type: Int, index: Int){
			val array = doubleArrayOf(currentPoint.x, currentPoint.y)
			when(type) {
				0 -> coordList.add(index, CoordEntry(segClose, array))
				1 -> coordList[index] = CoordEntry(segClose, array)
				2 -> coordList.removeAt(index)
			}
			update = true
		}
		
		override fun getPathIterator(at: AffineTransform?): PathIterator {
			return PathIteratorCustom(coordList.toList(), at)
		}
		
		fun clone() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		class CoordEntry(
				val type: Byte,
				val coords: DoubleArray
		)
		
	}
	
	private class PathIteratorCustom(
			private val coordList: List<ShapeCustom.CoordEntry>,
			private val at: AffineTransform?
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
				if(at == null){
					for (i in 0 until numCoords) {
						coords[i] = coordList[typeIdx].coords[i].toFloat()
					}
				} else {
					at.transform(coordList[typeIdx].coords, 0, coords, 0, numCoords / 2)
				}
			}
			return type
		}
		
		override fun currentSegment(coords: DoubleArray): Int {
			val type = coordList[typeIdx].type.toInt()
			val numCoords = curvecoords[type]
			if (numCoords > 0) {
				if(at == null){
					for (i in 0 until numCoords) {
						coords[i] = coordList[typeIdx].coords[i]
					}
				} else {
					at.transform(coordList[typeIdx].coords, 0, coords, 0, numCoords / 2)
				}
			}
			return type
		}
		
		override fun isDone(): Boolean {
			return (typeIdx >= coordList.size)
		}
		
	}
	
	private open class ShapeImpl(): Shape{
		
		private val path: Path2D = Path2D.Double()
		
		protected var update: Boolean = false
		
		private fun update(){
			if (update){
				path.reset()
				path.append(this, false)
			}
		}
		
		override fun getPathIterator(at: AffineTransform?): PathIterator {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
		
		override fun getPathIterator(at: AffineTransform?, flatness: Double): PathIterator {
			return FlatteningPathIterator(getPathIterator(at), flatness)
		}
		
		override fun contains(x: Double, y: Double): Boolean {
			update()
			return path.contains(x, y)
		}
		
		override fun contains(p: Point2D?): Boolean {
			update()
			return path.contains(p)
		}
		
		override fun contains(x: Double, y: Double, w: Double, h: Double): Boolean {
			update()
			return path.contains(x, y, w, h)
		}
		
		override fun contains(r: Rectangle2D?): Boolean {
			update()
			return path.contains(r)
		}
		
		override fun getBounds2D(): Rectangle2D {
			update()
			return path.bounds2D
		}
		
		override fun intersects(x: Double, y: Double, w: Double, h: Double): Boolean {
			update()
			return path.intersects(x, y, w, h)
		}
		
		override fun intersects(r: Rectangle2D?): Boolean {
			update()
			return path.intersects(r)
		}
		
		override fun getBounds(): Rectangle {
			update()
			return path.bounds
		}
		
	}
	
}
