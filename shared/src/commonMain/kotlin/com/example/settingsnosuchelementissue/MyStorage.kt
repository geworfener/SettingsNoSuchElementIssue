package com.example.settingsnosuchelementissue

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.serializedValue
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer

class MyStorage {

    private val settings = Settings()

    private companion object {
        private const val KEY_MY_ITEMS = "MY_ITEMS"
    }

    @OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi::class)
    private var _myItems: List<MyItemDto> by settings.serializedValue(
        ListSerializer(MyItemDto.serializer()),
        KEY_MY_ITEMS,
        emptyList<MyItemDto>(),
    )

    fun getMyItems() = _myItems

    fun replaceMyItems(items: List<MyItemDto>) {
        _myItems = items
    }
}

@Serializable
data class MyItemDto(
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: String,
)
