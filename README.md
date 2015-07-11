# RGGE-Engine

RGGE is a 2d java game engine using LWJGL3

To use simply create a project with LWJGL3 and LWJGL2_utils as libraries and copy the source into your project.

To use this engine there are a few basic concepts you need to know.
##Scenes
Scenes are how code is seperated. Simply create a scene and in Game.java and the function initGame(), put the line

`getSceneManager().switchScene("{{Name given to your scene}}");`

##Event management
To register an event you need to do the following. Implement the specific event handler. For Key events it's KeyHandler.
After implementing you need to write the line

`getEventManager().addListener(new KeyListener(this, new KeyEvent(new Key(0,0))));`
Notice: You can add a true to any Listener constructor to enable strict filtering. This means an event will only be sent if it matches the filter EXACTLY.

There is a lack of a overall game-object for now but you can create one if you want.

##Documentation
[Link](http://spanishtech.github.io/RGGE-Engine/ "Documentation")