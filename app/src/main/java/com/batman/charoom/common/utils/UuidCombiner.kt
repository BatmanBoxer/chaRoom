package com.batman.charoom.common.utils

fun combineUUIDs(uuid1: String, uuid2: String): String {
    val sortedUUIDs = listOf(uuid1, uuid2).sorted()
    return sortedUUIDs.joinToString(separator = "-")
}
fun splitCombinedUUID(combinedUUID: String): List<String> {
    return combinedUUID.split("-")
}