package com.brain.ion.components.vectors

import com.brain.ion.graphics.IonGraphics
import com.brain.ion.properties.FunctionVariable
import com.brain.ion.properties.Style
import java.awt.Shape
import java.awt.geom.Rectangle2D

class Rectangle(
		override val id: String,
		var x: Number,
		var y: Number,
		var width: Number,
		var height: Number,
		override var style: Style = Style()
) : Vector{
	
	private lateinit var varMap: VarFun
	
	private var shape: Rectangle2D = Rectangle2D.Double(
			x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble()
	)
	
	override fun assign(varMap: MutableMap<String, FunctionVariable>) {
		val v = varMap[""]
		if(v is VarFun){
			this.varMap = v
		}
	}
	
	override fun getShape(g: IonGraphics): Shape {
		x = varMap.xFun?.invoke() ?: x
		y = varMap.yFun?.invoke() ?: y
		width = varMap.widthFun?.invoke() ?: width
		height = varMap.heightFun?.invoke() ?: height
		style = varMap.styleFun?.invoke() ?: style
		
		shape = Rectangle2D.Double(
				x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble()
		)
		
		return shape
	}
	
	override fun clone() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	class VarFun(
			val xFun: (() -> Number)? = null,
			val yFun: (() -> Number)? = null,
			val widthFun: (() -> Number)? = null,
			val heightFun: (() -> Number)? = null,
			val styleFun: (() -> Style)? = null
	): FunctionVariable
	
}