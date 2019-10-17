package com.brain.ion.components

import com.brain.ion.properties.FunctionVariable
import com.brain.ion.properties.Renderable

interface Component{
	
	val id: String
	
	fun assign(varMap: MutableMap<String, FunctionVariable>)
	fun clone()
	
}