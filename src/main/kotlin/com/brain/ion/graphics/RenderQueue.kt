package com.brain.ion.graphics

interface RenderQueue {
	
	fun newGroup(): Group
	fun render(graphics: IonGraphics)
}