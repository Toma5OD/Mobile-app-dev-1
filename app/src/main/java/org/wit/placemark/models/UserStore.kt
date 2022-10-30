package org.wit.placemark.models


interface UserStore {
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun delete(user: UserModel)
    fun findAll(): List<UserModel>

}