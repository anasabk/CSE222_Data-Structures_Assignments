target:compile output

compile:
	javac -d bin src/*/*.java
	javac -d bin -cp bin src/Test/Driver.java

output:
	java -cp bin Test.Driver

clean:
	rm out