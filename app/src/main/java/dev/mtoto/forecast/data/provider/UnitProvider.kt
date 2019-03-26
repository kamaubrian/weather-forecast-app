package dev.mtoto.forecast.data.provider

import dev.mtoto.forecast.internal.UnitSystem

interface UnitProvider {
     fun getUnitSystem() : UnitSystem
}