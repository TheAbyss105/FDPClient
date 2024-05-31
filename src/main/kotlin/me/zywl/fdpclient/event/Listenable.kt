/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/SkidderMC/FDPClient/
 */
package me.zywl.fdpclient.event

import java.lang.ref.WeakReference
import java.lang.reflect.Method

interface Listenable {
    fun handleEvents(): Boolean
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventTarget(val ignoreCondition: Boolean = false, val priority: Int = 0)

internal class EventHook(val listenerRef: WeakReference<Listenable>, val method: Method, eventTarget: EventTarget) {
    val isIgnoreCondition = eventTarget.ignoreCondition
    val priority = eventTarget.priority

    constructor(listenerRef: WeakReference<Listenable>, method: Method) : this(listenerRef, method, method.getAnnotation(EventTarget::class.java))
}