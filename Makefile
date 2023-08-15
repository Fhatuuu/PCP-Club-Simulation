## Make file for compiling java programs and generating the #related java documentation (javadoc) 
# Fhatuwani Mokwenda
# 04-08-2023

# Define variables
JC = javac
JAVADOC = javadoc
SRCDIR = src
BINDIR = bin



SOURCES = $(shell find $(SRCDIR) -name '*.java')

# Define targets and dependencies
$(BINDIR)/%.class: $(SRCDIR)/%.java
	mkdir -p $(BINDIR)
	$(JC) -d $(BINDIR) -cp $(SOURCES) $<


# Define phony targets
.PHONY: all clean docs

# Define all target

all: $(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

# Define clean target
clean:
	rm -rf $(BINDIR) 

