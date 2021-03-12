-- we provide the same files as in assessed worksheet 4
import DFA
import NFA
import VisDFA
-- we provide solutions to worksheet 4 problems, you may find useful
import HiddenAlgorithms (determiniseNFA, minimiseDFA)
-- one more module is required for testing purposes
import Hidden

-- TODO: Complete this function
skip :: DFA -> DFA
skip dfa
    = resultDFA
    where
        -- 1. convert from DFA -> NFA
        nfa :: NFA
        nfa = nondeterminiseDFA dfa 
        
        -- 2. make two copies
        a :: GNFA (Int, Int)
        b :: GNFA (Int, Int)
        
        a = renameNFA (\n -> (n, 1))
         (statesNFA nfa, alphabetNFA nfa, transnsNFA nfa, startStatesNFA nfa, [])
         
        b = renameNFA (\n -> (n, 2))
         (statesNFA nfa, alphabetNFA nfa, transnsNFA nfa, [], acceptStatesNFA nfa)
        
        resultDFA = determiniseNFA $
         renumberNFA
            ((statesNFA a) ++ (statesNFA b), 
            (alphabetNFA a),
            (transnsNFA a) ++ (transnsNFA b) ++ (link), 
            (startStatesNFA a), 
            (acceptStatesNFA b))
        
        link = [(((fst q, 1), eps), (snd q, 2)) | q <- edges (transnsDFA dfa)]
        
