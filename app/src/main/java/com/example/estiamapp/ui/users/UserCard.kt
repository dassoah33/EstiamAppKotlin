package com.example.estiamapp.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.estiamapp.data.model.UserDto
import com.example.estiamapp.ui.theme.EstiamAppTheme

@Composable
fun UserCard (user: UserDto, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxHeight()) {
        Row(Modifier.padding(12.dp)) {
            AsyncImage(
                model = user.avatar,
                contentDescription = "Avatar",
                modifier = Modifier.size(56.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    user.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    user.email,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    user.role,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "UserCard")
@Composable
private fun UserCardPreview() {
    EstiamAppTheme {
        UserCard(
            user = UserDto(
                id = 1,
                email = "john@gmail.com",
                name = "John Doe",
                password = "test",
                role = "customer",
                avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                creationAt = "2025-11-03T17:19:40.000Z",
                updatedAt = "2025-11-03T17:19:40.000Z"
            )
        )
    }
}