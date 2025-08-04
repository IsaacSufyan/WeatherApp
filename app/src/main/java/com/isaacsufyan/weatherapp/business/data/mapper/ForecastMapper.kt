package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.ForeCast
import javax.inject.Inject

class ForecastMapper @Inject constructor(
    private val forecastDataMapper: ForecastDataMapper
) : IEntityMapper<ForeCastResponse, ForeCast> {
    override fun mapToModel(entity: ForeCastResponse): ForeCast {
        return ForeCast(
            foreCastList = entity.forecastDataList?.map {
                forecastDataMapper.mapToModel(it)
            }?: emptyList()
        )
    }

    override fun mapToEntity(model: ForeCast): ForeCastResponse {
        TODO("Not yet implemented")
    }

}