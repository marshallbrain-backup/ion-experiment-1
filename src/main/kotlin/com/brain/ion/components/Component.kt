package com.brain.ion.components

import com.brain.ion.properties.FunctionVariable

interface Component{
	
	val id: String
	
	fun assign(varMap: MutableMap<String, FunctionVariable>)
	fun clone()
	fun getCollection(): List<Component>
	
}