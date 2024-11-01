package ShadyEngine;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClearColor;

public class Window {

	private int width ,height;
	private String tittle;
	private long glfwWindow;
	
	private static Window window = null;
	
	private Window() {
		this.width = 1920;
		this.height = 1080;
		this.tittle = "Shady Engine";
	}
	
	public static Window get() {
		if(Window.window == null) {
			Window.window = new Window();
		}
		
		return Window.window;
	}
	
	public void run() {
		System.out.println("Hello LWJGL" + Version.getVersion() + "!");
		
		init();
		loop();

		glfwFreeCallbacks(glfwWindow);
		glfwDestroyWindow(glfwWindow);

		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!glfwInit()) {
			System.out.println("unable to inizialize");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
		
		glfwWindow = glfwCreateWindow(this.width, this.height, this.tittle, NULL, NULL);
		
		if(glfwWindow == NULL) {
			System.out.println("failed to create window");
		}

		glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePoseCallback);
		glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
		glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);

		
		glfwMakeContextCurrent(glfwWindow);
		
		glfwSwapInterval(1);
		
		glfwShowWindow(glfwWindow);
		
		GL.createCapabilities();
	}
	
	public void loop() {
		while(!glfwWindowShouldClose(glfwWindow)) {
			glfwPollEvents();
			
			glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			
			glfwSwapBuffers(glfwWindow);
		}
	}
}
