
import { Matiere } from "./matiere";
import { Professeur } from "./professeur";

export interface EnseignementUpdate {
    id?:string;
    professeur?:Professeur;
    matiereOld?:Matiere;
    matiere?:Matiere;

}




