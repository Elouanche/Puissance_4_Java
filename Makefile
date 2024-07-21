# Répertoire des sources
SRC_DIR=src

# Répertoire des classes compilées
CLASS_DIR=classes

# Liste des fichiers source
SOURCES := $(SRC_DIR)/*.java

# Commande de build
build:
	@echo "Construction du projet..."
	mkdir -p $(CLASS_DIR)
	javac -d $(CLASS_DIR) $(SOURCES)

# Commande pour nettoyer le projet (supprimer les fichiers .class)
clean:
	@echo "Nettoyage..."
	rm -rf $(CLASS_DIR)

# Commande pour exécuter le programme
run: build
	@echo "Execution du programme..."
	java -cp $(CLASS_DIR) src.Game

# Option 'phony' pour indiquer que 'clean', 'run', et 'build' ne sont pas des fichiers
.PHONY: build clean run
