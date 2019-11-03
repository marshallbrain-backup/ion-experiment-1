package com.brain.ion.properties

import java.awt.geom.Area
import java.awt.geom.Point2D

interface Clickable {
	
	val area: Area
	val clickEmpty: (Point2D) -> Unit
		get() = {}
	
	var onClick: (Point2D) -> Unit
	
}