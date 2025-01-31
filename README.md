compilando certinho

javac -d build/classes -sourcepath src src/Main.java src/DateTimeFormatterUtil.java

- compilando dinamicamente todos os arquivos java dentro de src inclusive sub diretorios (powershell)

  Get-ChildItem -Path src -Filter \*.java -Recurse | ForEach-Object { $\_.FullName } > sources.txt
  javac -d build/classes -sourcepath src $(Get-Content sources.txt)

2 - criar META-INF dentro do diretorio build, e então criar o MANIFEST.mf

3 - gerar o jar com as classes compiladas
jar cfm build/brasilian_date_formatter@0.0.1.jar build/META-INF/MANIFEST.MF -C build/classes .

4 - testando:
java -jar build/brasilian_date_formatter@0.0.1.jar

5 - funcionando ok

---

String name = "simple_date_formatter";
String version ="0.0.1";
String mainClass = "alpine.cryxie.Main";
String jarName = name + "@" + version + ".jar";
#   s i m p l e - d a t e - f o r m a t t e r  
 