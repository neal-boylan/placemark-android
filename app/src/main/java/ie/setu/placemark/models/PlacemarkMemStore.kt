package ie.setu.placemark.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlacemarkMemStore : PlacemarkStore {

    val placemarks = ArrayList<PlacemarkModel>()

    override fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    override fun findOne(placemark: PlacemarkModel): PlacemarkModel? {
        var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == placemark.id }
        return foundPlacemark
    }

    override fun create(placemark: PlacemarkModel) {
        placemark.id = getId()
        placemarks.add(placemark)
        i("Add Button Pressed:")
        logAll()
    }

    override fun update(placemark: PlacemarkModel) {
        var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == placemark.id }
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
            logAll()
        }
        else {
            create(placemark)
        }
    }

    fun logAll() {
        placemarks.forEach{ i("$it") }
    }
}