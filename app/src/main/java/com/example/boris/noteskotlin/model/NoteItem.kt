package com.example.boris.noteskotlin.model

class NoteItem(val id : Long, val title : String?, val body : String?, val date : Long) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is NoteItem) return false
        val that = other as NoteItem
        return that.id == id &&
                that.body.equals(body) &&
                that.title.equals(title) &&
                that.date == date
    }
}