package com.example.estiamapp.data

import com.example.estiamapp.data.local.UserEntity
import com.example.estiamapp.data.mappers.toFirestoreUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository (
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    private val users = db.collection("users")

    suspend fun addUser(user: UserEntity) {
        val uid = auth.currentUser?.uid ?: error("User not authenticated")

        val docId = "${uid}_${user.email.lowercase()}"

        users.document(docId)
            .set(user.toFirestoreUser(ownerUid = uid))
            .await()
    }
}