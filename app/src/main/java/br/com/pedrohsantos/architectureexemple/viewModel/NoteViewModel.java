package br.com.pedrohsantos.architectureexemple.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import br.com.pedrohsantos.architectureexemple.repository.NoteRepository;
import br.com.pedrohsantos.architectureexemple.room.entity.NoteEntity;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<NoteEntity>> allNotes;

    public NoteViewModel(Application application){
        super (application);
        this.repository = new NoteRepository(application);
        this.allNotes = this.repository.getAllNotes();
    }

    public void insert(NoteEntity noteEntity){
        this.repository.insert(noteEntity);
    }

    public void update(NoteEntity noteEntity){
        this.repository.update(noteEntity);
    }

    public void delete(NoteEntity noteEntity){
        this.repository.delete(noteEntity);
    }

    public void deleteAllNotes(){
        this.repository.deleteAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes(){
        return this.allNotes;
    }
}
