import { Annee } from "./annee";

export interface Evaluation {
    id?:string;
    libelle?:string;
    debutEval?:Date;
    finEval?:Date;
    periode?:string;
    annee?:Annee;
    active?:boolean;
}

