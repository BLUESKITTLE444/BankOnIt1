JAVAC = javac
JAVA = java

SOURCES = Admin.java Bank.java CheckingAccount.java Console.java Customer.java HasMenu.java SavingsAccount.java User.java

all:
	$(JAVAC) $(SOURCES)

run: all
	$(JAVA) Bank

clean:
	rm -f *.class
