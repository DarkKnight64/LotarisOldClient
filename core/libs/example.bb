;Includes
Include "BlitzXML.bb"

;Set up display
AppTitle "BlitzXML Example"
Graphics3D 800,600,0,2
SetBuffer BackBuffer()

;Load the xml file (and clock the time it takes to parse)
ms = MilliSecs()

rootnode = xmlLoad("example.xml")

parsetime = MilliSecs()-ms
DebugLog "Parse time: "+parsetime+" millisecs"

;Display any errors
errs = xmlErrorCount()
If errs > 0 Then
	Print "Errors have occurred while parsing the xml file:"
	Print
	For i = 1 To errs
		Print "Byte #"+xmlErrorPosition(i)+": "+xmlError(i)
	Next
	Print
	Print "Press any key to attempt to continue"
	WaitKey()
End If


;Get some information about the world (found under the info node)
infonode = xmlNodeFind("info", rootnode)
node = xmlNodeFind("title", infonode)
title$ = xmlNodeDataGet(node)
node = xmlNodeFind("author", infonode)
author$ = xmlNodeDataGet(node)
node = xmlNodeFind("description", infonode)
description$ = xmlNodeDataGet(node)

;Create entities founder under the world node
worldnode = xmlNodeFind("world", rootnode)		;Get the handle of the world node
count = xmlNodeChildCount(worldnode)	;Get the world node's child count
For i = 1 To count 						;Loop through each child node of the world node
	entitynode = xmlNodeChild(worldnode, i)				;Get the child node
	If xmlNodeNameGet(entitynode) = "entity" Then		;Check if it's an entity
		t$ = xmlNodeAttributeValueGet(entitynode, "type")	;Get the entity type
	End If
	
	;Light
	If t = "light" Then
		src = xmlNodeFind("source", entitynode)
		If src<>0 Then
			lighttype$ = xmlNodeAttributeValueGet(src, "type")
			If lighttype = "directional" Then entity = CreateLight(1)
			If lighttype = "point" Then entity = CreateLight(2)
			If lighttype = "spot" Then entity = CreateLight(3)
			If xmlNodeAttributeValueGet(src, "range")<>"" Then LightRange entity, xmlNodeAttributeValueGet(src, "range")
			If xmlNodeAttributeValueGet(src, "innerangle")<>"" Then LightConeAngles entity, xmlNodeAttributeValueGet(src, "innerangle"), xmlNodeAttributeValueGet(src, "outerangle")
		End If
		pos = xmlNodeFind("position", entitynode)
		If pos<>0 Then PositionEntity entity, xmlNodeAttributeValueGet(pos, "x"), xmlNodeAttributeValueGet(pos, "y"), xmlNodeAttributeValueGet(pos, "z")
		rot = xmlNodeFind("rotation", entitynode)
		If rot<>0 Then RotateEntity entity, xmlNodeAttributeValueGet(rot, "pitch"), xmlNodeAttributeValueGet(rot, "yaw"), xmlNodeAttributeValueGet(rot, "roll")
		clr = xmlNodeFind("color", entitynode)
		If clr<>0 Then LightColor entity, xmlNodeAttributeValueGet(clr, "r"), xmlNodeAttributeValueGet(clr, "g"), xmlNodeAttributeValueGet(clr, "b")
	End If
	
	;Cube
	If t = "cube" Then
		entity = CreateCube()
		pos = xmlNodeFind("position", entitynode)
		If pos<>0 Then PositionEntity entity, xmlNodeAttributeValueGet(pos, "x"), xmlNodeAttributeValueGet(pos, "y"), xmlNodeAttributeValueGet(pos, "z")
		rot = xmlNodeFind("rotation", entitynode)
		If rot<>0 Then RotateEntity entity, xmlNodeAttributeValueGet(rot, "pitch"), xmlNodeAttributeValueGet(rot, "yaw"), xmlNodeAttributeValueGet(rot, "roll")
		clr = xmlNodeFind("color", entitynode)
		If clr<>0 Then EntityColor entity, xmlNodeAttributeValueGet(clr, "r"), xmlNodeAttributeValueGet(clr, "g"), xmlNodeAttributeValueGet(clr, "b")
		scl = xmlNodeFind("scale", entitynode)
		If scl<>0 Then ScaleEntity entity, xmlNodeAttributeValueGet(scl, "x"), xmlNodeAttributeValueGet(scl, "y"), xmlNodeAttributeValueGet(scl, "z")
	End If
	
	;Sphere
	If t = "sphere" Then
		entity = CreateSphere()
		pos = xmlNodeFind("position", entitynode)
		If pos<>0 Then PositionEntity entity, xmlNodeAttributeValueGet(pos, "x"), xmlNodeAttributeValueGet(pos, "y"), xmlNodeAttributeValueGet(pos, "z")
		rot = xmlNodeFind("rotation", entitynode)
		If rot<>0 Then RotateEntity entity, xmlNodeAttributeValueGet(rot, "pitch"), xmlNodeAttributeValueGet(rot, "yaw"), xmlNodeAttributeValueGet(rot, "roll")
		clr = xmlNodeFind("color", entitynode)
		If clr<>0 Then EntityColor entity, xmlNodeAttributeValueGet(clr, "r"), xmlNodeAttributeValueGet(clr, "g"), xmlNodeAttributeValueGet(clr, "b")
		scl = xmlNodeFind("scale", entitynode)
		If scl<>0 Then ScaleEntity entity, xmlNodeAttributeValueGet(scl, "x"), xmlNodeAttributeValueGet(scl, "y"), xmlNodeAttributeValueGet(scl, "z")
	End If
	
	;Camera
	If t = "camera" Then
		entity = CreateCamera()
		pos = xmlNodeFind("position", entitynode)
		If pos<>0 Then PositionEntity entity, xmlNodeAttributeValueGet(pos, "x"), xmlNodeAttributeValueGet(pos, "y"), xmlNodeAttributeValueGet(pos, "z")
		rot = xmlNodeFind("rotation", entitynode)
		If rot<>0 Then RotateEntity entity, xmlNodeAttributeValueGet(rot, "pitch"), xmlNodeAttributeValueGet(rot, "yaw"), xmlNodeAttributeValueGet(rot, "roll")
		clr = xmlNodeFind("bgcolor", entitynode)
		If clr<>0 Then CameraClsColor entity, xmlNodeAttributeValueGet(clr, "r"), xmlNodeAttributeValueGet(clr, "g"), xmlNodeAttributeValueGet(clr, "b")
		view = xmlNodeFind("viewport", entitynode)
		If view<>0 Then CameraViewport entity, xmlNodeAttributeValueGet(view, "x"), xmlNodeAttributeValueGet(view, "y"), xmlNodeAttributeValueGet(view, "width"), xmlNodeAttributeValueGet(view, "height")
	End If
	
Next

;Test the file-saver by saving the load XML data to a temp. file
xmlSave("temp.xml", rootnode)

;Render the scene that was loaded completely from the XML file
While Not KeyDown(1)
	Delay 1
	RenderWorld
	
	;Display scene data
	Text 10,10,title$
	Text 10,30,"By "+author$
	Text 10,60,description$
	
	Flip
Wend

;Now free up nodes from the XML file
xmlNodeDelete(rootnode) ;Deleting the root node is how you delete a loaded file's contents

;NOTE: Freeing up memory before exiting is not necessary - this is simply a demonstration
;for when you WILL need to "close" XML files still in memory. This would occur when you are
;loading multiple XML files or complex XML files with cross-references to other files, for example.


End