JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	@javac -g Main.java
	@java Main $(FILE1) $(FILE2)