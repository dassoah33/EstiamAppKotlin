package com.example.estiamapp.data.mappers

import com.example.estiamapp.data.local.UserEntity

data class FirestoreUser (
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val ownerUid: String = "",
    val localId: Long? = null,
    val createAtMs: Long = System.currentTimeMillis(),
    val source: String = "local"
)

fun UserEntity.toFirestoreUser(ownerUid: String): FirestoreUser =
    FirestoreUser(
        firstName = firstName,
        lastName = lastName,
        email = email,
        ownerUid = ownerUid,
        localId = localId
    )