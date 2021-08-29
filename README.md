# This version of Lotaris is Public Domain. 

# LotarisOldClient

This is the old Lotaris source code, before I threw everything out and restarted on December 1st, 2018, with BlitzMax NG instead of BlitzPlus. This was worked on intermittently from August 10th, 2018, to December 1st, 2018.

This was a pre-Beta 2 release, and the saving to file is partially broken. Normal blocks will still save and load, but materials don't load.

No support will be provided at all.

Lotaris development has ceased as of April 24, 2019.

Controls:
- MOUSE1: Add block
Use the various options to change colour, etc, and save

FILE FORMAT:

[Lotaris-Dataformat 1.3] is the header, then followed by...
[X] x-position of block
[Y] y-position of block
[SX] Size X
[SY] Size Y
[CR] Colour R
[CG] Colour G
[CB] Colour B
[BLOCK] End separator.

[MATERIAL] is used if there is a material, but the loading never worked quite right, and then the image is listed.

32x32 is the material size, but you can define new materials in Materials.ldata, the original set weren't finished, but I was planning to add more and may upload more.

Lotaris © 2017 - 2019.

This also includes a couple of LDFL test files for you, test24 and test25. There are 28 in total, I can upload them if you want, but some of them will not load correctly due to using an older version of the format.


Credit to SyntaxBomb for some support 

lotaris.net coming soon
