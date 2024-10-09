package org.mathieu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import org.mathieu.data.remote.responses.LocationResponse

class LocationAPI(private val client: HttpClient) {

    /**
     * Fetches the location details based on the ID provided.
     *
     * @param id The unique identifier of the location to fetch.
     * @return The [LocationResponse] containing location details.
     * @throws HttpException if the request fails or if the status code is not [HttpStatusCode.OK].
     */
    suspend fun getLocationById(id: Int): LocationResponse = client
        .get("location/$id")
        .accept(HttpStatusCode.OK)
        .body()

}
