package samples.positioning;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class AspectRatio {
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    // The window handle
    private long window;

    int WIDTH = 600;
    int HEIGHT = 600;

    private int positionBufferObject;

    private int theProgram;

    private int offsetLocation;

    private int matrixLocation;

    private final float[] vertexPositions = {
            0.25f, 0.25f, 1.25f, 1.0f,
            0.25f, -0.25f, 1.25f, 1.0f,
            -0.25f, 0.25f, 1.25f, 1.0f,

            0.25f, -0.25f, 1.25f, 1.0f,
            -0.25f, -0.25f, 1.25f, 1.0f,
            -0.25f, 0.25f, 1.25f, 1.0f,

            0.25f, 0.25f, 2.75f, 1.0f,
            -0.25f, 0.25f, 2.75f, 1.0f,
            0.25f, -0.25f, 2.75f, 1.0f,

            0.25f, -0.25f, 2.75f, 1.0f,
            -0.25f, 0.25f, 2.75f, 1.0f,
            -0.25f, -0.25f, 2.75f, 1.0f,

            -0.25f, 0.25f, 1.25f, 1.0f,
            -0.25f, -0.25f, 1.25f, 1.0f,
            -0.25f, -0.25f, 2.75f, 1.0f,

            -0.25f, 0.25f, 1.25f, 1.0f,
            -0.25f, -0.25f, 2.75f, 1.0f,
            -0.25f, 0.25f, 2.75f, 1.0f,

            0.25f, 0.25f, 1.25f, 1.0f,
            0.25f, -0.25f, 2.75f, 1.0f,
            0.25f, -0.25f, 1.25f, 1.0f,

            0.25f, 0.25f, 1.25f, 1.0f,
            0.25f, 0.25f, 2.75f, 1.0f,
            0.25f, -0.25f, 2.75f, 1.0f,

            0.25f, 0.25f, 2.75f, 1.0f,
            0.25f, 0.25f, 1.25f, 1.0f,
            -0.25f, 0.25f, 1.25f, 1.0f,

            0.25f, 0.25f, 2.75f, 1.0f,
            -0.25f, 0.25f, 1.25f, 1.0f,
            -0.25f, 0.25f, 2.75f, 1.0f,

            0.25f, -0.25f, 2.75f, 1.0f,
            -0.25f, -0.25f, 1.25f, 1.0f,
            0.25f, -0.25f, 1.25f, 1.0f,

            0.25f, -0.25f, 2.75f, 1.0f,
            -0.25f, -0.25f, 2.75f, 1.0f,
            -0.25f, -0.25f, 1.25f, 1.0f,


            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,

            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,

            0.8f, 0.8f, 0.8f, 1.0f,
            0.8f, 0.8f, 0.8f, 1.0f,
            0.8f, 0.8f, 0.8f, 1.0f,

            0.8f, 0.8f, 0.8f, 1.0f,
            0.8f, 0.8f, 0.8f, 1.0f,
            0.8f, 0.8f, 0.8f, 1.0f,

            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,

            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,

            0.5f, 0.5f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.0f, 1.0f,

            0.5f, 0.5f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.0f, 1.0f,

            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,

            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,

            0.0f, 1.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f, 1.0f,

            0.0f, 1.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f, 1.0f
    };

    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");

        try {
            init();
            loop();

            // Release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            // Terminate GLFW and release the GLFWerrorfun
            glfwTerminate();
            errorCallback.release();
        }
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (glfwInit() != GL11.GL_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL11.GL_FALSE); // the window will stay hidden after creation

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GL11.GL_TRUE); // We will detect this in our rendering loop
            }
        });

        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                System.out.println(width+" / "+height);
                WIDTH = width;
                HEIGHT = height;
            }
        });

        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (GLFWvidmode.width(vidmode) - WIDTH) / 2,
                (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void initializeProgram() throws IOException, URISyntaxException {
        String vertexShader = new String(Files.readAllBytes(Paths.get(getClass().getResource("matrix.vert").toURI())));
        String fragmentShader = new String(Files.readAllBytes(Paths.get(getClass().getResource("../basics/RGB.frag").toURI())));

        ArrayList<Integer> shaderList = new ArrayList<>();
        shaderList.add( createShader( GL_VERTEX_SHADER, vertexShader ) );
        shaderList.add( createShader( GL_FRAGMENT_SHADER, fragmentShader ) );

        theProgram = createProgram( shaderList );

        offsetLocation = glGetUniformLocation(theProgram, "offset");
        matrixLocation = glGetUniformLocation(theProgram, "perspectiveMatrix");

        shaderList.forEach(GL20::glDeleteShader);
    }

    private int createShader(int shaderType, String shaderFile) {
        int shader = glCreateShader( shaderType );
        glShaderSource( shader, shaderFile );

        glCompileShader( shader );

        int status = glGetShaderi( shader, GL_COMPILE_STATUS );
        if ( status == GL_FALSE ) {
            int infoLogLength = glGetShaderi( shader, GL_INFO_LOG_LENGTH );

            String infoLog = glGetShaderInfoLog( shader, infoLogLength );

            String shaderTypeStr = null;
            switch ( shaderType ) {
                case GL_VERTEX_SHADER:
                    shaderTypeStr = "vertex";
                    break;
                case GL_GEOMETRY_SHADER:
                    shaderTypeStr = "geometry";
                    break;
                case GL_FRAGMENT_SHADER:
                    shaderTypeStr = "fragment";
                    break;
            }

            System.err.printf( "Compile failure in %s shader:\n%s\n", shaderTypeStr, infoLog.trim() );
        }

        return shader;
    }

    private int createProgram(ArrayList<Integer> shaderList) {

        int program = glCreateProgram();

        for ( Integer shader : shaderList ) {
            glAttachShader( program, shader );
        }

        glLinkProgram( program );

        int status = glGetProgrami( program, GL_LINK_STATUS );
        if ( status == GL_FALSE ) {
            int infoLogLength = glGetProgrami( program, GL_INFO_LOG_LENGTH );

            String infoLog = glGetProgramInfoLog( program, infoLogLength );
            System.err.printf( "Linker failure: %s\n", infoLog.trim() );
        }

        for ( Integer shader : shaderList ) {
            glDetachShader( program, shader );
        }

        return program;
    }

    private void initializeVertexBuffer() {
        FloatBuffer vertexPositionsBuffer = BufferUtils.createFloatBuffer(vertexPositions.length);
        vertexPositionsBuffer.put(vertexPositions);
        vertexPositionsBuffer.flip();

        positionBufferObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
        glBufferData(GL_ARRAY_BUFFER, vertexPositionsBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private void loop() throws IOException, URISyntaxException {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLContext.createFromCurrent();

        // Set the clear color
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        initializeProgram();
        initializeVertexBuffer();

        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glFrontFace(GL_CW);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (glfwWindowShouldClose(window) == GL_FALSE) {

            glViewport(0, 0, WIDTH, HEIGHT);

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glUseProgram(theProgram);

            glUniform2f(offsetLocation, 0.5f, 0.75f);

            final float near = 0.5f;
            final float far = 3f;
            FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
            matrixBuffer.put(new float[] {(float)HEIGHT/WIDTH, 0f, 0f, 0f});
            matrixBuffer.put(new float[] {0f, 1f, 0f, 0f});
            matrixBuffer.put(new float[] {0f, 0f, (near+far)/(far-near), 1f});
            matrixBuffer.put(new float[] {0f, 0f, 2*near*far/(near-far), 0f});
            matrixBuffer.flip();
            glUniformMatrix4fv(matrixLocation, false, matrixBuffer);

            glBindBuffer(GL15.GL_ARRAY_BUFFER, positionBufferObject);
            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);
            glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, Float.BYTES*4*36);

            glDrawArrays(GL_TRIANGLES, 0, 36);

            glDisableVertexAttribArray(0);
            glDisableVertexAttribArray(1);
            glUseProgram(0);

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
        System.out.println("Buy, LWJGL!");
    }

    public static void main(String[] args) {
        new AspectRatio().run();
    }
}
