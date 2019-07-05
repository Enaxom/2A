with Ada.Text_IO;
with Ada.Command_Line;
with Ada.Exceptions;
use Ada;
with Gdk.Threads;
with Gtk.Main;
with Bridge.Affic;
with Bridge.Tasks;
with Bridge.Simu;
with Bridge.Synchro;

procedure Bridge.Main is
   Nb_Joueurs : Natural;
   
   procedure Start_Client_Tasks is
      Tj : Bridge.Tasks.Joueur_Access;
   begin
      for I in 1..Nb_Joueurs loop
         Tj := new Bridge.Tasks.Joueur;
         Tj.Init(I);
      end loop;
   end Start_Client_Tasks;
   
begin
   if Ada.Command_Line.Argument_Count /= 1 then
      Text_IO.Put_Line("Un argument attendu : nbjoueurs.");
      return;
   end if;
   
   Nb_Joueurs := Natural'Value(Ada.Command_Line.Argument(1));
   
   if (Nb_Joueurs > Bridge.Tasks.MAXPROCS) then
      Text_IO.Put_Line("Nb_Joueurs >= 1, Nb_Joueurs <=" & Natural'Image(Bridge.Tasks.MAXPROCS));
      return;
   end if;
   
   Gdk.Threads.G_Init;
   Gdk.Threads.Init;
   Gtk.Main.Init;
   
   Bridge.Simu.Init(Nb_Joueurs);
   Bridge.Affic.Init(Bridge.Synchro.Nom_Strategie, Nb_Joueurs);
   Start_Client_Tasks;

   Gdk.Threads.Enter;
   Gtk.Main.Main;
   Gdk.Threads.Leave;
   
exception
   when Error: others =>
      Text_IO.Put_Line("**** BridgeTask: exception: " & Ada.Exceptions.Exception_Information(Error));
end Bridge.Main;
