package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastAstroResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.ForeCastAstro
import javax.inject.Inject

class ForecastAstroMapper @Inject constructor() : IEntityMapper<ForeCastAstroResponse, ForeCastAstro> {
    override fun mapToModel(entity: ForeCastAstroResponse): ForeCastAstro {
        return ForeCastAstro(
            sunrise = entity.sunrise?:"",
            sunset = entity.sunset?:"",
            moonrise = entity.moonrise?:"",
            moonset = entity.moonset?:""
        )
    }

    override fun mapToEntity(model: ForeCastAstro): ForeCastAstroResponse {
        TODO("Not yet implemented")
    }

}