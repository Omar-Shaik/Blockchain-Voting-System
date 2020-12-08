# Blockchain Voting System
#### Authors: Sohaib Mohiuddin, Umar Riaz, Omar Shaik
---
## Introduction
The current election process consists of flaws and major risks that will impact the lives and future of a country and its people. Therefore in order to minimize these  risks, we propose a blockchain based voting system that will address the problems that occur in the current election process such as correct voter validation, accurate count of votes and security during this process.
Our project ensures that only an eligible voter with authentic validation will be able to cast a vote. A voter will be only able to vote once during the election process.  Our system also makes sure that all votes are counted accurately and results are not tampered with. This will ensure that the system set in place is a secure platform that voters can trust during the elections. 
The motivation behind the idea of this project is to restore the faith of a country in it’s leaders and to ensure the leader that is being elected represents and speaks for the interests of the majority of the population. The blockchain voting system will be a safe, secure and trustworthy voting platform that will eliminate the major risks that are attached with the traditional election process.

## How to Run
Program Files to run: Client, NodeServer
1. Compile all files in the *Blockchain-Voting-System/* directory by typing ``` javac *.java```
2. Open a seperate terminal in *Blockchain-Voting-System/* directory and run ```rmiregistry```
3. Open 4 terminals in the directory:
	1. Terminal 1: Server 1 -> ```java -Djava.security.policy="policy.txt" NodeServer <Casting Address> <Listening Address Server 2> <Listening Address Server 3> <Server Name>```
	2. Terminal 2: Server 2 -> ``` java -Djava.security.policy="policy.txt" NodeServer <Casting Address> <Listening Address Server 1> <Listening Address Server 3> <Server Name>```
	3. Terminal 3: Server 3 -> ``` java -Djava.security.policy="policy.txt" NodeServer <Casting Address> <Listening Address Server 1> <Listening Address Server 2> <Server Name>```
	4. Terminal 4: Client -> ``` java -Djava.security.policy="policy.txt" Client localhost <Server Name>```
4. Now Clients can connect to the blockchain and cast their unique vote. Their vote is added to the list of votes associated with its respective hash and uniqueness. 
