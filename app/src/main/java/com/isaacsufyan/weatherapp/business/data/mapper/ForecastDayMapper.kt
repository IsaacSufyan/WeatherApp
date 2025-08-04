package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ConditionResponse
import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastDayResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.ForeCastDay
import javax.inject.Inject

class ForecastDayMapper @Inject constructor(
    private val conditionMapper: ConditionMapper
) : IEntityMapper<ForeCastDayResponse, ForeCastDay> {
    override fun mapToModel(entity: ForeCastDayResponse): ForeCastDay {
        return ForeCastDay(
            maxtemp_c = entity.maxtemp_c?:"",
            mintemp_c = entity.mintemp_c?:"",
            condition = conditionMapper.mapToModel(entity.condition?: ConditionResponse())
        )
    }

    override fun mapToEntity(model: ForeCastDay): ForeCastDayResponse {
        TODO("Not yet implemented")
    }

}