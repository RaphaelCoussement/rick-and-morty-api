package org.mathieu.characters.detailsLocation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage

private typealias UIState = LocationDetailsState

@Composable
fun LocationDetailsScreen(
    navController: NavController,
    locationId: Int // Changer ici pour Int
) {
    val viewModel: LocationDetailsViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    // Appel à init pour charger les données uniquement lors du premier rendu
    LaunchedEffect(locationId) { // Changer locationName par locationId
        viewModel.init(locationId) // Passer locationId ici
    }

    LocationDetailsContent(
        state = state,
        onClickBack = navController::popBackStack
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun LocationDetailsContent(
    state: UIState = UIState(),
    onClickBack: () -> Unit = { }
) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text("Location Details") },
            navigationIcon = {
                IconButton(onClick = onClickBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }
) { paddingValues ->
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues), contentAlignment = Alignment.Center) {
        AnimatedContent(targetState = state.error != null, label = "") {
            state.error?.let { error ->
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = error,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 36.sp
                )
            } ?: Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Location: ${state.name}", style = MaterialTheme.typography.headlineMedium)
                    Text(text = "Type: ${state.type}", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Residents:", style = MaterialTheme.typography.headlineSmall)

                    state.residents.forEach { resident ->
                        Text(
                            text = "${resident.name}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
