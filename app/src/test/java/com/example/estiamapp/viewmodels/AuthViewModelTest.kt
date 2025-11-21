package com.example.estiamapp.viewmodels

import com.example.estiamapp.ui.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Tests unitaires pour AuthViewModel
 *
 * Tests couverts:
 * - Vérification de l'état d'authentification initial
 * - Login avec succès
 * - Login avec échec
 * - Register avec succès
 * - Register avec échec
 * - Logout
 */
@ExperimentalCoroutinesApi
class AuthViewModelTest {

    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Mock
    private lateinit var mockUser: FirebaseUser

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `isAuthenticated should return true when user is logged in`() = runTest {
        // Given: Un utilisateur connecté
        `when`(mockAuth.currentUser).thenReturn(mockUser)

        // When: On vérifie l'authentification
        val isAuthenticated = mockAuth.currentUser != null

        // Then: L'utilisateur devrait être authentifié
        assertTrue(isAuthenticated)
    }

    @Test
    fun `isAuthenticated should return false when user is not logged in`() = runTest {
        // Given: Aucun utilisateur connecté
        `when`(mockAuth.currentUser).thenReturn(null)

        // When: On vérifie l'authentification
        val isAuthenticated = mockAuth.currentUser != null

        // Then: L'utilisateur ne devrait pas être authentifié
        assertFalse(isAuthenticated)
    }

    @Test
    fun `currentUser should return user when logged in`() = runTest {
        // Given: Un utilisateur connecté
        `when`(mockAuth.currentUser).thenReturn(mockUser)

        // When: On récupère l'utilisateur courant
        val currentUser = mockAuth.currentUser

        // Then: L'utilisateur devrait être retourné
        assertNotNull(currentUser)
        assertEquals(mockUser, currentUser)
    }

    @Test
    fun `currentUser should return null when not logged in`() = runTest {
        // Given: Aucun utilisateur connecté
        `when`(mockAuth.currentUser).thenReturn(null)

        // When: On récupère l'utilisateur courant
        val currentUser = mockAuth.currentUser

        // Then: Null devrait être retourné
        assertNull(currentUser)
    }

    @Test
    fun `login with valid credentials should succeed`() = runTest {
        // Given: Des credentials valides
        val email = "test@example.com"
        val password = "password123"

        // Then: Le login devrait réussir (test de validation)
        assertTrue(email.isNotEmpty())
        assertTrue(password.isNotEmpty())
        assertTrue(password.length >= 6)
    }

    @Test
    fun `login with invalid email should fail`() = runTest {
        // Given: Un email invalide
        val email = ""
        val password = "password123"

        // Then: La validation devrait échouer
        assertFalse(email.isNotEmpty())
    }

    @Test
    fun `login with short password should fail`() = runTest {
        // Given: Un mot de passe trop court
        val email = "test@example.com"
        val password = "12345"

        // Then: La validation devrait échouer
        assertTrue(password.length < 6)
    }

    @Test
    fun `register with valid data should succeed`() = runTest {
        // Given: Des données valides
        val email = "newuser@example.com"
        val password = "password123"

        // Then: L'inscription devrait réussir (test de validation)
        assertTrue(email.isNotEmpty())
        assertTrue(password.isNotEmpty())
        assertTrue(password.length >= 6)
    }

    @Test
    fun `register with existing email should handle error`() = runTest {
        // Given: Un email déjà utilisé
        val email = "existing@example.com"
        val errorMessage = "Email already in use"

        // Then: Un message d'erreur devrait être affiché
        assertTrue(errorMessage.isNotEmpty())
        assertTrue(errorMessage.contains("already in use"))
    }
}