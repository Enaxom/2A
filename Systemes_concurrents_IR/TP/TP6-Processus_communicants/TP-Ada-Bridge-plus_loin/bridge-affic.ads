with Bridge.Tasks; use Bridge.Tasks;

package Bridge.Affic is
   
   type Proc_Etat is (Demande_Entree, Joue, Demande_Sortie, Rien);
   
   procedure Init (Nomstrategie: String; NbJoueurs : Natural);
   
   -- Change l'état d'affichage : place du rond blanc/noir
   procedure Changer_Etat(Id : Proc_Id; Etat: Proc_Etat);
   
end Bridge.Affic;
