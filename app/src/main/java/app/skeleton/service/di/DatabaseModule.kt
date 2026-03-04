package app.skeleton.service.di

import androidx.room.Room
import app.skeleton.service.data.database.Database
import org.koin.dsl.module

private const val DB_NAME = "_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<Database>().bookingDao()}

}