package org.wit.placemark.models


import android.content.Context
import com.google.gson.reflect.TypeToken

import org.wit.placemark.helpers.exists
import org.wit.placemark.helpers.read
import org.wit.placemark.helpers.write
import timber.log.Timber
import java.lang.reflect.Type
import java.util.ArrayList


const val USER_JSON_FILE = "users.json"

val UserListType: Type = object : TypeToken<ArrayList<UserModel>>() {}.type

class UserJSONStore(private val context: Context) : UserStore{

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, USER_JSON_FILE)) {
            deserialize()
        }
    }

    override fun create(user: UserModel) {
        users.add(user)
        serialize()
    }

    override fun update(user: UserModel) {
        TODO("Not yet implemented")
    }

    override fun delete(user: UserModel) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<UserModel> {
        logAll()
        return users
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, UserListType)
        write(context, USER_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, USER_JSON_FILE)
        users = gsonBuilder.fromJson(jsonString,UserListType)
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }

}