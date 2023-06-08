package com.id2real.gevisco.evaluation.services;



import java.util.List;

import com.id2real.gevisco.evaluation.dtos.EvaluationDTO;
import com.id2real.gevisco.evaluation.entities.Evaluation;
import com.id2real.socle.core.services.GenericServiceInt;

public interface EvaluationServiceInt  extends GenericServiceInt<Evaluation, String, EvaluationDTO> {
	public List<EvaluationDTO> findByAnnee( String id);
}
