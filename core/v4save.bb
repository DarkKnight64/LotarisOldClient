Function save_map_information()
count=1
currentfile$ = RequestFile("Save game","ldfl",True)
If currentfile$ = 0 
Return ; 1.0.298.0
EndIf
logtoconsole("Saving file " + currentfile$ + "to disk")
logtoconsole("ExecFile('v4save.bat ' + currentfile$)")
ExecFile("v4save.bat " + currentfile$) ; Utility Batch File for BlitzPlus not having a createfile function

Delay 750 ; wait for batch file to complete processing
logtoconsole("batch file complete")
logtoconsole("Opening file...")
currentfile2$ = OpenFile(currentfile$)
logtoconsole("Done.")
SeekFile currentfile2$, 0
logtoconsole("Jumped to byte #0")
logtoconsole("Writing header...")
WriteLine(currentfile2$, "Lotaris-Dataformat v1.35") ; header

logtoconsole("Writing block data...")
For b.block = Each block
logtoconsole("Writing block #" + count)
WriteLine(currentfile2$, b\blocktype)
If b\blocktype = 2
	WriteLine(currentfile2$, b\x)
	WriteLine(currentfile2$, b\y)
	For m.material = Each material
		WriteLine(currentfile2$, m\v4mtex$)
	Next
	WriteLine(currentfile2$, "MATERIAL")
EndIf 
If b\blocktype <> 2
WriteLine(currentfile2$, b\x)
WriteLine(currentfile2$, b\y)
WriteLine(currentfile2$, b\sizex)
WriteLine(currentfile2$, b\sizey)
WriteLine(currentfile2$, b\colourr)
WriteLine(currentfile2$, b\colourg)
WriteLine(currentfile2$, b\colourb)
WriteLine(currentfile2$, b\mtex)
WriteLine(currentfile2$, "BLOCK")
EndIf
count=count+1

Next
; --COMMENTED OUT DUE TO TENDENCY TO WRITE TENS OF MILLIONS OF EMPTY LINES TO THE FILE--;
;failsafe$ = ReadLine(currentfile2$)
;If failsafe$ = "" Return 
;If failsafe$ <> ""
;Repeat
;WriteLine(currentfile2$, "")
;Until Eof(currentfile2$)
;EndIf
Notify ("save complete")
CloseFile(currentfile2$)
Return
End Function

Function load_map_information()
currentfile$ = RequestFile("Open game","ldfl",False)
If currentfile$ = 0
debug_throw_error_and_crash_violently("v4save.bb -> function load_map_information() -> currentfile$ = RequestFile('Save game', 'ldfl', False): No file chosen", 18)
EndIf

currentfile2$ = OpenFile(currentfile$)
SeekFile currentfile2$, 0
checksum2$ = ReadLine(currentfile2$)

If checksum2$ <> "Lotaris-Dataformat v1.35"
debug_throw_error_and_crash_violently("v4save.bb -> function load_map_information() -> checksum2 = ReadLine(currentfile$): File is corrupt, header missing or corrupt, file cannot be loaded", 19)
EndIf

Cls
Delay 750 ; why not
count=1
While Not Eof(currentfile2$)



b.block = New block

sbtype = Int ReadLine(currentfile2$)
If sbtype = 2
	
	sx = Int ReadLine(currentfile2$)
	sy = Int ReadLine(currentfile2$)
	st = ReadLine(currentfile2$)
	For m.material = Each material ; hacky shit
		temp = m\v4mtex$
		If temp = st
		DrawImage m\v4mimage,sx,sy
		EndIf
	Next
	ReadLine(currentfile2$)
EndIf
If sbtype <> 2
EndIf
sx = Int ReadLine(currentfile2$)
sy = Int ReadLine(currentfile2$)
bsx = Int ReadLine(currentfile2$)
bsy = Int ReadLine(currentfile2$)
bcr = Int ReadLine(currentfile2$)
bcg = Int ReadLine(currentfile2$)
bcb = Int ReadLine(currentfile2$)
bmt = Int ReadLine(currentfile2$)
ReadLine(currentfile2$)
b\blocktype = sbtype
b\x = sx
b\y = sy
b\sizex = bsx
b\sizey = bsy
b\colourr = bcr
b\colourg = bcg
b\colourb = bcb
b\mtex = mtex
Color b\colourr, b\colourg, b\colourb
Rect b\x,b\y,b\sizex,b\sizey,1
Color ocolourr, ocolourg, ocolourb


count = count+1 
Wend
Notify("load complete")
Return 
End Function