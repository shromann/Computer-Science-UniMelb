import DPDA
import RunDPDA  -- For testing; do not remove
import Hidden   -- For testing; do not remove
import VisDPDA

dpdaL, dpdaR :: DPDA

dpdaL = ([1,2,3,4], "abc", "abc*#", delta, 1, [4])
    where
      delta = [ 
                ((1,'a',eps), (2,'*')),
                ((1,'b',eps), (2,'#')),
                ((1,'c',eps), (4,eps)),
                
                ((2,'a',eps), (2,'a')),
                ((2,'b',eps), (2,'b')),
                ((2,'c',eps), (3,eps)),
                
                ((3,'a','a'), (3,eps)),
                ((3,'b','b'), (3,eps)),
                ((3,'a','*'), (4,eps)),
                ((3,'b','#'), (4,eps)),
                
                ((4,'a',eps), (4,eps)),
                ((4,'b',eps), (4,eps)),    
                ((4,'c',eps), (4,eps))
              ]        

dpdaR = ([1,2,3], "abc", "abc", delta, 1, [1,3])
    where
      delta = [ ((1,'a',eps), (2,'a')),
                ((1,'b',eps), (2,'b')),
                ((1,'c',eps), (2,'c')),
                
                ---
                
                ((2,'a','a'), (3,'a')),
                
                ((2,'b','a'), (3,'b')),
                ((2,'b','b'), (3,'b')),
                
                ((2,'c','a'), (3,'c')),
                ((2,'c','b'), (3,'c')),
                ((2,'c','c'), (3,'c')),
                
                ---
                
                ((3,'a','a'), (2,'a')),
                
                ((3,'b','a'), (2,'b')),
                ((3,'b','b'), (2,'b')),
                
                ((3,'c','a'), (2,'c')),
                ((3,'c','b'), (2,'c')),
                ((3,'c','c'), (2,'c'))                    
              ]        
