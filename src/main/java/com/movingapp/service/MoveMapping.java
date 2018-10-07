package com.movingapp.service;

import com.movingapp.entity.User;
import com.movingapp.model.MoveEntity;
import com.movingapp.model.NoteEntity;
import com.movingapp.view.Move;
import com.movingapp.view.Note;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoveMapping {

    @Autowired
    private com.movingapp.dao.UserRepo userRepo;

    public List<Move> MoveEntityToMoves(List<MoveEntity> moveEntityList) {

        List<Move> movesList = new ArrayList<Move>();

        for(int i = 0; i < moveEntityList.size(); i++) {
            Move move = new Move();
            move.setId(moveEntityList.get(i).getID());
            List<Note> notes = NoteEntityToNote(moveEntityList.get(i).getNotes());
            move.setNotes(notes);
            move.setComment(moveEntityList.get(i).getComment());
            move.setStartsAt(moveEntityList.get(i).getMoveStart());
            move.setFromCity(moveEntityList.get(i).getfromCity());
            move.setFromState(moveEntityList.get(i).getFromState());
            move.setFromStreet(moveEntityList.get(i).getfromStreet());
            move.setFromZip(moveEntityList.get(i).getFromZip());
            move.setToCity(moveEntityList.get(i).getToCity());
            move.setToState(moveEntityList.get(i).getToState());
            move.setToStreet(moveEntityList.get(i).getToStreet());
            move.setToZip(moveEntityList.get(i).getToZip());
            move.setDateOfBooking(moveEntityList.get(i).getDateOfBooking());
            move.setStatus(moveEntityList.get(i).getStatus());
            move.setTitle(moveEntityList.get(i).getMoveTitle());
            move.setEndsAt(moveEntityList.get(i).getMoveEnd());

            movesList.add(move);
        }
        return movesList;
    }

    public List<Note> NoteEntityToNote(List<NoteEntity> notesEntityList) {

        List<Note> noteList = new ArrayList<Note>();

        for(int i = 0; i < notesEntityList.size(); i++) {
            Note note = new Note();
            note.setAuthor(notesEntityList.get(i).getAuthor());
            note.setComment(notesEntityList.get(i).getComment());
            note.setDate(notesEntityList.get(i).getDate());
            note.setID(notesEntityList.get(i).getID());
            noteList.add(note);
        }

        return noteList;
    }

    public List<NoteEntity> NoteToNoteEntity(List<Note> notesList, MoveEntity move) {

        List<NoteEntity> noteEntityList = new ArrayList<NoteEntity>();

        for(int i = 0; i < notesList.size(); i++) {
            NoteEntity note = new NoteEntity();
            note.setAuthor(notesList.get(i).getAuthor());
            note.setComment(notesList.get(i).getComment());
            note.setDate(notesList.get(i).getDate());
            note.setID(notesList.get(i).getID());
            note.setMove(move);
            noteEntityList.add(note);
        }

        return noteEntityList;
    }

    public MoveEntity moveToMoveEntity(Move move) {

        MoveEntity moveEntity = new MoveEntity();
        moveEntity.setID(move.getId());
        moveEntity.setMoveStart(move.getStartsAt());
        moveEntity.setComment(move.getComment());
        moveEntity.setfromCity(move.getFromCity());
        moveEntity.setFromState(move.getFromState());
        moveEntity.setfromStreet(move.getFromStreet());
        moveEntity.setFromZip(move.getFromZip());
        moveEntity.setToCity(move.getToCity());
        moveEntity.setToState(move.getToState());
        moveEntity.setToStreet(move.getToStreet());
        moveEntity.setToZip(move.getToZip());
        moveEntity.setDateOfBooking(move.getDateOfBooking());
        moveEntity.setStatus(move.getStatus());
        //moveEntity.setCharges(ChargeToChargeEntity(move.getCharges(), moveEntity));
        moveEntity.setNotes(NoteToNoteEntity(move.getNotes(), moveEntity));
        moveEntity.setMoveEnd(move.getEndsAt());
        moveEntity.setMoveTitle(move.getTitle());
        Optional<User> foundUser = userRepo.findById(move.getUser().getId());
        User user = foundUser.get();
        moveEntity.setUser(user);
        return moveEntity;
    }

    public Move MoveEntityToMove(MoveEntity moveEntity) {
        Move move = new Move();
        move.setId(moveEntity.getID());
        List<Note> notes = NoteEntityToNote(moveEntity.getNotes());
        move.setNotes(notes);
        move.setComment(moveEntity.getComment());
        move.setStartsAt(moveEntity.getMoveStart());
        move.setFromCity(moveEntity.getfromCity());
        move.setFromState(moveEntity.getFromState());
        move.setFromStreet(moveEntity.getfromStreet());
        move.setFromZip(moveEntity.getFromZip());
        move.setToCity(moveEntity.getToCity());
        move.setToState(moveEntity.getToState());
        move.setToStreet(moveEntity.getToStreet());
        move.setToZip(moveEntity.getToZip());
        move.setDateOfBooking(moveEntity.getDateOfBooking());
        move.setStatus(moveEntity.getStatus());
        move.setEndsAt(moveEntity.getMoveEnd());
        move.setTitle(moveEntity.getMoveTitle());
        UserView userView = new UserView();
        userView.setEmail(moveEntity.getUser().getEmail());
        userView.setFirstName(moveEntity.getUser().getFirstName());
        userView.setId(moveEntity.getUser().getId());
        userView.setLastName(moveEntity.getUser().getLastName());
        userView.setPhone(moveEntity.getUser().getPhone());
        move.setUser(userView);

        return move;
    }
}
