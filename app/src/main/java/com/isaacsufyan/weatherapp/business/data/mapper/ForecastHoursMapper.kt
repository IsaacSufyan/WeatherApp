package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ConditionResponse
import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastHoursResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.ForeCastHours
import javax.inject.Inject

class ForecastHoursMapper @Inject constructor(
    private val conditionMapper: ConditionMapper
) : IEntityMapper<ForeCastHoursResponse, ForeCastHours> {
    override fun mapToModel(entity: ForeCastHoursResponse): ForeCastHours {
        return ForeCastHours(
            timeEpoch = entity.timeEpoch ?: 0L,
            time = entity.time ?: "",
            temperatureCelsius = entity.temperatureCelsius ?: 0.0,
            condition = conditionMapper.mapToModel(entity.condition ?: ConditionResponse())
        )
    }

    override fun mapToEntity(model: ForeCastHours): ForeCastHoursResponse {
        TODO("Not yet implemented")
    }

}