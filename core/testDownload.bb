Include "ClientDownloader.bb"

file% = OpenRemoteFile("http://mojolabs.nz/codearcs.php")
save% = WriteFile("received.avi")

Graphics 400, 60, 32, 2
SetBuffer BackBuffer()

While Not KeyHit(1)
	Cls
	
	Text 10, 10, "Downloaded: " + total% + "/" + RemoteFileSize(file%) + "(" + (Int 100 * (Float total% / RemoteFileSize(file%))) + "%)"
	
	If Not EORF(file%)
		N% = WriteRemoteBytes(file%, save%, 0, RemoteReadAvail(file%))
		total% = total% + N%
	Else
		Text 10, 30, "FINISHED!"
	EndIf
	
	Flip
Wend

CloseFile save%
CloseRemoteFile(file%)