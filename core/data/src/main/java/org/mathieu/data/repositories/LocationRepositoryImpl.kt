package org.mathieu.data.repositories

import android.util.Log
import org.mathieu.data.remote.LocationAPI
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository

/**
 * Fetches a location by its ID and retrieves its residents' characters.
 *
 * This function operates as follows:
 * 1. Calls the API to get the location data based on the provided ID.
 * 2. Extracts the list of resident character URLs from the API response.
 * 3. Maps the URLs to their corresponding character IDs and fetches each character from the CharacterRepository.
 * 4. Constructs a Location object using the fetched location details and the list of resident characters.
 *
 * @param id The ID of the location to be fetched.
 * @return A Location object containing the location's details and its residents.
 */
class LocationRepositoryImpl(
    private val locationAPI: LocationAPI,
    private val characterRepository: CharacterRepository
) : LocationRepository {
    override suspend fun getLocationById(id: Int): Location {

        // get the specific location linked to the API
        val locationResponse = locationAPI.getLocationById(id)

        Log.d("LocationRepository", "Fetched locationResponse: $locationResponse")

        val residents: List<Character> = locationResponse.residents.map { characterUrl ->
            val characterId = characterUrl.substringAfterLast("/").toInt()
            // get the character with his identifier
            characterRepository.getCharacter(characterId)
        }

        return Location(
            id = locationResponse.id,
            name = locationResponse.name,
            type = locationResponse.type,
            dimension = locationResponse.dimension,
            residents = residents
        )
    }
}
