package book.chapters

import java.util.logging.Logger

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL as GLCommons
import org.lwjgl.opengl.GL46 as GL


import utils.OSDetector
import utils.LangExtensions.nullptr
import utils.LangExtensions.isNullPtr

object C04_HelloWindow {

    val log = Logger.getLogger("04_HelloWindow")
    val osDetector = OSDetector()

    // Window size
    const val WIDTH = 800
    const val HEIGHT = 600

    @JvmStatic
    fun main(args: Array<String>) {
        // Init GLFW
        GLFW.glfwInit()
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3)
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3)
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE)

        // ME: Detect OS
        log.info("OS: ${osDetector.osName}")

        // In Mac OS X
        if (osDetector.isMac) {
            GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL.GL_TRUE)
        }


        // Create Window
        val windowPtr = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "LearnOpenGL Book", nullptr, nullptr)
        if (windowPtr.isNullPtr()) {
            log.severe("Failed to create GLFW window")
            GLFW.glfwTerminate()
        }
        log.info("Window ptr: $windowPtr")
        GLFW.glfwMakeContextCurrent(windowPtr)
        // Needed for LWJGL
        val capabilities = GLCommons.createCapabilities()
        log.info("GL Capabilities: $capabilities")

        // GL Viewport
        GL.glViewport(0, 0, WIDTH, HEIGHT)

        // Set up resize callback
        GLFW.glfwSetFramebufferSizeCallback(windowPtr, ::onWindowResize)

        // Main loop
        while (!GLFW.glfwWindowShouldClose(windowPtr)) {
            // Input
            processInput(windowPtr)

            // Rendering
            GL.glClearColor(0.2f, 0.3f, 0.3f, 1f)
            GL.glClear(GL.GL_COLOR_BUFFER_BIT)

            // Swap buffers (Double buffers) and poll events
            GLFW.glfwSwapBuffers(windowPtr)
            GLFW.glfwPollEvents()
        }

        // Terminate
        GLFW.glfwTerminate()
    }


    private fun onWindowResize(windowPtr: Long, width: Int, height: Int) {
        GL.glViewport(0, 0, width, height)
    }

    private fun processInput(windowPtr: Long) {
        if (GLFW.glfwGetKey(windowPtr, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) {
            GLFW.glfwSetWindowShouldClose(windowPtr, true)
        }
    }
}