package com.example.estiamapp.ui.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estiamapp.data.model.CategoryDto
import com.example.estiamapp.data.model.ProductDto
import com.example.estiamapp.data.model.UserDto
import com.example.estiamapp.ui.products.ProductCard
import com.example.estiamapp.ui.theme.EstiamAppTheme
import com.example.estiamapp.ui.users.UserCard

/**
 * Previews pour tous les écrans principaux
 * Conformément au sujet : au moins 2 previews par écran (Light/Dark, Phone/Tablet)
 */

// ============ PRODUCT CARD PREVIEWS ============

@Preview(
    name = "ProductCard - Light - Phone",
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun ProductCardLightPhonePreview() {
    EstiamAppTheme(darkTheme = false) {
        ProductCard(
            product = ProductDto(
                id = 1,
                title = "Wireless Headphones Premium",
                slug = "wireless-headphones",
                price = 149.99,
                description = "High-quality wireless headphones with noise cancellation and 40h battery life",
                category = CategoryDto(2, "Electronics", "electronics", "img.jpg", "2025", "2025"),
                images = emptyList(),
                creationAt = "2025-01-01",
                updatedAt = "2025-01-01"
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview(
    name = "ProductCard - Dark - Phone",
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun ProductCardDarkPhonePreview() {
    EstiamAppTheme(darkTheme = true) {
        ProductCard(
            product = ProductDto(
                id = 1,
                title = "Wireless Headphones Premium",
                slug = "wireless-headphones",
                price = 149.99,
                description = "High-quality wireless headphones with noise cancellation and 40h battery life",
                category = CategoryDto(2, "Electronics", "electronics", "img.jpg", "2025", "2025"),
                images = emptyList(),
                creationAt = "2025-01-01",
                updatedAt = "2025-01-01"
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview(
    name = "ProductCard - Light - Tablet",
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun ProductCardLightTabletPreview() {
    EstiamAppTheme(darkTheme = false) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductCard(
                product = ProductDto(
                    id = 1,
                    title = "Wireless Headphones",
                    slug = "wireless-headphones",
                    price = 149.99,
                    description = "Premium audio quality",
                    category = CategoryDto(2, "Electronics", "electronics", "img.jpg", "2025", "2025"),
                    images = emptyList(),
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
            ProductCard(
                product = ProductDto(
                    id = 2,
                    title = "Smart Watch Pro",
                    slug = "smart-watch",
                    price = 299.99,
                    description = "Advanced fitness tracking",
                    category = CategoryDto(2, "Electronics", "electronics", "img.jpg", "2025", "2025"),
                    images = emptyList(),
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    name = "ProductCard - Dark - Tablet",
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun ProductCardDarkTabletPreview() {
    EstiamAppTheme(darkTheme = true) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductCard(
                product = ProductDto(
                    id = 1,
                    title = "Wireless Headphones",
                    slug = "wireless-headphones",
                    price = 149.99,
                    description = "Premium audio quality",
                    category = CategoryDto(2, "Electronics", "electronics", "img.jpg", "2025", "2025"),
                    images = emptyList(),
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
            ProductCard(
                product = ProductDto(
                    id = 2,
                    title = "Smart Watch Pro",
                    slug = "smart-watch",
                    price = 299.99,
                    description = "Advanced fitness tracking",
                    category = CategoryDto(2, "Electronics", "electronics", "img.jpg", "2025", "2025"),
                    images = emptyList(),
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// ============ USER CARD PREVIEWS ============

@Preview(
    name = "UserCard - Light - Phone",
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun UserCardLightPhonePreview() {
    EstiamAppTheme(darkTheme = false) {
        UserCard(
            user = UserDto(
                id = 1,
                email = "john.doe@example.com",
                name = "John Doe",
                password = "password",
                role = "customer",
                avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                creationAt = "2025-01-01",
                updatedAt = "2025-01-01"
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview(
    name = "UserCard - Dark - Phone",
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun UserCardDarkPhonePreview() {
    EstiamAppTheme(darkTheme = true) {
        UserCard(
            user = UserDto(
                id = 1,
                email = "john.doe@example.com",
                name = "John Doe",
                password = "password",
                role = "customer",
                avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                creationAt = "2025-01-01",
                updatedAt = "2025-01-01"
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview(
    name = "UserCard - Light - Tablet",
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun UserCardLightTabletPreview() {
    EstiamAppTheme(darkTheme = false) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserCard(
                user = UserDto(
                    id = 1,
                    email = "john@example.com",
                    name = "John Doe",
                    password = "password",
                    role = "customer",
                    avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
            UserCard(
                user = UserDto(
                    id = 2,
                    email = "jane@example.com",
                    name = "Jane Smith",
                    password = "password",
                    role = "admin",
                    avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    name = "UserCard - Dark - Tablet",
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun UserCardDarkTabletPreview() {
    EstiamAppTheme(darkTheme = true) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserCard(
                user = UserDto(
                    id = 1,
                    email = "john@example.com",
                    name = "John Doe",
                    password = "password",
                    role = "customer",
                    avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
            UserCard(
                user = UserDto(
                    id = 2,
                    email = "jane@example.com",
                    name = "Jane Smith",
                    password = "password",
                    role = "admin",
                    avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                    creationAt = "2025-01-01",
                    updatedAt = "2025-01-01"
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// ============ FORM PREVIEWS ============

@Preview(
    name = "User Form - Light - Phone",
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun UserFormLightPreview() {
    EstiamAppTheme(darkTheme = false) {
        Card(Modifier.padding(16.dp)) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Add User", style = MaterialTheme.typography.titleLarge)
                Text("First Name", style = MaterialTheme.typography.labelMedium)
                Text("Last Name", style = MaterialTheme.typography.labelMedium)
                Text("Email", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Preview(
    name = "User Form - Dark - Phone",
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun UserFormDarkPreview() {
    EstiamAppTheme(darkTheme = true) {
        ElevatedCard(Modifier.padding(16.dp)) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Add User", style = MaterialTheme.typography.titleLarge)
                Text("First Name", style = MaterialTheme.typography.labelMedium)
                Text("Last Name", style = MaterialTheme.typography.labelMedium)
                Text("Email", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}