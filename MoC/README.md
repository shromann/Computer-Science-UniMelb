# Problems
Here are the problems that I have solved in their respective *Haskell* programs.

## Idempotence
1. Write a function h:S→S in Haskell which is not idempotent.
2. Write two functions h1 and h2 in Haskell which are both not idempotent but the composition h1(h2) is idempotent.
3. Write a function g which is idempotent but the composition g(f) is not idempotent

## Automaton Design
Make DFA for:
1. A1 = (a(bb)\*)\*
2. A2 = ((ab)\*)\*
3. A3 = A1 * A2

## Skip
Make a function that takes in  DFA and returns another DFA, specifically its skip DFA. 
The Language transformer
skip(L) = { xy | xzy ∈ L, and x, y, ∈ ∑\*, z ∈ ∑ }

## Model Automata
I showed that finite state automata can compute propositional logic expression

## Deterministic Push Down Automata
A deterministic pushdown automaton (DPDA) is a pushdown automaton that never finds itself in a position where it can make two different transition steps. That is, from any configuration (state, input symbol, stack symbol), there is at most one state it can transition to. (If it is in a configuration where no state can be transitioned to, the DPDA immediately rejects its input.) Each transition step consumes exactly one input symbol. The stack operations are exactly as for PDAs: in one transition step, the DPDA can push to the stack, pop from the stack, do both, or leave the stack unchanged. We follow the conventions for PDA transitions, as used in lectures, that is, in stack operations we use epsilon (ϵ) to mean 'nothing'. So 'pop x' is captured as 'replace x by ϵ', 'push x' as 'replace ϵ by x', and 'leave the stack untouched' by 'replace ϵ by ϵ'. Your task is to construct two DPDAs, both having input alphabet {a, b, c}. The DPDAs should recognise the following languages:
1. L = {xc(x^r)y | x ∈ {a, b}\*, y ∈ {a, b, c}\*}
2. R = {a^i b^j c^k | i >= 0, j >= 0, k >= 0, i + j + k is even}
