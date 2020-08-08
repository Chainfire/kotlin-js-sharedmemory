package eu.chainfire.kjs.sharedmemory

import org.khronos.webgl.*
import kotlin.collections.Iterator

class TypedArrayIndexValue<Index, Value> {
    val index: Index
        inline get() = asDynamic()[0].unsafeCast<Index>()

    val value: Value
        inline get() = asDynamic()[1].unsafeCast<Value>()
}

class TypedArrayIterator<T>(private val jsIterator: dynamic) : Iterator<T> {
    private var value: T? = null
    private var done: Boolean = false

    init {
        progress()
    }

    private fun progress() {
        val o = jsIterator.next()
        done = o["done"].unsafeCast<Boolean>()
        if (o["value"] === undefined) {
            value = null
        } else {
            value = o["value"].unsafeCast<T>()
        }
    }

    override fun hasNext(): Boolean {
        return !done
    }

    override fun next(): T {
        val v = value ?: throw NoSuchElementException()
        progress()
        return v
    }
}

typealias TypedArrayIntElementCallback = (element: Int, index: Int, array: ArrayBufferView) -> Int
typealias TypedArrayFloatElementCallback = (element: Float, index: Int, array: ArrayBufferView) -> Float
typealias TypedArrayDoubleElementCallback = (element: Double, index: Int, array: ArrayBufferView) -> Double

typealias TypedArrayIntElementBooleanCallback = (element: Int, index: Int, array: ArrayBufferView) -> Boolean
typealias TypedArrayFloatElementBooleanCallback = (element: Float, index: Int, array: ArrayBufferView) -> Boolean
typealias TypedArrayDoubleElementBooleanCallback = (element: Double, index: Int, array: ArrayBufferView) -> Boolean

typealias TypedArrayIntElementUnitCallback = (element: Int, index: Int, array: ArrayBufferView) -> Unit
typealias TypedArrayFloatElementUnitCallback = (element: Float, index: Int, array: ArrayBufferView) -> Unit
typealias TypedArrayDoubleElementUnitCallback = (element: Double, index: Int, array: ArrayBufferView) -> Unit

typealias TypedArrayPreviousIntElementCallback = (previousElement: Int, element: Int, index: Int, array: ArrayBufferView) -> Int
typealias TypedArrayPreviousFloatElementCallback = (previousElement: Float, element: Float, index: Int, array: ArrayBufferView) -> Float
typealias TypedArrayPreviousDoubleElementCallback = (previousElement: Double, element: Double, index: Int, array: ArrayBufferView) -> Double

typealias TypedArrayIntElementComparator = (firstEl: Int, secondEl: Int) -> Int
typealias TypedArrayFloatElementComparator = (firstEl: Float, secondEl: Float) -> Int
typealias TypedArrayDoubleElementComparator = (firstEl: Double, secondEl: Double) -> Int

val Int8Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Uint8Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Uint8ClampedArray.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Int16Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Uint16Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Int32Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Uint32Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Float32Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()
val Float64Array.Companion.name: String inline get() = asDynamic().name.unsafeCast<String>()

inline fun Int8Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Int8Array = asDynamic().copyWithin(target, start, end).unsafeCast<Int8Array>()
inline fun Uint8Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Uint8Array = asDynamic().copyWithin(target, start, end).unsafeCast<Uint8Array>()
inline fun Uint8ClampedArray.copyWithin(target: Int, start: Int, end: Int? = undefined): Uint8ClampedArray = asDynamic().copyWithin(target, start, end).unsafeCast<Uint8ClampedArray>()
inline fun Int16Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Int16Array = asDynamic().copyWithin(target, start, end).unsafeCast<Int16Array>()
inline fun Uint16Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Uint16Array = asDynamic().copyWithin(target, start, end).unsafeCast<Uint16Array>()
inline fun Int32Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Int32Array = asDynamic().copyWithin(target, start, end).unsafeCast<Int32Array>()
inline fun Uint32Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Uint32Array = asDynamic().copyWithin(target, start, end).unsafeCast<Uint32Array>()
inline fun Float32Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Float32Array = asDynamic().copyWithin(target, start, end).unsafeCast<Float32Array>()
inline fun Float64Array.copyWithin(target: Int, start: Int, end: Int? = undefined): Float64Array = asDynamic().copyWithin(target, start, end).unsafeCast<Float64Array>()

