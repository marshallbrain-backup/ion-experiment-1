package com.brain.ion.components

import com.brain.ion.graphics.Renderable

interface Component: Renderable {
	
	val id: String
	val emptyFunction: (Component) -> Unit
		get() = {}
	
	var onRender: (Component) -> Unit
	
	fun clone(): Component
	fun getCollection(): List<Component>
	
}