package com.brain.ion.components

import com.brain.ion.properties.Renderable

interface Component{
	
	val id: String
	
	fun clone()
	
}