inline fun Int8Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Uint8Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Uint8ClampedArray.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Int16Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Uint16Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Int32Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Uint32Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Int>> = TypedArrayIterator(asDynamic().entries())
inline fun Float32Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Float>> = TypedArrayIterator(asDynamic().entries())
inline fun Float64Array.entries(): TypedArrayIterator<TypedArrayIndexValue<Int, Double>> = TypedArrayIterator(asDynamic().entries())

inline fun Int8Array.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Uint8Array.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Uint8ClampedArray.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Int16Array.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Uint16Array.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Int32Array.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Uint32Array.every(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Float32Array.every(noinline callback: TypedArrayFloatElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()
inline fun Float64Array.every(noinline callback: TypedArrayDoubleElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().every(callback).unsafeCast<Boolean>()

inline fun Int8Array.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Int8Array = asDynamic().fill(value, start, end).unsafeCast<Int8Array>()
inline fun Uint8Array.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Uint8Array = asDynamic().fill(value, start, end).unsafeCast<Uint8Array>()
inline fun Uint8ClampedArray.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Uint8ClampedArray = asDynamic().fill(value, start, end).unsafeCast<Uint8ClampedArray>()
inline fun Int16Array.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Int16Array = asDynamic().fill(value, start, end).unsafeCast<Int16Array>()
inline fun Uint16Array.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Uint16Array = asDynamic().fill(value, start, end).unsafeCast<Uint16Array>()
inline fun Int32Array.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Int32Array = asDynamic().fill(value, start, end).unsafeCast<Int32Array>()
inline fun Uint32Array.fill(value: Int, start: Int? = undefined, end: Int? = undefined): Uint32Array = asDynamic().fill(value, start, end).unsafeCast<Uint32Array>()
inline fun Float32Array.fill(value: Float, start: Int? = undefined, end: Int? = undefined): Float32Array = asDynamic().fill(value, start, end).unsafeCast<Float32Array>()
inline fun Float64Array.fill(value: Double, start: Int? = undefined, end: Int? = undefined): Float64Array = asDynamic().fill(value, start, end).unsafeCast<Float64Array>()

inline fun Int8Array.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int8Array = asDynamic().filter(callback).unsafeCast<Int8Array>()
inline fun Uint8Array.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Uint8Array = asDynamic().filter(callback).unsafeCast<Uint8Array>()
inline fun Uint8ClampedArray.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Uint8ClampedArray = asDynamic().filter(callback).unsafeCast<Uint8ClampedArray>()
inline fun Int16Array.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int16Array = asDynamic().filter(callback).unsafeCast<Int16Array>()
inline fun Uint16Array.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Uint16Array = asDynamic().filter(callback).unsafeCast<Uint16Array>()
inline fun Int32Array.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int32Array = asDynamic().filter(callback).unsafeCast<Int32Array>()
inline fun Uint32Array.filter(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Uint32Array = asDynamic().filter(callback).unsafeCast<Uint32Array>()
inline fun Float32Array.filter(noinline callback: TypedArrayFloatElementBooleanCallback, thisArg: Any? = undefined): Float32Array = asDynamic().filter(callback).unsafeCast<Float32Array>()
inline fun Float64Array.filter(noinline callback: TypedArrayDoubleElementBooleanCallback, thisArg: Any? = undefined): Float64Array = asDynamic().filter(callback).unsafeCast<Float64Array>()

inline fun Int8Array.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Uint8Array.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Uint8ClampedArray.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Int16Array.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Uint16Array.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Int32Array.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Uint32Array.find(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int? = asDynamic().find(callback).unsafeCast<Int?>()
inline fun Float32Array.find(noinline callback: TypedArrayFloatElementBooleanCallback, thisArg: Any? = undefined): Float? = asDynamic().find(callback).unsafeCast<Float?>()
inline fun Float64Array.find(noinline callback: TypedArrayDoubleElementBooleanCallback, thisArg: Any? = undefined): Double? = asDynamic().find(callback).unsafeCast<Double?>()

inline fun Int8Array.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Uint8Array.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Uint8ClampedArray.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Int16Array.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Uint16Array.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Int32Array.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Uint32Array.findIndex(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Float32Array.findIndex(noinline callback: TypedArrayFloatElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()
inline fun Float64Array.findIndex(noinline callback: TypedArrayDoubleElementBooleanCallback, thisArg: Any? = undefined): Int = asDynamic().findIndex(callback).unsafeCast<Int>()

inline fun Int8Array.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Uint8Array.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Uint8ClampedArray.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Int16Array.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Uint16Array.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Int32Array.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Uint32Array.forEach(noinline callback: TypedArrayIntElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Float32Array.forEach(noinline callback: TypedArrayFloatElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()
inline fun Float64Array.forEach(noinline callback: TypedArrayDoubleElementUnitCallback, thisArg: Any? = undefined) = asDynamic().forEach(callback).unsafeCast<Unit>()

inline fun Int8Array.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Uint8Array.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Uint8ClampedArray.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Int16Array.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Uint16Array.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Int32Array.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Uint32Array.includes(searchElement: Int, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Float32Array.includes(searchElement: Float, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()
inline fun Float64Array.includes(searchElement: Double, fromIndex: Int? = undefined): Boolean = asDynamic().includes(searchElement, fromIndex).unsafeCast<Boolean>()

inline fun Int8Array.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint8Array.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint8ClampedArray.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Int16Array.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint16Array.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Int32Array.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint32Array.indexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Float32Array.indexOf(searchElement: Float, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Float64Array.indexOf(searchElement: Double, fromIndex: Int? = undefined): Int = asDynamic().indexOf(searchElement, fromIndex).unsafeCast<Int>()

inline fun ArrayBufferView.join(separator: String? = undefined) : String = asDynamic().join(separator).unsafeCast<String>()

inline fun ArrayBufferView.keys(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().keys())

inline fun Int8Array.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint8Array.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint8ClampedArray.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Int16Array.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint16Array.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Int32Array.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Uint32Array.lastIndexOf(searchElement: Int, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Float32Array.lastIndexOf(searchElement: Float, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()
inline fun Float64Array.lastIndexOf(searchElement: Double, fromIndex: Int? = undefined): Int = asDynamic().lastIndexOf(searchElement, fromIndex).unsafeCast<Int>()

inline fun Int8Array.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Int8Array = asDynamic().map(callback).unsafeCast<Int8Array>()
inline fun Uint8Array.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Uint8Array = asDynamic().map(callback).unsafeCast<Uint8Array>()
inline fun Uint8ClampedArray.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Uint8ClampedArray = asDynamic().map(callback).unsafeCast<Uint8ClampedArray>()
inline fun Int16Array.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Int16Array = asDynamic().map(callback).unsafeCast<Int16Array>()
inline fun Uint16Array.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Uint16Array = asDynamic().map(callback).unsafeCast<Uint16Array>()
inline fun Int32Array.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Int32Array = asDynamic().map(callback).unsafeCast<Int32Array>()
inline fun Uint32Array.map(noinline callback: TypedArrayIntElementCallback, thisArg: Any? = undefined): Uint32Array = asDynamic().map(callback).unsafeCast<Uint32Array>()
inline fun Float32Array.map(noinline callback: TypedArrayFloatElementCallback, thisArg: Any? = undefined): Float32Array = asDynamic().map(callback).unsafeCast<Float32Array>()
inline fun Float64Array.map(noinline callback: TypedArrayDoubleElementCallback, thisArg: Any? = undefined): Float64Array = asDynamic().map(callback).unsafeCast<Float64Array>()

inline fun Int8Array.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Uint8Array.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Uint8ClampedArray.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Int16Array.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Uint16Array.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Int32Array.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Uint32Array.reduce(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduce(callback).unsafeCast<Int>()
inline fun Float32Array.reduce(noinline callback: TypedArrayPreviousFloatElementCallback, initialValue: Float? = undefined): Float = asDynamic().reduce(callback).unsafeCast<Float>()
inline fun Float64Array.reduce(noinline callback: TypedArrayPreviousDoubleElementCallback, initialValue: Double? = undefined): Double = asDynamic().reduce(callback).unsafeCast<Double>()

inline fun Int8Array.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Uint8Array.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Uint8ClampedArray.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Int16Array.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Uint16Array.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Int32Array.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Uint32Array.reduceRight(noinline callback: TypedArrayPreviousIntElementCallback, initialValue: Int? = undefined): Int = asDynamic().reduceRight(callback).unsafeCast<Int>()
inline fun Float32Array.reduceRight(noinline callback: TypedArrayPreviousFloatElementCallback, initialValue: Float? = undefined): Float = asDynamic().reduceRight(callback).unsafeCast<Float>()
inline fun Float64Array.reduceRight(noinline callback: TypedArrayPreviousDoubleElementCallback, initialValue: Double? = undefined): Double = asDynamic().reduceRight(callback).unsafeCast<Double>()

inline fun Int8Array.reverse(): Int8Array = asDynamic().reverse().unsafeCast<Int8Array>()
inline fun Uint8Array.reverse(): Uint8Array = asDynamic().reverse().unsafeCast<Uint8Array>()
inline fun Uint8ClampedArray.reverse(): Uint8ClampedArray = asDynamic().reverse().unsafeCast<Uint8ClampedArray>()
inline fun Int16Array.reverse(): Int16Array = asDynamic().reverse().unsafeCast<Int16Array>()
inline fun Uint16Array.reverse(): Uint16Array = asDynamic().reverse().unsafeCast<Uint16Array>()
inline fun Int32Array.reverse(): Int32Array = asDynamic().reverse().unsafeCast<Int32Array>()
inline fun Uint32Array.reverse(): Uint32Array = asDynamic().reverse().unsafeCast<Uint32Array>()
inline fun Float32Array.reverse(): Float32Array = asDynamic().reverse().unsafeCast<Float32Array>()
inline fun Float64Array.reverse(): Float64Array = asDynamic().reverse().unsafeCast<Float64Array>()

inline fun Int8Array.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Uint8Array.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Uint8ClampedArray.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Int16Array.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Uint16Array.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Int32Array.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Uint32Array.some(noinline callback: TypedArrayIntElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Float32Array.some(noinline callback: TypedArrayFloatElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()
inline fun Float64Array.some(noinline callback: TypedArrayDoubleElementBooleanCallback, thisArg: Any? = undefined): Boolean = asDynamic().some(callback).unsafeCast<Boolean>()

inline fun Int8Array.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Int8Array = asDynamic().sort().unsafeCast<Int8Array>()
inline fun Uint8Array.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Uint8Array = asDynamic().sort().unsafeCast<Uint8Array>()
inline fun Uint8ClampedArray.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Uint8ClampedArray = asDynamic().sort().unsafeCast<Uint8ClampedArray>()
inline fun Int16Array.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Int16Array = asDynamic().sort().unsafeCast<Int16Array>()
inline fun Uint16Array.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Uint16Array = asDynamic().sort().unsafeCast<Uint16Array>()
inline fun Int32Array.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Int32Array = asDynamic().sort().unsafeCast<Int32Array>()
inline fun Uint32Array.sort(noinline comparator: TypedArrayIntElementComparator? = undefined): Uint32Array = asDynamic().sort().unsafeCast<Uint32Array>()
inline fun Float32Array.sort(noinline comparator: TypedArrayFloatElementComparator? = undefined): Float32Array = asDynamic().sort().unsafeCast<Float32Array>()
inline fun Float64Array.sort(noinline comparator: TypedArrayDoubleElementComparator? = undefined): Float64Array = asDynamic().sort().unsafeCast<Float64Array>()

inline fun Int8Array.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Uint8Array.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Uint8ClampedArray.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Int16Array.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Uint16Array.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Int32Array.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Uint32Array.values(): TypedArrayIterator<Int> = TypedArrayIterator(asDynamic().values())
inline fun Float32Array.values(): TypedArrayIterator<Float> = TypedArrayIterator(asDynamic().values())
inline fun Float64Array.values(): TypedArrayIterator<Double> = TypedArrayIterator(asDynamic().values())
