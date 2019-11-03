package com.brain.ion.components

import com.brain.ion.graphics.IonGraphics
import com.brain.ion.graphics.Renderable

interface Component: Renderable {
	
	val id: String
	val emptyFunction: (Component) -> Unit
		get() = {}
	
	var visible: Boolean
	var onRender: (Component) -> Unit
	var x: Number
	var y: Number
	
	fun clone(): Component
	fun getCollection(graphics: IonGraphics = IonGraphics()): List<Component>
	
}