package br.com.pedrohsantos.architectureexemple.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import br.com.pedrohsantos.architectureexemple.room.dao.NoteDao;
import br.com.pedrohsantos.architectureexemple.room.entity.NoteEntity;
import br.com.pedrohsantos.architectureexemple.room.sqlite.NoteDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(NoteEntity noteEntity){
        new InsertNoteEntityAsyncTask(noteDao).execute(noteEntity);
    }

    public void update(NoteEntity noteEntity){
        new UpdateNoteEntityAsyncTask(noteDao).execute(noteEntity);
    }

    public void delete(NoteEntity noteEntity){
        new DeleteNoteEntityAsyncTask(noteDao).execute(noteEntity);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteEntityAsyncTask(noteDao).execute();
    }

    public LiveData<List<NoteEntity>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteEntityAsyncTask extends AsyncTask<NoteEntity, Void, Void>{
        private NoteDao noteDao;
        private InsertNoteEntityAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDao.insert(noteEntities[0]);
            return null;
        }
    }

    private static class UpdateNoteEntityAsyncTask extends AsyncTask<NoteEntity, Void, Void>{
        private NoteDao noteDao;
        private UpdateNoteEntityAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDao.update(noteEntities[0]);
            return null;
        }
    }

    private static class DeleteNoteEntityAsyncTask extends AsyncTask<NoteEntity, Void, Void>{
        private NoteDao noteDao;
        private DeleteNoteEntityAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDao.delete(noteEntities[0]);
            return null;
        }
    }

    private static class DeleteAllNoteEntityAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;
        private DeleteAllNoteEntityAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
