with Ada.Text_IO; use Ada.Text_IO;
with Bridge.Synchro;
with Bridge.Simu;
with Bridge.Affic;
with Ada.Numerics.Discrete_Random;
with Ada.Exceptions;

package body Bridge.Tasks is
   
   package Rand_Int is new Ada.Numerics.Discrete_Random(Positive);
   Generator : Rand_Int.Generator;
   
   -- Chaque joueur joue entre MinDelayJoue et MaxDelayJoue secondes, et
   -- pense entre MinDelayJRien et MaxDelayJRien (les deux bornes incluses).
   MinDelayJoue   : constant Positive := 5;
   MaxDelayJoue   : constant Positive := 20;
   MinDelayRien : Positive := MinDelayJoue;
   MaxDelayRien : Positive := MaxDelayJoue;
   
   -- Suspend la simulation de la tâche pendant [bi..bs] unités
   procedure Random_Sleep(No: Proc_Id; Bi: Positive; Bs: Positive) is
      Duree : Positive;
   begin
      Duree := (Rand_Int.Random(Generator) mod (Bs - Bi + 1)) + Bi;
      Bridge.Simu.Sleep(No, Duree);
   end Random_Sleep;
   
   task body Joueur is
      Id: Proc_Id;
   begin
      accept Init(MonId: Proc_Id) do 
      	Id:=MonId; 
      	Put_Line("Init id: " & Natural'Image(MonId));
      end Init;
      loop
 		 Random_Sleep(Id, MinDelayRien, MaxDelayRien);
         
         Put_Line (Proc_Id'Image(Id) & " Appel E");
         Bridge.Affic.Changer_Etat(Id, Bridge.Affic.Demande_Entree);
         Bridge.Synchro.Entrer;
         
         Put_Line (Proc_Id'Image(Id) & " Entree passee, jeu");
         Bridge.Affic.Changer_Etat(Id, Bridge.Affic.Joue);
         Random_Sleep(Id, MinDelayJoue, MaxDelayJoue);
         
         Put_Line (Proc_Id'Image(Id) & " Appel S");
         Bridge.Affic.Changer_Etat(Id, Bridge.Affic.Demande_Sortie);
		 Bridge.Synchro.Sortir;
         Bridge.Affic.Changer_Etat(Id, Bridge.Affic.Rien);
      end loop;
   exception
      when Error: others =>
         Put_Line("**** Tâche Joueur "& Natural'Image(Id)&" : exception: " & Ada.Exceptions.Exception_Information(Error));
   end Joueur;
   
begin
   Rand_Int.Reset(Generator);
end Bridge.Tasks;
