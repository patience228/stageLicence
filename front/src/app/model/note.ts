import { Eleve } from "./eleve";
import { Evaluation } from "./evaluation";
import { Matiere } from "./matiere";

export interface Note {
    id?:string;
    notes?:number;
    eleve?:Eleve;
    matiere?:Matiere;
    evaluation?:Evaluation;
    active?:boolean;
}




