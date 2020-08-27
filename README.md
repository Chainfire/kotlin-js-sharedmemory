# Kotlin/JS: SharedMemory

External declarations related to shared memory  

## License

Copyright &copy; 2020 Jorrit *Chainfire* Jongma

This code is released under the [Apache License version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

## About

This packages adds or extends external declarations related to shared memory
that are currently missing or incomplete in Kotlin 1.4.0-RC.

These *TypedArray* classes are extended:

* Int8Array
* Uint8Array
* Uint8ClampedArray
* Int16Array
* Uint16Array
* Int32Array
* Uint32Array
* Float32Array
* Float64Array

with these fields and methods:

* name
* copyWithin()
* entries()
* every()
* fill()
* filter()
* find()
* findIndex()
* forEach()
* includes()
* indexOf()
* join()
* keys()
* lastIndexOf()
* map()
* reduce()
* reduceRight()
* reverse()
* some()
* sort()
* values()

`BigInt64Array` and `BigUint64Array` are *not* currently provided. The `Int`
class can only represent 53-bit numbers accurately, and while Kotlin does 
have the `Long` class it is not mapped to Javascript's `BigInt` at this time.
This needs some further thought to do right.

These new classes are provided:

* SharedArrayBuffer
    * name
    * byteLength
    * slice()
    * isSupported()

* Atomics
    * add()
    * and()
    * compareExchange()
    * exchange()
    * isLockFree()
    * load()
    * notify()
    * or()
    * store()
    * sub()
    * wait()
    * xor()
    * isSupported()
 
## Disclaimer

At the time of writing I've used Kotlin for about 3 days, so there may be 
room for improvement. This was written for a hobby project and shared
because "why not", updates are dependent on my own needs and PR's. 

Inline documentation is not provided, see 
[MDN](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects) 
for usage details.

## Browser support

Not all classes and methods are available in all browsers. Checking if
the client supports these calls is up to you.

For example, the `TypedArray::fill()` method lists a polyfill on MDN.
Both `SharedArrayBuffer` and `Atomics` are disabled on various browser
versions (due to the exploits) either completely or activated
with a default-disabled config flag. Some mobile browsers don't support
them at all. 2020 has seen (desktop) Chrome, Firefox and Edge bring
back support, provided the document is running in a 
[secure context](https://developer.mozilla.org/en-US/docs/Web/Security/Secure_Contexts) 
(such as https or localhost) and these headers are sent:

```
Cross-Origin-Opener-Policy: same-origin
Cross-Origin-Embedder-Policy: require-corp
```

To serve these headers in a `KotlinWebpack` browser run, create a new a
new directory called `webpack.config.d` in your project's root,
with a new `whatever.js` inside:

```
config.devServer = config.devServer || {}
config.devServer.headers = config.devServer.headers || {}
config.devServer.headers["Cross-Origin-Opener-Policy"] = "same-origin"
config.devServer.headers["Cross-Origin-Embedder-Policy"] = "require-corp"
```

Both `SharedArrayBuffer` and `Atomics` provide `isSupported()`, which
is a simple `undefined` check for both objects at this point.

## Example

```
import eu.chainfire.kjs.sharedmemory.*
import org.khronos.webgl.*

fun main() {
    val sab = SharedArrayBuffer(Uint32Array.BYTES_PER_ELEMENT * 3)
    val u32 = Uint32Array(sab)
    u32.set(arrayOf(1, 2, 3))
    Atomics.add(u32, 2, 2)
    for (i in u32.entries()) {
        console.log(i.index, i.value)
    }
}
```

## Kotlin DSL

```
repositories {
    maven("https://dl.bintray.com/chainfire/maven")
}

dependencies {
    implementation("eu.chainfire:kotlin-js-sharedmemory:1.0.1")
}
```
