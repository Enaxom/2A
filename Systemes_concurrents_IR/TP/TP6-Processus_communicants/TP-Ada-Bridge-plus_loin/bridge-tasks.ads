-- Tâches clients : joueurs
package Bridge.Tasks is
   
   MAXPROCS : constant Integer := 200;
   
   subtype Proc_Id is Positive range 1..MAXPROCS;
   
   task type Joueur is
      entry Init(MonId: Proc_Id);
   end Joueur;
   
   
   type Joueur_Access is access Joueur;
   
end Bridge.Tasks;
