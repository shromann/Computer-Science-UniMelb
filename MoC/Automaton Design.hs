import DFA
import NFA
import VisDFA

dfa1, dfa2, dfa3 :: DFA

dfa1 = ([0,1,2,3],"ab",[((0,'a'),1),((1,'a'),1),((1,'b'),2),((2,'b'),1),((0,'b'),3),((2,'a'),3),((3,'a'),3),((3,'b'),3)],0,[0,1])

dfa2 = ([0,1,2,3],"ab",[((0,'b'),1),((1,'a'),0),((1,'b'),2),((2,'a'),0),((2,'b'),2),((0,'a'),3),((3,'a'),3),((3,'b'),3)],2,[2])

dfa3 = completeDFA $ minimiseDFA $ productDFA dfa1 dfa2
