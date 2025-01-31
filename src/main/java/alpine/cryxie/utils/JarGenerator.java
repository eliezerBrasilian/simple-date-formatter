package alpine.cryxie.utils;

import java.io.*;
import java.nio.file.*;

public class JarGenerator {
    private File jarFile;
    private static final String BUILD_DIR = "build";
    private static final String CLASSES_DIR = BUILD_DIR + "/classes";
    private static final String MANIFEST_PATH = BUILD_DIR + "/META-INF/MANIFEST.MF";

    public void generateJar() throws IOException, InterruptedException {
       
        String name = "simple_date_formatter";
        String version ="0.0.1";
        String mainClass = "alpine.cryxie.Main";
        String jarName = name + "@" + version + ".jar";

        // Define caminho final do JAR
        jarFile = new File(BUILD_DIR + "/" + jarName);

        // Garante que os diretórios necessários existam
        new File(BUILD_DIR).mkdirs();
        new File(CLASSES_DIR).mkdirs();
        new File(BUILD_DIR + "/META-INF").mkdirs();

        // 🔹 1. Compilar os arquivos Java para build/classes
        compileJavaSources();

        // 🔹 2. Criar o arquivo MANIFEST.MF
        generateManifest(mainClass);

        // 🔹 3. Criar o JAR
        createJar();
    }

    private void compileJavaSources() throws IOException, InterruptedException {
        // Comando para compilar os arquivos .java e gerar os .class em "build/classes"
        String[] compileCommand = {
            "powershell.exe", "-Command",
            "Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { $_.FullName } > sources.txt; " +
            "javac -d " + CLASSES_DIR + " -sourcepath src $(Get-Content sources.txt)"
        };

        runCommand(compileCommand);
    }

    private void generateManifest(String mainClass) throws IOException {
        String manifestContent = "Manifest-Version: 1.0\n" +
                                 "Main-Class: " + mainClass + "\n";

        Files.write(Paths.get(MANIFEST_PATH), manifestContent.getBytes());
    }

    private void createJar() throws IOException, InterruptedException {
        // Comando para gerar o JAR com base nas classes compiladas
        String[] jarCommand = {
            "jar", "cfm", jarFile.getAbsolutePath(), MANIFEST_PATH, "-C", CLASSES_DIR, "."
        };

        runCommand(jarCommand);
    }

    private void runCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Erro ao executar comando: " + String.join(" ", command));
        }
    }

    public File getJarFile() {
        return jarFile;
    }
}
