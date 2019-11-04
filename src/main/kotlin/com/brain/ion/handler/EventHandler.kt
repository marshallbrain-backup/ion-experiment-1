package com.brain.ion.handler

class EventHandler(
		private val map: MutableMap<EventType, MutableSet<(Any) -> Unit>> = mutableMapOf()
) : Map<EventType, MutableSet<(Any) -> Unit>> by map {
	
	fun add(target: EventType, trigger: (Any) -> Unit) {
		val list = map.getOrPut(target) { mutableSetOf() }
		list.add(trigger)
	}
	
	fun trigger(source: Any, target: EventType) {
		get(target)?.forEach { it.invoke(source) }
	}
	
}