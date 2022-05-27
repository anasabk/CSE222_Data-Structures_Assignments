target:compile output

compile:
	javac -d bin src/CPS/Building/*.java src/CPS/Street/*.java src/CPS/Exceptions/*.java src/CPS/LDLinkedList/*.java
	javac -d bin -cp bin src/Driver.java

output:
	java -cp bin Driver

clean:
	rm out