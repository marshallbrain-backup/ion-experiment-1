package com.brain.ion.graphics

import com.brain.ion.components.vectors.Vector

class Group (
        val id: String
) {
    
    val queue = mutableListOf<Vector>()
        get() {
            return field.toMutableList()
        }
    
    fun addToQueue(vectors: Array<out Vector>) {
        queue.addAll(vectors)
    }
    
    fun removeFromQueue(vectors: Array<out Vector>) {
        queue.removeAll(vectors)
    }
    
    fun render(g: ComGraphics) {
        for (v in queue) {
            g.draw(v)
        }
    }
    
}