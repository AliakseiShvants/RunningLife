package com.shvants.runninglife.mocks

import org.junit.Assert
import java.io.InputStream

class Mocks {

    fun stream(name: String): InputStream {
        val resourceAsStream = object {}.javaClass.classLoader.getResourceAsStream(name)
        Assert.assertNotNull("resource not found, maybe you forget add .json?", resourceAsStream)

        return resourceAsStream
    }
}