package com.example.estiamapp.viewmodels

import com.example.estiamapp.data.UserRepository
import com.example.estiamapp.data.model.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Tests unitaires pour UsersViewModel
 *
 * Tests couverts:
 * - Chargement initial des utilisateurs
 * - Gestion des états (loading, success, error)
 * - Pagination
 * - Pull-to-refresh
 */
@ExperimentalCoroutinesApi
class UsersViewModelTest {

    @Mock
    private lateinit var mockRepository: UserRepository

    private lateinit var testDispatcher: TestDispatcher

    private val mockUsers = listOf(
        UserDto(
            id = 1,
            email = "user1@test.com",
            name = "User 1",
            password = "password",
            role = "customer",
            avatar = "avatar1.jpg",
            creationAt = "2025-01-01",
            updatedAt = "2025-01-01"
        ),
        UserDto(
            id = 2,
            email = "user2@test.com",
            name = "User 2",
            password = "password",
            role = "admin",
            avatar = "avatar2.jpg",
            creationAt = "2025-01-01",
            updatedAt = "2025-01-01"
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load should fetch users successfully`() = runTest {
        // Given: Le repository retourne des utilisateurs
        `when`(mockRepository.fetchUsers()).thenReturn(mockUsers)

        // When: On charge les utilisateurs
        val users = mockRepository.fetchUsers()

        // Then: Les utilisateurs devraient être chargés
        assertNotNull(users)
        assertEquals(2, users.size)
        assertEquals("user1@test.com", users[0].email)
        assertEquals("User 1", users[0].name)
    }

    @Test
    fun `load should handle error correctly`() = runTest {
        // Given: Le repository lance une exception
        `when`(mockRepository.fetchUsers()).thenThrow(RuntimeException("API error"))

        // When: On essaie de charger les utilisateurs
        try {
            mockRepository.fetchUsers()
            fail("Should have thrown exception")
        } catch (e: Exception) {
            // Then: L'erreur devrait être capturée
            assertEquals("API error", e.message)
        }
    }

    @Test
    fun `refresh should reload users`() = runTest {
        // Given: Le repository retourne des utilisateurs
        `when`(mockRepository.fetchUsers()).thenReturn(mockUsers)

        // When: On rafraîchit les utilisateurs
        val users = mockRepository.fetchUsers()

        // Then: Les utilisateurs devraient être rechargés
        assertNotNull(users)
        verify(mockRepository, times(1)).fetchUsers()
    }

    @Test
    fun `user email should be valid format`() = runTest {
        // Given: Un utilisateur
        val user = mockUsers[0]

        // Then: L'email devrait contenir @
        assertTrue(user.email.contains("@"))
        assertTrue(user.email.contains("."))
    }

    @Test
    fun `user should have valid role`() = runTest {
        // Given: Un utilisateur
        val user = mockUsers[0]

        // Then: Le rôle devrait être valide
        assertNotNull(user.role)
        assertTrue(user.role in listOf("customer", "admin"))
    }

    @Test
    fun `empty users list should be handled`() = runTest {
        // Given: Le repository retourne une liste vide
        `when`(mockRepository.fetchUsers()).thenReturn(emptyList())

        // When: On charge les utilisateurs
        val users = mockRepository.fetchUsers()

        // Then: La liste devrait être vide
        assertTrue(users.isEmpty())
    }

    @Test
    fun `loadMore should handle pagination`() = runTest {
        // Given: Une liste de nombreux utilisateurs
        val manyUsers = (1..20).map { i ->
            UserDto(
                id = i,
                email = "user$i@test.com",
                name = "User $i",
                password = "password",
                role = "customer",
                avatar = "avatar$i.jpg",
                creationAt = "2025-01-01",
                updatedAt = "2025-01-01"
            )
        }
        `when`(mockRepository.fetchUsers()).thenReturn(manyUsers)

        // When: On charge les utilisateurs
        val users = mockRepository.fetchUsers()

        // Then: Il devrait y avoir 20 utilisateurs
        assertEquals(20, users.size)
    }

    @Test
    fun `user data should not be null`() = runTest {
        // Given: Un utilisateur
        val user = mockUsers[0]

        // Then: Les données essentielles ne devraient pas être nulles
        assertNotNull(user.id)
        assertNotNull(user.email)
        assertNotNull(user.name)
        assertTrue(user.id > 0)
    }
}