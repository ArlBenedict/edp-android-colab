package com.example.labactivity10.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull
import java.time.Instant

object CreatedAtSerializer : KSerializer<Long> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CreatedAtSerializer", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Long {
        val jsonPrimitive = (decoder as? JsonDecoder)?.decodeJsonElement() as? JsonPrimitive
        if (jsonPrimitive != null) {
            if (jsonPrimitive.isString) {
                val content = jsonPrimitive.content
                return content.toLongOrNull() ?: try {
                    Instant.parse(content).toEpochMilli()
                } catch (e: Exception) {
                    0L
                }
            } else {
                return jsonPrimitive.longOrNull ?: 0L
            }
        }
        return decoder.decodeLong()
    }

    override fun serialize(encoder: Encoder, value: Long) {
        encoder.encodeLong(value)
    }
}

@Serializable
data class MessageDto(
    val id: String? = null,
    val sender: String? = null,
    val text: String? = null,
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: Long? = null
)
