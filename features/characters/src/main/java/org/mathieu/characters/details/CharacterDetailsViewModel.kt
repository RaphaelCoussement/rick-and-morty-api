package org.mathieu.characters.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository
import org.mathieu.ui.ViewModel

class CharacterDetailsViewModel(application: Application) : ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

    private val characterRepository: CharacterRepository by inject()
    private val locationRepository: LocationRepository by inject()

    fun init(characterId: Int) {
        fetchData(
            source = { characterRepository.getCharacter(id = characterId) }
        ) {
            onSuccess { character ->
                updateState {
                    copy(
                        avatarUrl = character.avatarUrl,
                        name = character.name,
                        error = null,
                        locationName = "",
                        locationType = "",
                        locationId = character.location.second
                    )
                }
                // Get location's details
                fetchLocationDetails(character.location.second)
            }

            onFailure {
                val errorMessage = it.toString()
                Log.e("CharacterDetailsViewModel", "Error fetching character details: $errorMessage")
                updateState { copy(error = errorMessage) }

            }

            updateState { copy(isLoading = false) }
        }
    }

    private fun fetchLocationDetails(locationId: Int) {
        viewModelScope.launch {
            try {
                val location: Location = locationRepository.getLocationById(locationId)
                Log.d("CharacterDetailsViewModel", "Fetched location: $location")
                updateState {
                    copy(
                        locationName = location.name,
                        locationType = location.type
                    )
                }
            } catch (e: Exception) {
                Log.e("CharacterDetailsViewModel", "Error fetching location details: ${e.message}")
                updateState { copy(error = e.toString()) }
            }
        }
    }

}

data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val error: String? = null,
    val locationName: String = "",
    val locationType: String = "",
    val locationId: Int? = null
)
