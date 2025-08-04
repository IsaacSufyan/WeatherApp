package com.isaacsufyan.weatherapp.business.domain.mapper

interface IEntityMapper<Entity, Model> {

    fun mapToModel(entity: Entity) : Model

    fun mapToEntity(model: Model) : Entity
}