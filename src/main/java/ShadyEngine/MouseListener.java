package ShadyEngine;

import static org.lwjgl.glfw.GLFW.*;


public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0;
        this.scrollY = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastY = 0;
        this.lastX = 0;
    }

    public static MouseListener getInstance() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return instance;
    }

    public static void mousePoseCallback(long window, double xpos, double ypos) {
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
        getInstance().xPos = xpos;
        getInstance().yPos = ypos;
        getInstance().isDragging = getInstance().mouseButtonPressed[0] || getInstance().mouseButtonPressed[1] || getInstance().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < getInstance().mouseButtonPressed.length) {
                getInstance().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < getInstance().mouseButtonPressed.length) {
                getInstance().mouseButtonPressed[button] = false;
                getInstance().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        getInstance().scrollX = xOffset;
        getInstance().scrollY = yOffset;
    }

    public static void endFrame() {
        getInstance().scrollX = 0;
        getInstance().scrollY = 0;
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
    }

    public static float getx() {
        return (float) getInstance().xPos;
    }

    public static float gety() {
        return (float) getInstance().yPos;
    }

    public static float getDx() {
        return (float) (getInstance().lastX - getInstance().xPos);
    }

    public static float getDy() {
        return (float) (getInstance().lastY - getInstance().yPos);
    }

    public static float getScrollX() {
        return (float) getInstance().scrollX;
    }

    public static float getScrollY() {
        return (float) getInstance().scrollY;
    }

    public static boolean isDragging() {
        return getInstance().isDragging;
    }

    public static boolean isMouseButtonDown(int button) {
        if (button < getInstance().mouseButtonPressed.length) {
            return getInstance().mouseButtonPressed[button];
        }
        else {
            return false;
        }
    }

}