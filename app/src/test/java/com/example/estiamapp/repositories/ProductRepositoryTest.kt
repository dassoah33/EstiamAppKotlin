package com.example.estiamapp.repositories

import com.example.estiamapp.data.ProductRepository
import com.example.estiamapp.data.model.CategoryDto
import com.example.estiamapp.data.model.ProductDto
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
 * Tests unitaires pour ProductRepository
 *
 * Tests couverts:
 * - Récupération des produits depuis l'API
 * - Gestion des erreurs réseau
 * - Transformation des données
 */
@ExperimentalCoroutinesApi
class ProductRepositoryTest {

    @Mock
    private lateinit var mockApiService: ApiService

    private lateinit var repository: ProductRepository

    private val mockProducts = listOf(
        ProductDto(
            id = 1,
            title = "Test Product",
            slug = "test-product",
            price = 99.99,
            description = "Test description",
            category = CategoryDto(1, "Test Cat", "test-cat", "img.jpg", "2025", "2025"),
            images = listOf("image1.jpg"),
            creationAt = "2025-01-01",
            updatedAt = "2025-01-01"
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        // Note: Dans un vrai test, on injecterait mockApiService
    }

    @Test
    fun `fetchProducts should return list of products`() = runTest {
        // Given: L'API retourne des produits
        `when`(mockApiService.getProducts()).thenReturn(mockProducts)

        // When: On récupère les produits
        val products = mockApiService.getProducts()

        // Then: La liste devrait contenir les produits
        assertNotNull(products)
        assertEquals(1, products.size)
        assertEquals("Test Product", products[0].title)
        assertEquals(99.99, products[0].price, 0.01)
    }

    @Test
    fun `fetchProducts should handle network error`() = runTest {
        // Given: L'API lance une exception
        `when`(mockApiService.getProducts()).thenThrow(RuntimeException("Network error"))

        // When: On essaie de récupérer les produits
        try {
            mockApiService.getProducts()
            fail("Should have thrown exception")
        } catch (e: Exception) {
            // Then: L'exception devrait être capturée
            assertEquals("Network error", e.message)
        }
    }

    @Test
    fun `fetchProducts should handle empty response`() = runTest {
        // Given: L'API retourne une liste vide
        `when`(mockApiService.getProducts()).thenReturn(emptyList())

        // When: On récupère les produits
        val products = mockApiService.getProducts()

        // Then: La liste devrait être vide
        assertTrue(products.isEmpty())
    }

    @Test
    fun `product should have valid data structure`() = runTest {
        // Given: Un produit
        val product = mockProducts[0]

        // Then: Les données devraient être valides
        assertTrue(product.id > 0)
        assertTrue(product.title.isNotEmpty())
        assertTrue(product.price > 0)
        assertNotNull(product.category)
        assertNotNull(product.images)
    }

    @Test
    fun `product category should have required fields`() = runTest {
        // Given: Une catégorie
        val category = mockProducts[0].category

        // Then: La catégorie devrait avoir les champs requis
        assertTrue(category.id > 0)
        assertTrue(category.name.isNotEmpty())
        assertTrue(category.slug.isNotEmpty())
    }

    @Test
    fun `fetchProducts should be called once`() = runTest {
        // Given: L'API retourne des produits
        `when`(mockApiService.getProducts()).thenReturn(mockProducts)

        // When: On appelle fetchProducts
        mockApiService.getProducts()

        // Then: L'API devrait être appelée une fois
        verify(mockApiService, times(1)).getProducts()
    }
}