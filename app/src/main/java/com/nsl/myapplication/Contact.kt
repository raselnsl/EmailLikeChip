package com.nsl.myapplication

class Contact(s: String, adamFord: Int) {
    var s : String = "temp"
    var resourceAddrese : Int = R.drawable.ic_person
    init {
        this.s = s
        this.resourceAddrese = adamFord
    }
    fun getName(): String {
        return s
    }

    fun getAvatarResource(): Int {
        return resourceAddrese;
    }

}