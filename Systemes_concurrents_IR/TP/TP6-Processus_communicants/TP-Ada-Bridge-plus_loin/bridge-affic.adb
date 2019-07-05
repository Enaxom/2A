with Ada.Text_IO; use Ada.Text_IO;
with Ada.Tags;
with Ada.Numerics.Discrete_Random;
with Glib; use Glib;
with Gtkada.Builder; use Gtkada.Builder;
with Gtkada.Handlers;
with Gdk.Threads;
with Gdk.GC;
with Gdk.Color;
with Gdk.Drawable;
with Gdk.Window;
with Glib.Error;
with Gtk.Widget;
with Gtk.Main;
with Gtk.Label;
with Gtk.Adjustment;
with Gtk.GRange;
with Gtk.Handlers;
with Bridge.Tasks; use Bridge.Tasks; -- just for Proc_Id
with Bridge.Simu;

package body Bridge.Affic is

   Nb_Joueurs : Natural;

   Dialog_Aide : Gtk.Widget.GTK_Widget;

   type Point is record
      X : Natural;
      Y : Natural;
   end record;

   type Objet is record
      Etat : Proc_Etat;
      Pos_Rien : Point;
      Pos_DemandeE : Point;
      Pos_DemandeS : Point;
      Pos_Joue : Point;
   end record;

   Nb_Objets : Natural; -- = Nb_Joueurs
   Les_Objets : array(Proc_Id) of Objet;

   -- Event "clicked" on button "aide"
   procedure Aide_Afficher(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   begin
      Gtk.Widget.Show(Dialog_Aide);
   end Aide_Afficher;

   -- Event "clicked" on button "fermer"
   procedure Aide_Fermer(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   begin
      Gtk.Widget.Hide(Dialog_Aide);
   end Aide_Fermer;

   -- Event clicked on button "pause".
   procedure Pause_Or_Run(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   begin
      Bridge.Simu.Swap_Running;
   end Pause_Or_Run;

   -- Event "clicked" on button "paramètres"
   --  procedure Show_Param(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   --  begin
   --     -- Gtk.Widget.Show(Dialog_Parametres);
   --     null;
   --  end Show_Param;

   -- Event "value_changed" de l'ajustement de l'échelle de vitesse du temps.
   procedure Set_Timespeed(Adj : access Gtk.Widget.Gtk_Widget_Record'Class) is
      Val : Natural;
   begin
      Val := Natural(Gtk.Adjustment.Get_Value(Gtk.GRange.Get_Adjustment(Gtk.GRange.Gtk_Range(Adj))));
      Bridge.Simu.Set_Timespeed(Val);
   end Set_Timespeed;

   procedure Quitter(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
      procedure Libc_Exit(Status: Natural);
      pragma Import(C, Libc_Exit, "exit");
   begin
      -- Gtk.Main.Main_Quit;
      Libc_Exit(0);
   end Quitter;

   ----------------------------------------------------------------

   package Random_Proc is
      function Get(Min, Max: Proc_Id) return Proc_Id;
   end Random_Proc;
   package body Random_Proc is
      package Rand_Proc is new Ada.Numerics.Discrete_Random(Proc_Id);
      Generator : Rand_Proc.Generator;
      function Get(Min, Max: Proc_Id) return Proc_Id Is
         X : Proc_Id;
      begin
         loop
            X := Rand_Proc.Random(Generator);
            exit when (X >= Min and X <= Max);
         end loop;
         return X;
      end Get;
   begin
      Rand_Proc.Reset(Generator);
   end Random_Proc;

   -- Cherche dans l'intervalle [debut..fin] un processus dans l'état etat1.
   -- S'il y en a au moins un, un processus *au hasard* (dans cet état) est réveillé.
   -- 
   function Chercher_Dormeur (Debut: Proc_Id; Fin: Proc_Id; Etat1: Proc_Etat) return Boolean is
      Base : Proc_Id;
   begin
      -- Tirons au hasard le point de départ, puis on parcourt circulairement.
      base := Random_Proc.Get(Debut, Fin+1);
      for i in Base .. Fin loop
         if (Les_Objets(i).Etat = Etat1) then
            Bridge.Simu.wakeup (i);
            return True;
         end if;
      end loop;
      for i in Debut..Base-1 loop
         if (Les_Objets(i).Etat = Etat1) then
            Bridge.Simu.wakeup (i);
            return True;
         end if;
      end loop;
      return False;
   end Chercher_Dormeur;

     function Click_Event_Dehors(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
     begin
        return Chercher_Dormeur(Proc_Id'First, Nb_Joueurs, Rien);
     end Click_Event_Dehors;

     function Click_Event_Salle(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
     begin
        return Chercher_Dormeur(Proc_Id'First, Nb_Objets, Joue);
     end Click_Event_Salle;

     ----------------------------------------------------------------

     -- Event configure (= realize or resize) on window redacteur.
     -- XXXX Hypothèses:
     --   - les deux fenêtres dehors, jeu ont la même hauteur et la même largeur;
     --   - la fenêtre dehors est en haut, la fenêtre jeu en bas.
     function Compute_Placement(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
        Taille_Fen_X, Taille_Fen_Y : Float;
        Interv_Dehors, Interv_Salle : Float;
        Start_Dehors, Start_Salle : Float;
     begin
        Taille_Fen_X := Float(Gtk.Widget.Get_Allocation_Width(Gtkada.Builder.Get_Widget(Builder, "drawingarea_dehors")));
        Taille_Fen_Y := Float(Gtk.Widget.Get_Allocation_Height(Gtkada.Builder.Get_Widget(Builder, "drawingarea_dehors")));

        Interv_Dehors := Taille_Fen_X / (Float(Nb_Joueurs - 1) + 4.0);
        Interv_Salle := Interv_Dehors;
        Start_Dehors := 2.0 * Interv_Dehors;
        Start_Salle := Start_Dehors;

        for i in Proc_Id'First .. Proc_Id'First + Nb_Joueurs - 1 loop
           	Les_Objets(i).Pos_Rien.x := Natural(Start_Dehors);
           	Les_Objets(i).Pos_Rien.y := Natural(Taille_Fen_Y * 0.10);
           	Les_Objets(i).Pos_DemandeE.x := Natural(Start_Dehors);
           	Les_Objets(i).Pos_DemandeE.y := Natural(Taille_Fen_Y * 0.75);
           	Les_Objets(i).Pos_Joue.x := Natural(Start_Salle);
           	Les_Objets(i).Pos_Joue.y := Natural(Taille_Fen_Y * 0.5);
        	Les_Objets(i).Pos_DemandeS.x := Les_Objets(i).Pos_Joue.x;
           	Les_Objets(i).Pos_DemandeS.y := Les_Objets(i).Pos_Joue.y;
          	Start_Dehors := Start_Dehors + Interv_Dehors;
           	Start_Salle := Start_Salle + Interv_Salle;
        end loop;

        return False;
     end Compute_Placement;

     ----------------------------------------------------------------

     -- rayon du cercle
     Rayon : Natural := 7;
     -- graphic context des cercles
     GC_Epais : Gdk.GC.Gdk_GC;

     Xfen_Dehors : Gtk.Widget.Gtk_Widget;
     Xfen_Salle : Gtk.Widget.Gtk_Widget;

     -- Event expose on any drawingarea.
     function Expose(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
        procedure Tracer_Cercle(Win : Gtk.Widget.Gtk_Widget; Fill : Boolean; Center: Point) is
        begin
           Gdk.Drawable.Draw_Arc(Gtk.Widget.Get_Window(Win), GC_Epais, Fill, Gint(Center.X - Rayon), Gint(Center.Y - Rayon), Gint(Rayon*2), Gint(Rayon*2), 0, 360*64);
        end Tracer_Cercle;

     begin
        for I in Proc_Id'First .. Proc_Id'First + Nb_Objets - 1 loop
           case Les_Objets(I).Etat is
              when Demande_Entree =>
                 Tracer_Cercle(Xfen_Dehors, True, Les_Objets(i).Pos_DemandeE);
              when Joue =>
                 Tracer_Cercle(Xfen_Salle, False, Les_Objets(i).Pos_Joue);
              when Rien =>
                 Tracer_Cercle(Xfen_Dehors, False, Les_Objets(i).Pos_Rien);
              when Demande_Sortie =>
                 Tracer_Cercle(Xfen_Salle, True, Les_Objets(i).Pos_DemandeS);
           end case;
        end loop;
        return True;
     end Expose;

     ----------------------------------------------------------------

     procedure Init (Nomstrategie: String; NbJoueurs : Natural) is
        Builder : Gtkada.Builder.Gtkada_Builder;
        Error   : Glib.Error.GError;
     begin
        Nb_Joueurs := NbJoueurs;
        Nb_Objets := NbJoueurs;
        for I in Proc_Id'First .. Proc_Id'First + Nb_Joueurs - 1 loop
           Les_Objets(I).Etat := Rien;
        end loop;

        Gtkada.Builder.Gtk_New (Builder);
        Error := Gtkada.Builder.Add_From_File (Builder, "bridge.glade");
        Dialog_Aide := Gtkada.Builder.Get_Widget(Builder, "dialog_aide");

        declare
           Nom_Strategie : Gtk.Label.Gtk_Label;
        begin
           Nom_Strategie :=  Gtk.Label.Gtk_Label(Gtkada.Builder.Get_Widget(Builder, "label_strategie"));
           Gtk.Label.Set_Text(Nom_Strategie, Nomstrategie);
        end;

        declare
           Timespeed_Scale : Gtk.GRange.Gtk_Range;
        begin
           Timespeed_Scale := Gtk.GRange.Gtk_Range(Gtkada.Builder.Get_Widget(Builder, "timespeed"));
           Gtkada.Handlers.Widget_Callback.Connect(Timespeed_Scale, "value_changed", Set_Timespeed'Access);
           Set_Timespeed(Timespeed_Scale);
        end;

        Xfen_Dehors := Gtkada.Builder.Get_Widget(Builder, "drawingarea_dehors");
        Xfen_Salle := Gtkada.Builder.Get_Widget(Builder, "drawingarea_salle");
        Gdk.GC.Gdk_New(GC_Epais, Gtk.Widget.Get_Root_Window(Xfen_Salle));
        Gdk.GC.Set_Line_Attributes(GC_Epais, 2, Gdk.GC.Line_Solid, Gdk.GC.Cap_Round, Gdk.GC.Join_Round);
        Gdk.GC.Set_Foreground(GC_Epais, Gdk.Color.Black(Gtk.Widget.Get_Default_Colormap));
        Gdk.GC.Set_Background(GC_Epais, Gdk.Color.White(Gtk.Widget.Get_Default_Colormap));

        Gtkada.Builder.Register_Handler (Builder, "aff_aide_afficher", Aide_Afficher'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_aide_fermer", Aide_Fermer'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_click_event_dehors", Click_Event_Dehors'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_click_event_salle", Click_Event_Salle'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_compute_placement", Compute_Placement'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_expose", Expose'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_pause_or_run", Pause_Or_Run'Access);
        -- Gtkada.Builder.Register_Handler (Builder, "aff_show_param", Show_Param'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_quitter", Quitter'Access);
        Gtkada.Builder.Do_Connect(Builder);

        Gtk.Widget.Show_All(Gtkada.Builder.Get_Widget(Builder, "Bridge"));
     end Init;

     procedure Changer_Etat(Id: Proc_Id; Etat: Proc_Etat) is
     begin
        Gdk.Threads.Enter;
        -- Put_Line(Natural'Image(Id) & " -> " & Proc_Etat'Image(Etat));
        Les_Objets(Id).Etat := Etat;
        Gtk.Widget.Draw(Gtk.Widget.Get_Toplevel(Xfen_Salle)); -- this should refresh the two windows
        Gdk.Threads.Leave;
     end Changer_Etat;

   end Bridge.Affic;