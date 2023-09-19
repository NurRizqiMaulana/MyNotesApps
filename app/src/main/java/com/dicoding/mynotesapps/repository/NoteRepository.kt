package com.dicoding.mynotesapps.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.mynotesapps.database.Note
import com.dicoding.mynotesapps.database.NoteDao
import com.dicoding.mynotesapps.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = mNoteDao.getAllNotes()

    fun insert(note: Note){
        executorService.execute{ mNoteDao.insert(note)}
    }

    fun delete(note: Note){
        executorService.execute{ mNoteDao.delete(note)}
    }

    fun update(note: Note){
        executorService.execute{ mNoteDao.update(note)}
    }
}