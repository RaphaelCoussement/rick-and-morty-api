package org.mathieu.characters.detailsLocation

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.repositories.LocationRepository

class LocationDetailsViewModel(application: Application) : org.mathieu.ui.ViewModel<LocationDetailsState>(
    LocationDetailsState(), application) {

    private val locationRepository: LocationRepository by inject()

    fun init(locationId: Int) {
        fetchData(
            source = { locationRepository.getLocationById(id = locationId) }
        ) {
            onSuccess { location ->
                updateState {
                    copy(
                        name = location.name,
                        type = location.type,
                        residents = location.residents,
                        error = null,
                        isLoading = false
                    )
                }
            }

            onFailure {
                updateState { copy(error = it.toString(), isLoading = false) }
            }
        }
    }
}

data class LocationDetailsState(
    val isLoading: Boolean = true,
    val name: String = "",
    val type: String = "",
    val residents: List<Character> = emptyList(),
    val error: String? = null
)
