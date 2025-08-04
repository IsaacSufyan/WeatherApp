package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ConditionResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.Condition
import javax.inject.Inject

class ConditionMapper @Inject constructor() : IEntityMapper<ConditionResponse, Condition> {
    override fun mapToModel(entity: ConditionResponse): Condition {
        return Condition(
            text = entity.text ?: "",
            icon = entity.icon ?: "",
            code = entity.code ?: 0
        )
    }

    override fun mapToEntity(model: Condition): ConditionResponse {
        TODO("Not yet implemented")
    }

}