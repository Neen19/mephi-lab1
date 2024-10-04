APP_NAME = lab1
JAR_FILE = $(APP_NAME).jar

SRC_DIR = src
OUT_DIR = out
META_INF_DIR = META-INF

JAVAC = javac
JAR = jar
JAVA_FLAGS = -d $(OUT_DIR)

MAIN_SRC = $(SRC_DIR)/Main.java
MANIFEST_FILE = $(META_INF_DIR)/MANIFEST.MF

.PHONY: all jar clean run run_interactive run_cli

all: jar

compile:
	javac -d out src/Main.java src/InteractiveMode.java src/CommandLineMode.java
	jar cfm lab.jar META-INF/MANIFEST.MF -C out .
