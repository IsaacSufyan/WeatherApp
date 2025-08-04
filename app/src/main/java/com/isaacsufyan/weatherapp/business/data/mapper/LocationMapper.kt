package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.LocationResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.Location
import javax.inject.Inject

class LocationMapper @Inject constructor(): IEntityMapper<LocationResponse, Location> {

    override fun mapToModel(entity: LocationResponse): Location {
        return Location(
            name = entity.name ?: "", // Provide a default value for name if it's null
            region = entity.region ?: "", // Provide a default value for region if it's null
            country = entity.country ?: "", // Provide a default value for country if it's null
            lat = entity.lat ?: 0.0, // Provide a default value for lat if it's null
            lon = entity.lon ?: 0.0, // Provide a default value for lon if it's null
            tzId = entity.tzId ?: "", // Provide a default value for tzId if it's null
            localTimeEpoch = entity.localTimeEpoch ?: 0L, // Provide a default value for localTimeEpoch if it's null
            localtime = entity.localtime ?: "" // Provide a default value for localtime if it's null
        )
    }

    override fun mapToEntity(model: Location): LocationResponse {
        TODO("Not yet implemented")
    }

}