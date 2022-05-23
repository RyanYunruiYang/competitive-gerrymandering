# competitive-gerrymandering
Spring Project on Combinatorial Game Theory. 
Class: RESEARCH TOPICS IN MODERN MATHEMATICS, HONORS (MA683HO)
Authors: Ryan Yang and Ryan Kim.

Overleaf Link
https://www.overleaf.com/read/zjmwmsrqnhcg


The main piece of code is "dpWithDFS.java" That is where dp(a,b,d) exists, which when called returns the nim-value of the game played on an axb grid with pieces of size d.

The only other piece of code worth reading is linearGerry.py, which efficiently calculates the nim value of an 1xn grid with pieces of size d. Currently, it is rigged to find values of n for which 1xn is a first player win for all values of d.
