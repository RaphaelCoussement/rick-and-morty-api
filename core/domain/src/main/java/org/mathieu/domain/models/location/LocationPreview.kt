package org.mathieu.domain.models.location

/**
 * Represents a preview of a location, containing essential details.
 *
 * @property id The unique identifier for the location.
 * @property name The name of the location.
 * @property type The type or category of the location.
 * @property dimension The specific dimension or universe where this location exists.
 */
class LocationPreview (
    val id: Int,
    val name: String,
    val type: String? = null,
    val dimension: String? = null
)