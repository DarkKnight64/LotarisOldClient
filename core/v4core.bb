Include "v4init.bb"
Include "v4buildmanager.bb"
Include "v4loadmaterials.bb"
Include "v4initwindow.bb"
Include "v4createblock.bb"
Include "v4changecolour.bb"
Include "v4miscfunctions.bb"
Include "v4save.bb"
Include "v4exit.bb"

gamestate=1


If gamestate <> 1 debug_throw_error_and_crash_violently("Somehow we oofed", 1)



Repeat
; --- MAIN LOOP r1 --- ;
	If MouseDown(1) gamestate=2

	If gamestate=2 create_block(colourr,colourg,colourb,0)
	FlipCanvas(game)
	v4setstatustext()
	UpdateWindowMenu lwin
Select WaitEvent()
Case $803
v4exit()
FreeGadget lwin

Exit
End 


Case $1001
menuid=EventData()
Select menuid

Case 1

logtoconsole("Deleting all parts")

For b.block = Each block 
Delete b.block
Next 

Color 0,0,0
Rect 0,0,GraphicsWidth(),GraphicsHeight(),1

Color ocolourr,ocolourg,ocolourb

Case 2 

load_map_information()

Case 3

save_map_information()
Case 4

Case 5


Case 101

change_block_colour()


Case 102
	Notify("Lotaris Materials are in development.")
	ShowGadget mtmenu
	EnableGadget mtmenu
	logtoconsole("Populating Material Menu...")
	If Not v4materialspopulated
		For m.material = Each material
			AddGadgetItem mtmenulb1,m\v4mname$ ; add gadget item
			v4materialspopulated=1 ; material populated check so not populated multiple times
		Next
	EndIf 

Repeat
Select WaitEvent()
Case $803

	HideGadget mtmenu
	DisableGadget mtmenu
	Exit

Case $401
	If EventSource()=mtmenulb1 ; eventsource
		For m.material = Each material ; loop
			temp=Int(m\v4mid$)
			If temp = SelectedGadgetItem(mtmenulb1) ; if v4mid converted to an int is equal to selected gadget item, repeated for every material, set the current material to v4mid (int)
				v4currentmaterial=temp
				blocktype=2
			EndIf
		Next
	EndIf

End Select
Forever 




Case 103

ShowGadget bsmenu
EnableGadget bsmenu

Repeat
Select WaitEvent()
Case $803
HideGadget bsmenu
DisableGadget bsmenu
Exit


Case $401
	
	sizex=Int TextFieldText$(bsmenut1)
	sizey=Int TextFieldText$(bsmenut2)
	gsizex=sizex
	gsizey=sizey
End Select 

Forever



Case 104
Notify("Gridsize Manipulation is deprecated as of 1.0.307.0 and will be removed soon.")
ShowGadget bgmenu
EnableGadget bgmenu

Repeat
Select WaitEvent()
Case $803
DisableGadget bgmenu
HideGadget bgmenu
Exit



Case $401

gsizex=Int TextFieldText$(bgmenut1)
gsizey=Int TextFieldText$(bgmenut2)

End Select 

Forever
Case 105

ShowGadget sbmenu
EnableGadget sbmenu

Repeat
	Select WaitEvent()
	Case $803
		HideGadget sbmenu
		DisableGadget bsmenu
	Default 
	End Select
Forever

Case 106

If blocktype=0
	blocktype=1
	SetStatusText lwin,"Eraser is on." 
	Notify("Eraser turned on.")
	ElseIf blocktype=1
	SetStatusText lwin,"" 
	Notify("Eraser turned off.")
	blocktype=0 
EndIf

Case 107

ShowGadget fxmenu
EnableGadget fxmenu



Case 200

Case 201

Case 202

Case 203

Case 204



Case 301
ExecFile("help.exe")
Case 302
ExecFile("iexplore lotaris.net")
Case 303

Case 304

Case 400

Case 401

Case 402

Notify("Lotaris - © 2018 Lotaris Inc. Version " + version + "." + build + "." + revision + ".")
End Select
End Select

Forever
 