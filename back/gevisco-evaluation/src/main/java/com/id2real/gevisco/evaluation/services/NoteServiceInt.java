package com.id2real.gevisco.evaluation.services;


import java.util.List;

import com.id2real.gevisco.evaluation.dtos.NoteDTO;
import com.id2real.gevisco.evaluation.entities.Note;
import com.id2real.socle.core.services.GenericServiceInt;

public interface NoteServiceInt  extends GenericServiceInt<Note, String, NoteDTO> {
	
	public List<?> NoteByLibelle(String libelle);
}
