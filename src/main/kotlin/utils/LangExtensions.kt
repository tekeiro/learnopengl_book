package utils

import org.lwjgl.system.MemoryUtil

/**
 * Lang extensions
 */
object LangExtensions {

    /**
     * Null pointer representation
     */
    val nullptr = MemoryUtil.NULL


    /**
     * Return true if variable holds
     * a null reference
     */
    fun Long.isNullPtr() = this == nullptr




}