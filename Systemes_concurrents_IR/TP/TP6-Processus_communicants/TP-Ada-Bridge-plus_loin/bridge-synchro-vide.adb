with Ada.Text_IO; use Ada.Text_IO;
with Ada.Exceptions;

-- Bridge sans contrainte
package body Bridge.Synchro.Vide is
	
   function Nom_Strategie return String is
   begin
      return "Serveur";
   end Nom_Strategie;
   
   task BridgeTask is
      entry Entrer;
      entry Sortir;
   end BridgeTask;

   task body BridgeTask is	
   begin
      loop
      	select    			
      		accept Entrer; 
      	or
      		accept Sortir;
		end select;
      end loop;
   exception
      when Error: others =>
         Put_Line("**** BridgeTask: exception: " & Ada.Exceptions.Exception_Information(Error));
   end BridgeTask;

   procedure Sortir is
   begin
      BridgeTask.Sortir;
   end Sortir;

   procedure Entrer is
   begin
      BridgeTask.Entrer;
   end Entrer;

end Bridge.Synchro.Vide;
