package com.devsahil.mvvmexampleapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDataBase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {
        private var INSTANCE: QuoteDataBase? = null
        fun getDatabase (context: Context): QuoteDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDataBase::class.java,
                        "quote_database"
                    )
                        .createFromAsset("quotes.db")
                        .build()
                }

            }
            return INSTANCE!!

        }
    }
}