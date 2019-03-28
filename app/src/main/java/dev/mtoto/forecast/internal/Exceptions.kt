package dev.mtoto.forecast.internal

import java.io.IOException

class NoConnectivityException :IOException()
class LocationPermissionNotGrantedException: Exception()