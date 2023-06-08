import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Note } from 'src/app/model/note';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

    constructor(private http: HttpClient) { }

    addNote(note:Note){
      return this.http.post<any>(environment.note_url+"/save",note)
      .toPromise();
  }

  updateNote(note:Note){
      return this.http.post<any>(environment.note_url+"/update",note)
      .toPromise();
  }

  deleteNote(note:Note){
      return this.http.delete<any>(environment.note_url+"/"+note.id)
      .toPromise();
  }

  getNotes() {
      return this.http.get<any>(environment.note_url)
      .toPromise();
  }

  getNoteById(note:Note) {
      return this.http.get<any>(environment.note_url+"/find/"+note.id)
      .toPromise();
  }

  getNoteByLibelle(libelle:string) {
    return this.http.get<any>(environment.note_url+"/"+libelle)
    .toPromise();
}

  getNotesCount() {
      return this.http.get<any>(environment.note_url+"/count")
      .toPromise();
  }



}
