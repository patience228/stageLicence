import { Annee } from "./annee";
import { Classe } from "./classe";
import { Eleve } from "./eleve";

export interface Affectation {
    id?:string;
    eleve?:Eleve;
    annee?:Annee;
    classe?:Classe;
    active?:boolean;
}




