; Lotaris Client 4.0
; © 2017-2018 Lotaris.
; Inspired by ROBLOX - © 2003-2005 ROBLOX, Inc. © 2005-2018 ROBLOX Corporation.

; --- FILE INFORMATION --- 
;
; FILE NAME : "v4exit.bb"
;
; FILE PURPOSE : Exit Lotaris, cleaning up properly
;
; FILE REVISIONS : 
; Oct 4 2018 v1.0
; Oct 4 2018 v1.5 : Added freeing of master window gadget
; --- END FILE INFORMATION ---

Function v4exit()
	logtoconsole("Exiting...")
	CloseFile v4_mdata
	logtoconsole("Closed " + v4_mdata_fname$)
		For m.material = Each material ; clean up materials
			logtoconsole("Freeing Material Image " + m\v4mtex$) ; log
			FreeImage m\v4mimage ; free material image
			logtoconsole("Freed Material Image") ; log that.
		Next ; loop
		logtoconsole("Freeing Master Window Gadget")
		FreeGadget lwin
		End
End Function

