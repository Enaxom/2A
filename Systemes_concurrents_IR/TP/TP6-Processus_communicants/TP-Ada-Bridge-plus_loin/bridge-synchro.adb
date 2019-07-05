with Bridge.Synchro.Vide;  -- XXXX

package body Bridge.Synchro is
   
   package Synchro renames Bridge.Synchro.Vide; -- XXXX
    
   function Nom_Strategie return String renames Synchro.Nom_Strategie;

   procedure Entrer renames Synchro.Entrer;

   procedure Sortir renames Synchro.Sortir;

end Bridge.Synchro;
