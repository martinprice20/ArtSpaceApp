package com.martinprice20.artspaceapp


class ArtGallery {

    companion object {
        fun getGallery() : List<ArtWork> {
            return listOf(
                ArtWork("abstrakt", "Abstrakt", "An abstract art scupture", "Oino Biongo", "01/06/2023"),
                ArtWork("bride", "Bride", "A picture of a beautiful bride", "Wilhem Sarcophogus", "02/06/2023"),
                ArtWork("clouds", "Clouds", "An image of clouds over the coastline", "Nancy Ephemeral", "03/06/2023"),
                ArtWork("couple", "Couple", "A picture of a married couple dressed in yellow", "Nighflight Smithson", "04/06/2023"),
                ArtWork("dog", "Dog", "An image of a dog", "Pelham Gastard", "05/06/2023"),
                ArtWork("hall", "Hall", "An image of a large Exhibition Hall", "Dedalus Wompstone", "06/06/2023"),
                ArtWork("men", "Men", "A picture of two men talking", "Zants Zwillinger", "07/06/2023"),
                ArtWork("minaret", "Minaret", "A photo of a mosque's prayer tower", "Peebles Drinkproud", "08/06/2023")
            )
        }
    }
}

data class ArtWork(
    val resId: String,
    val name: String,
    val contentDescription: String,
    val artist: String,
    val date: String,
)