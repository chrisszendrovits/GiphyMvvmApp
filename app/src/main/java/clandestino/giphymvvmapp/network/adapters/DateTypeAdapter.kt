package clandestino.giphymvvmapp.network.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class DateTypeAdapter: TypeAdapter<Date?>()  {

    val datePattern = "yyyy-MM-dd HH:mm:ss"
    val dateFormatter = SimpleDateFormat(datePattern)

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Date?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(dateFormatter.format(value))
        }
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Date? {
        val token: JsonToken = reader.peek()
        return when (token) {
            JsonToken.NULL -> {
                reader.nextNull()
                null
            }
            JsonToken.STRING -> {
                dateFormatter.parse(reader.nextString())
            }
            else -> throw IllegalStateException("Expected formated String but was $token")
        }
    }
}