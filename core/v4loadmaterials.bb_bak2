
; Lotaris Client 4.0
; © 2017-2018 Lotaris.
; Inspired by ROBLOX - © 2003-2005 ROBLOX, Inc. © 2005-2018 ROBLOX Corporation.

; --- FILE INFORMATION --- 
;
; FILE NAME : "v4loadmaterials.bb"
;
; FILE PURPOSE : Load the Materials List for the Lotaris Materials
;
; FILE REVISIONS : 
; Oct 3 2018 v1.0
; Oct 4 2018 v2.0 : Added LoadMaterials (Hacky-ass function)
; Oct 4 2018 v3.0 : Actually made nonshit loadmaterial code
; Oct 4 2018 v3.1 : Added Material Names
; --- END FILE INFORMATION ---

Global v4_mdata_fname$ = "data/materialdata.ldata" ; File Name for Reading
Global v4_mdata=OpenFile(v4_mdata_fname$) ; Open File for r/w

Function v4readmaterials()
	logtoconsole("v4loadmaterials is reading " + v4_mdata_fname$) ; log that.
		While Not Eof(v4_mdata) ; Loop until end of file
			m.material = New material ; create new material
			m\v4mid$ = ReadLine(v4_mdata) ; Set Material ID 
			logtoconsole("Creating MaterialID#" + m\v4mid)
			m\v4mname$ = ReadLine(v4_mdata) ; Set Material Name
			logtoconsole("With name " + m\v4mname$)
			m\v4mtex$ = ReadLine(v4_mdata) ; Set Material TexID (ImageID)

			logtoconsole("...with Image ID " + m\v4mtex$) ; Log that.
			
			m\v4mimage = LoadImage(m\v4mtex$) ; Load image set by V4 MaterialTexture
			logtoconsole("Loaded Material Image " + m\v4mtex$) ; log that.
		Wend
		

	logtoconsole("Material Loading done!")
End Function

