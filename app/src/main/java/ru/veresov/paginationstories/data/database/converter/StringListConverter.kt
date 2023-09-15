package ru.veresov.paginationstories.data.database.converter

import androidx.room.TypeConverter

/**
 * @author Veresov Yuriy
 * @date 15.09.2023
 */

class StringListConverter {
    @TypeConverter
    fun fromList(stringList: List<String>?): String? {
        return stringList?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(string: String?): List<String>? {
        return string?.split(",")?.map { it.trim() }
    }
}
