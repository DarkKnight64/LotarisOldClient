; © 2018 Aurora Interactive
; Lotaris Build Manager

Include "v4init.bb"
LogToConsole("Configuring build number...")
Global temp = OpenFile("v4build.txt")

temp4 = ReadLine(temp)

temp2 = Int temp4
build = temp2 + 1
temp2 = build
SeekFile(temp, 0) ; go to byte #0 in the file (start of zero-indexed file)
temp3 = Str build 
WriteLine(temp, temp3)


LogToConsole("Lotaris build: " + build)