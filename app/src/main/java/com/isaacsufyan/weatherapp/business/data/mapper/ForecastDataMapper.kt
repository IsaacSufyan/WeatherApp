package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastDataResponse
import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastDayResponse
import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastAstroResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.ForeCastData
import javax.inject.Inject

class ForecastDataMapper @Inject constructor(
    private val forecastDayMapper: ForecastDayMapper,
    private val forecastAstroMapper: ForecastAstroMapper,
    private val forecastHoursMapper: ForecastHoursMapper
) : IEntityMapper<ForeCastDataResponse, ForeCastData> {
    override fun mapToModel(entity: ForeCastDataResponse): ForeCastData {
        return ForeCastData(
            day = forecastDayMapper.mapToModel(entity.day ?: ForeCastDayResponse()),
            astro = forecastAstroMapper.mapToModel(entity.astro ?: ForeCastAstroResponse()),
            listOfHours = entity.hour?.map { forecastHoursMapper.mapToModel(it) }?: emptyList()
        )
    }

    override fun mapToEntity(model: ForeCastData): ForeCastDataResponse {
        TODO("Not yet implemented")
    }

}