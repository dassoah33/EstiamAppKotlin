package com.example.estiamapp.ui.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estiamapp.data.model.CategoryDto
import com.example.estiamapp.data.model.ProductDto
import com.example.estiamapp.ui.theme.EstiamAppTheme

@Composable
fun ProductCard (product: ProductDto, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxHeight()) {
        Row(Modifier.padding(12.dp)) {
            Column(Modifier.weight(2f)) {
                Text(
                    product.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    product.price.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "ProductCard")
@Composable
private fun ProductCardPreview() {
    EstiamAppTheme {
        ProductCard(
            product = ProductDto(
                id = 1,
                title = "Immerse yourself in superior sound quality with these sleek red and silver over-ear headphones. Designed for comfort and style, the headphones feature cushioned ear cups, an adjustable padded headband, and a detachable red cable for easymmerse yourself in superior sound quality with these sleek red and silver over-ear headphones. Designed for comfort and style, the headphones feature cushioned ear cups, an adjustable padded headband, and a detachable red cable for easy",
                slug = "Immerse-yourself-in-superior-sound-quality-with-these-sleek-red-and-silver-over-ear-headphones-designed-for-comfort-and-style-the-headphones-feature-cushioned-ear-cups-an-adjustable-padded-headband-and-a-detachable-red-cable-for-easymmerse-yourself-in-superior-sound-quality-with-these-sleek-red-and-silver-over-ear-headphones-designed-for-comfort-and-style-the-headphones-feature-cushioned-ear-cups-an-adjustable-padded-headband-and-a-detachable-red-cable-for-easy",
                price = 12.0,
                description = "Immerse yourself in superior sound quality with these sleek red and silver over-ear headphones. Designed for comfort and style, the headphones feature cushioned ear cups, an adjustable padded headband, and a detachable red cable for easy storage and portability. Perfect for music lovers and audiophiles who value both appearance and audio fidelity.",
                category = CategoryDto(
                    id = 2,
                    name = "Electronics",
                    slug = "electronics",
                    image = "https://i.imgur.com/ZANVnHE.jpeg",
                    creationAt = "2025-11-03T17:19:40.000Z",
                    updatedAt = "2025-11-03T17:19:40.000Z"
                ),
                images = listOf("https://placehold.co/600x400/EEE/31343C"),
                creationAt = "2025-11-03T17:19:40.000Z",
                updatedAt = "2025-11-04T12:57:57.000Z"
            )
        )
    }
}