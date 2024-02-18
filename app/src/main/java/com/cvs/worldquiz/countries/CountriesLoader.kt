package com.cvs.worldquiz.countries

import android.content.Context
import com.cvs.worldquiz.db.Database
import com.cvs.worldquiz.db.model.Country
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import java.util.concurrent.Callable
import java.util.concurrent.Future

private const val COUNTRY_JSON_FILE = "countries_details.json"

class CountriesLoader {

    fun loaded(): Observable<Boolean> {
        val observable = Observable.fromCallable {
            return@fromCallable Database.threadSafe.getAll(Country::class.java).isNotEmpty()
        }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    fun load(context: Context?): Observable<Boolean> {
        val observable = Observable.fromCallable {
            val database = Database.threadSafe
            val all = database.getAll(Country::class.java)
            if (all.isEmpty()) {
                context?.let {
                    val inputStream = it.assets.open(COUNTRY_JSON_FILE)
                    database.fromStream(Country::class.java, inputStream)
                }
                return@fromCallable database.getAll(Country::class.java).isNotEmpty()
            }
            return@fromCallable true
        }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

}