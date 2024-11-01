package ShadyEngine;

import org.w3c.dom.html.HTMLImageElement;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

    private boolean ChangingScene = false;
    private float TimeToChangeScene = 2.0f;

    public LevelEditorScene() {
        System.out.println("LevelEditorScene constructor");
    }

    @Override
    public void update(float dt) {

//        shows fps
        System.out.println("fps: " + 1.0f / dt);

        if (!ChangingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE))
        {
            ChangingScene = true;
        }
        if(ChangingScene && TimeToChangeScene > 0.0f)
        {
            TimeToChangeScene -= dt;
            Window.get().r -= dt * 5;
            Window.get().g -= dt * 5;
            Window.get().b -= dt * 5;
        }
        else if (ChangingScene)
        {
            Window.changeScene(1);
        }
    }
}
