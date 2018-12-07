all: Main.java
	javac Main.java

run: Main.class
	java Main

clean: Main.class
	find -name "*.class" -delete
