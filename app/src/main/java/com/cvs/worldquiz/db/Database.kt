package com.cvs.worldquiz.db

import io.realm.Case
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.io.InputStream
import kotlin.reflect.KClass

 class Database private constructor() {

    private val database: Realm

    init {
        database = Realm.getDefaultInstance()
    }

    private object Holder {
        val instance = Database()
    }

    companion object {
        val shared: Database by lazy { Holder.instance }
        val threadSafe: Database
            get() = Database()
    }

    fun fromJson(clazz: Class<out RealmObject>, data: String) {
        database.beginTransaction()
        database.createAllFromJson(clazz, data)
        database.commitTransaction()
    }

    fun fromStream(clazz: Class<out RealmObject>, data: InputStream) {
        database.beginTransaction()
        database.createAllFromJson(clazz, data)
        database.commitTransaction()
    }

    fun getAll(clazz: Class<out RealmObject>): RealmResults<out RealmObject> {
        return database.where(clazz).findAll()
    }

    fun <T : RealmObject> getAll(clazz: KClass<T>): RealmResults<T> {
        return database.where(clazz.java).findAll()
    }

    fun <T : RealmObject> where(clazz: KClass<T>, query: String, columns: Array<String>): RealmResults<T> {
        val realmQuery = database.where(clazz.java)
        columns.forEachIndexed { index, s ->
            realmQuery.contains(s, query, Case.INSENSITIVE)
            if (index < columns.size - 1) {
                realmQuery.or()
            }
        }
        return realmQuery.findAll()
    }

}
