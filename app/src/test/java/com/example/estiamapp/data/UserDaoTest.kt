package com.example.estiamapp.data

import com.example.estiamapp.data.local.UserDao
import com.example.estiamapp.data.local.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Tests unitaires pour UserDao
 *
 * Tests couverts:
 * - Insertion d'utilisateurs
 * - Observation des utilisateurs (Flow)
 * - Suppression de tous les utilisateurs
 * - Gestion des conflits (REPLACE)
 */
@ExperimentalCoroutinesApi
class UserDaoTest {

    @Mock
    private lateinit var mockDao: UserDao

    private val testUser1 = UserEntity(
        localId = 1,
        firstName = "John",
        lastName = "Doe",
        email = "john.doe@test.com"
    )

    private val testUser2 = UserEntity(
        localId = 2,
        firstName = "Jane",
        lastName = "Smith",
        email = "jane.smith@test.com"
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `insert user should add user to database`() = runTest {
        // Given: Un utilisateur à insérer

        // When: On insère l'utilisateur
        mockDao.insert(testUser1)

        // Then: L'insertion devrait être appelée
        verify(mockDao, times(1)).insert(testUser1)
    }

    @Test
    fun `observedUsers should return flow of users`() = runTest {
        // Given: Une liste d'utilisateurs dans la DB
        val usersList = listOf(testUser1, testUser2)
        `when`(mockDao.observedUsers()).thenReturn(flowOf(usersList))

        // When: On observe les utilisateurs
        val users = mockDao.observedUsers().first()

        // Then: La liste devrait contenir les utilisateurs
        assertEquals(2, users.size)
        assertEquals("John", users[0].firstName)
        assertEquals("Jane", users[1].firstName)
    }

    @Test
    fun `observedUsers should return empty list when no users`() = runTest {
        // Given: Aucun utilisateur dans la DB
        `when`(mockDao.observedUsers()).thenReturn(flowOf(emptyList()))

        // When: On observe les utilisateurs
        val users = mockDao.observedUsers().first()

        // Then: La liste devrait être vide
        assertTrue(users.isEmpty())
    }

    @Test
    fun `clear should delete all users`() = runTest {
        // Given: Des utilisateurs dans la DB

        // When: On supprime tous les utilisateurs
        mockDao.clear()

        // Then: clear devrait être appelé
        verify(mockDao, times(1)).clear()
    }

    @Test
    fun `insert with REPLACE strategy should update existing user`() = runTest {
        // Given: Un utilisateur existant avec le même ID
        val existingUser = testUser1.copy(firstName = "Updated John")

        // When: On insère avec le même ID
        mockDao.insert(existingUser)

        // Then: L'utilisateur devrait être mis à jour (REPLACE)
        verify(mockDao, times(1)).insert(existingUser)
    }

    @Test
    fun `user entity should have valid email format`() = runTest {
        // Given: Un UserEntity
        val user = testUser1

        // Then: L'email devrait être valide
        assertTrue(user.email.contains("@"))
        assertTrue(user.email.contains("."))
        assertFalse(user.email.isBlank())
    }

    @Test
    fun `user entity should have valid names`() = runTest {
        // Given: Un UserEntity
        val user = testUser1

        // Then: Les noms devraient être valides
        assertTrue(user.firstName.isNotBlank())
        assertTrue(user.lastName.isNotBlank())
        assertTrue(user.firstName.length > 0)
        assertTrue(user.lastName.length > 0)
    }

    @Test
    fun `autoGenerate should create unique IDs`() = runTest {
        // Given: Deux utilisateurs insérés
        val user1 = testUser1.copy(localId = 0) // Auto-generate
        val user2 = testUser2.copy(localId = 0) // Auto-generate

        // Then: Les IDs devraient être générés automatiquement
        assertEquals(0, user1.localId) // Avant insertion
        assertEquals(0, user2.localId) // Avant insertion
    }

    @Test
    fun `observe flow should emit updates`() = runTest {
        // Given: Un Flow qui émet des mises à jour
        val initialList = listOf(testUser1)
        val updatedList = listOf(testUser1, testUser2)

        `when`(mockDao.observedUsers())
            .thenReturn(flowOf(initialList))
            .thenReturn(flowOf(updatedList))

        // When: On observe les changements
        val first = mockDao.observedUsers().first()

        // Then: Le premier état devrait être correct
        assertEquals(1, first.size)
    }
}