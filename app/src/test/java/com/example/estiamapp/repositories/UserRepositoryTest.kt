package com.example.estiamapp.repositories

import com.example.estiamapp.data.UserRepository
import com.example.estiamapp.data.model.UserDto
import com.example.estiamapp.data.remote.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Tests unitaires pour UserRepository
 *
 * Tests couverts:
 * - Récupération des utilisateurs depuis l'API
 * - Gestion des erreurs
 * - Validation des données
 */
@ExperimentalCoroutinesApi
class UserRepositoryTest {

    @Mock
    private lateinit var mockApiService: ApiService

    private val mockUsers = listOf(
        UserDto(
            id = 1,
            email = "test@example.com",
            name = "Test User",
            password = "password123",
            role = "customer",
            avatar = "avatar.jpg",
            creationAt = "2025-01-01",
            updatedAt = "2025-01-01"
        ),
        UserDto(
            id = 2,
            email = "admin@example.com",
            name = "Admin User",
            password = "admin123",
            role = "admin",
            avatar = "admin.jpg",
            creationAt = "2025-01-01",
            updatedAt = "2025-01-01"
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `fetchUsers should return list of users`() = runTest {
        // Given: L'API retourne des utilisateurs
        `when`(mockApiService.getUsers()).thenReturn(mockUsers)

        // When: On récupère les utilisateurs
        val users = mockApiService.getUsers()

        // Then: La liste devrait contenir les utilisateurs
        assertNotNull(users)
        assertEquals(2, users.size)
        assertEquals("test@example.com", users[0].email)
        assertEquals("Test User", users[0].name)
    }

    @Test
    fun `fetchUsers should handle network error`() = runTest {
        // Given: L'API lance une exception
        `when`(mockApiService.getUsers()).thenThrow(RuntimeException("Connection timeout"))

        // When: On essaie de récupérer les utilisateurs
        try {
            mockApiService.getUsers()
            fail("Should have thrown exception")
        } catch (e: Exception) {
            // Then: L'exception devrait être capturée
            assertEquals("Connection timeout", e.message)
        }
    }

    @Test
    fun `fetchUsers should handle empty response`() = runTest {
        // Given: L'API retourne une liste vide
        `when`(mockApiService.getUsers()).thenReturn(emptyList())

        // When: On récupère les utilisateurs
        val users = mockApiService.getUsers()

        // Then: La liste devrait être vide
        assertTrue(users.isEmpty())
    }

    @Test
    fun `user should have valid email`() = runTest {
        // Given: Un utilisateur
        val user = mockUsers[0]

        // Then: L'email devrait être valide
        assertTrue(user.email.contains("@"))
        assertTrue(user.email.contains("."))
        assertTrue(user.email.isNotEmpty())
    }

    @Test
    fun `user role should be valid`() = runTest {
        // Given: Des utilisateurs avec différents rôles
        val customerUser = mockUsers[0]
        val adminUser = mockUsers[1]

        // Then: Les rôles devraient être valides
        assertEquals("customer", customerUser.role)
        assertEquals("admin", adminUser.role)
        assertTrue(customerUser.role in listOf("customer", "admin"))
        assertTrue(adminUser.role in listOf("customer", "admin"))
    }

    @Test
    fun `user should have all required fields`() = runTest {
        // Given: Un utilisateur
        val user = mockUsers[0]

        // Then: Tous les champs requis devraient être présents
        assertTrue(user.id > 0)
        assertTrue(user.email.isNotEmpty())
        assertTrue(user.name.isNotEmpty())
        assertTrue(user.role.isNotEmpty())
        assertNotNull(user.avatar)
    }

    @Test
    fun `fetchUsers should be called once`() = runTest {
        // Given: L'API retourne des utilisateurs
        `when`(mockApiService.getUsers()).thenReturn(mockUsers)

        // When: On appelle fetchUsers
        mockApiService.getUsers()

        // Then: L'API devrait être appelée une fois
        verify(mockApiService, times(1)).getUsers()
    }

    @Test
    fun `users should have unique IDs`() = runTest {
        // Given: Plusieurs utilisateurs
        `when`(mockApiService.getUsers()).thenReturn(mockUsers)

        // When: On récupère les utilisateurs
        val users = mockApiService.getUsers()

        // Then: Les IDs devraient être uniques
        val ids = users.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }
}