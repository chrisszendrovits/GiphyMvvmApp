package clandestino.giphymvvmapp.network.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import com.google.gson.stream.JsonToken.BOOLEAN
import com.google.gson.stream.JsonToken.NULL
import com.google.gson.stream.JsonToken.NUMBER
import com.google.gson.stream.JsonToken.STRING

class BooleanTypeAdapter: TypeAdapter<Boolean?>() {
    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Boolean?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(value)
        }
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Boolean? {
        val token: JsonToken = reader.peek()
        return when (token) {
            NULL -> {
                reader.nextNull()
                null
            }
            BOOLEAN -> reader.nextBoolean()
            NUMBER -> reader.nextInt() !== 0
            STRING -> java.lang.Boolean.parseBoolean(reader.nextString())
            else -> throw IllegalStateException("Expected BOOLEAN or NUMBER but was $token")
        }
    }
}