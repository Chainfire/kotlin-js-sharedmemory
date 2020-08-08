import org.khronos.webgl.ArrayBufferView
import org.khronos.webgl.Int32Array

external class Atomics {
    companion object {
        fun add(typedArray: ArrayBufferView, index: Int, value: Int): Int
        fun and(typedArray: ArrayBufferView, index: Int, value: Int): Int
        fun compareExchange(typedArray: ArrayBufferView, index: Int, expectedValue: Int, replacementValue: Int): Int
        fun exchange(typedArray: ArrayBufferView, index: Int, value: Int): Int
        fun isLockFree(size: Int): Boolean
        fun load(typedArray: ArrayBufferView, index: Int): Int
        fun notify(typedArray: Int32Array, index: Int, count: Int = definedExternally): Int
        fun or(typedArray: ArrayBufferView, index: Int, value: Int): Int
        fun store(typedArray: ArrayBufferView, index: Int, value: Int): Int
        fun sub(typedArray: ArrayBufferView, index: Int, value: Int): Int
        fun wait(typedArray: Int32Array, index: Int, value: Int, timeout: Int = definedExternally): String
        fun xor(typedArray: ArrayBufferView, index: Int, value: Int): Int
    }
}
