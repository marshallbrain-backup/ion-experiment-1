package com.brain.ion.graphics

import com.brain.ion.components.Component

interface Group: Renderable, List<Renderable> {
	
	fun getPosition(): Int
	fun add(element: Component)
	fun add(index: Int, element: Component)
	fun newGroup(): Group
	fun render(graphics: IonGraphics)
}