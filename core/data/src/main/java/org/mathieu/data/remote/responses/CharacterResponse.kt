package org.mathieu.data.remote.responses

import kotlinx.serialization.Serializable

/**
 * Represents detailed information about a character, typically received from an API response.
 *
 * @property id The unique identifier for the character.
 * @property name The name of the character.
 * @property status The life status of the character - can be 'Alive', 'Dead', or 'unknown'.
 * @property species The species to which the character belongs.
 * @property type The specific subspecies or type of the character.
 * @property gender The gender of the character - can be 'Female', 'Male', 'Genderless', or 'unknown'.
 * @property origin Information about the character's origin, including name and link to the origin location.
 * @property location Information about the character's last known location, including name and link.
 * @property image A URL pointing to an image of the character. Typically 300x300px, suitable for use as an avatar.
 * @property episode A list of episodes in which the character has appeared.
 * @property url The unique URL endpoint specifically for this character.
 * @property created The timestamp indicating when the character was added to the database.
 * @property locationPreviews A list of previews for the locations visited by the character.
 */
@Serializable
internal data class CharacterResponse(
    val id:	Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationResponse,
    val location: CharacterLocationResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

/**
 * Represents the origin or location information in a character response.
 *
 * @property name The name of the location (origin or current location).
 * @property url The URL of the location (origin or current location).
 */
@Serializable
internal data class CharacterLocationResponse(val name: String, val url: String)

/**
 * Represents a location preview in the API response.
 *
 * @property id The unique identifier of the location.
 * @property name The name of the location.
 * @property type The type or category of the location.
 * @property dimension The dimension in which this location exists.
 */
@Serializable
internal data class LocationPreviewResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
