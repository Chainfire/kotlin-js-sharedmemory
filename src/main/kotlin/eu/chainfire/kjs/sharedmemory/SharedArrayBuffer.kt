package eu.chainfire.kjs.sharedmemory

import org.khronos.webgl.ArrayBuffer

external class SharedArrayBuffer(length: Int) : ArrayBuffer {
    companion object {
        val name: String
    }
}

inline fun SharedArrayBuffer.Companion.isSupported(): Boolean = js("typeof SharedArrayBuffer != 'undefined'").unsafeCast<Boolean>()
