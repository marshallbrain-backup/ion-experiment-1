package com.brain.ion.components.vectors

import com.brain.ion.components.Component
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Point2D

class Shape(
		override val id: String,
		shape: Shape,
		override var style: Style
) : Vector {
	
	private var privateShape = shape
	override var visible: Boolean = true
	override var onRender: (Component) -> Unit = renderEmpty
	override var x: Number = 0
	override var y: Number = 0
	override var onClick: (Point2D) -> Unit = clickEmpty
	
	fun setShape(shape: Shape) {
		privateShape = shape
	}
	
	override fun getShape(): Shape {
		return privateShape
	}
	
	override fun clone(): Component {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}