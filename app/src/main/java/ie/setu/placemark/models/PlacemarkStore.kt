package ie.setu.placemark.models

interface PlacemarkStore {
    fun findAll(): List<PlacemarkModel>
    fun findOne(placemark: PlacemarkModel): PlacemarkModel?
    fun create(placemark: PlacemarkModel)
    fun update(placemark: PlacemarkModel)
}