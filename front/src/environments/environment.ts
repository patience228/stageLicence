// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const   root="http://localhost:8080/";
export const environment = {
  production: false,
  annee_url: root+"gevisco/api/preinscripion/an",
  niveau_url: root+"gevisco/api/preinscripion/niv",
  eleve_url: root+"gevisco/api/preinscripion/elv",
  preinscription_url: root+"gevisco/api/preinscripion/pre",

  root_url:root,

  classe_url: root+"gevisco/api/etudes/class",
  matiere_url: root+"gevisco/api/etudes/mat",
  professeur_url: root+"gevisco/api/etudes/prof",
  titulaire_url: root+"gevisco/api/etudes/titu",
  affectation_url: root+"gevisco/api/etudes/affect",
  enseignement_url: root+"gevisco/api/etudes/enseign",

  evaluation_url: root+"gevisco/api/evaluation/eval",
  note_url: root+"gevisco/api/evaluation/note",

  scolarite_url: root+"gevisco/api/inscripion/sco",
  inscription_url: root+"gevisco/api/inscripion/inscri",

  user_url: root+"gevisco/api/utilisateurs/user",
  utilisateur_url: root+"gevisco/api/security/user",
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
