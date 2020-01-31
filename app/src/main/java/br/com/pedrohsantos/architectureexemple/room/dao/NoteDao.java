package br.com.pedrohsantos.architectureexemple.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import br.com.pedrohsantos.architectureexemple.room.entity.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(NoteEntity noteEntity);
    @Update
    void update(NoteEntity noteEntity);
    @Delete
    void delete(NoteEntity noteEntity);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<NoteEntity>> getAllNotes();
}
