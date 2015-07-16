# RGGE-Engine

RGGE is a 2d java game engine using LWJGL3

To use RGGE please download the dependencies from [Here](http://spanishtech.github.io/RGGE-Engine/dep/ "Dependencies")
After downloading them add them to a projects class path.

From here creating a game is simple, but you must know some basic ideas first.
#####Debugging
While you are debugging you must create a new instance of the engine. Basically you are calling the engine. This means you need to create a new Main function that creates a new Engine instance but with an extra boolean in the constructor (This boolean tells the engine you're in debugging mode and it will not act as a release). The Main function you created will only be used while debugging and when it is released will never be touched again
#####Release
Upon releasing you need not do anything to your Main function as it will be ignored. During the release the engine will call your Game class. This means that you will be loaded in when the engine is ready. Keep in mind that load order of jars is important as Mods act in the same ways games work.
#####Creating your game
Now all you need to do is create a new Main function (Entry point) and call a new engine with the boolean true in the constructor (To notify the engine it's being run in debug mode). Similar to:
`Engine engine = new Engine("My cool game name", true);`
after that you will want to create a new Subclass of the Game class. Then go back into the main function and add a new line
`engine.loadGame(new MyGameSubClass());`
now your game is running. Simply put all your logic in the constructor of the MyGameSubClass class. From there you can create scenes, focus scenes and various other things this engine can handle

##Documentation
[Link](http://spanishtech.github.io/RGGE-Engine/ "Documentation")
