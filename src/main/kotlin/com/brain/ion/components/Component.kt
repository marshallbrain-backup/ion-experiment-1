package com.brain.ion.components

import com.brain.ion.properties.FunctionVariable

interface Component{
	
	val id: String
	val emptyFunction: (Component) -> Unit
		get() = {}
	
	var onRender: (Component) -> Unit
	
	fun clone()
	fun getCollection(): List<Component>
	
}