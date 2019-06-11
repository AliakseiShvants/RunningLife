package com.shvants.runninglife.model.ui

open class RideDetailedActivityUi(override var id: Long,
                                  override var name: String,
                                  override var movingTime: String,
                                  override var type: String,
                                  override var startDate: String,
                                  override var distance: String,
                                  override var avgSpeed: String,
                                  override var startLatlng: FloatArray,
                                  override var endLatlng: FloatArray,
                                  override var map: String,
                                  override var elevation: Int,
                                  override var calories: Int,
                                  override var avgHR: Int,
                                  var avgWatts: Int) :
        DetailedActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, startLatlng,
                endLatlng, map, elevation, calories, avgHR)