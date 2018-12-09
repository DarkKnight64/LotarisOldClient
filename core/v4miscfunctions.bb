; v4miscfunctions

; Lotaris Compatible With - 4.1.312.0+ (Post Beta 1+)

Function v4setstatustext()
If blocktype=0 SetStatusText lwin,"Normal block - Click to add. Use Tools -> Set Brick Colour... to change colour"
If blocktype=1 SetStatusText lwin,"Erase - Click to erase blocks. Select Tools -> Erase Tool again to turn off."
If blocktype=2 SetStatusText lwin,"Material - Use Tools -> Set Brick Material... to change the material. CLICK to add material."
End Function