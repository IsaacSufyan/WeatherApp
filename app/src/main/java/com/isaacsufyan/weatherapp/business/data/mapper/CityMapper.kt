package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.CityResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.City
import javax.inject.Inject

class CityMapper @Inject constructor() : IEntityMapper<CityResponse, City> {
    override fun mapToModel(entity: CityResponse): City {
        return City(
            cityName = entity.cityName,
            country = entity.country,
            lat = entity.lat,
            lng = entity.lng,
        )
    }

    override fun mapToEntity(model: City): CityResponse {
        TODO("Not yet implemented")
    }

}