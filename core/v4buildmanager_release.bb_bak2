; © 2018 Aurora Interactive
; Lotaris build manager for external release versions. Does not contain the code required to write the new build number.

; current external release: version 1.0.056.0 beta

Include "v4init.bb"
LogToConsole("Configuring build number...")
Global temp = OpenFile("v4build.txt")

temp4 = ReadLine(temp)

temp2 = Int temp4
build = temp2 + 1
temp2 = build



LogToConsole("Lotaris build: " + build)