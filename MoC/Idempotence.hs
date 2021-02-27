isIdempotent :: (Int -> Int) -> Bool
isIdempotent f = f 0 == f (f 0) && f 1 == f (f 1) && f 2 == f (f 2) && f 3 == f (f 3)

f :: Int -> Int
f n 
    | n `mod` 2 == 0    = n
    | otherwise         = (n + 3) `mod` 4 

h :: Int -> Int
h n 
    | n `mod` 2 == 0    = n + 1
    | otherwise         = n - 1

h1 :: Int -> Int
h1 n 
    | n `mod` 2 == 0    = n + 1
    | otherwise         = n - 1 
    
h2 :: Int -> Int
h2 n = (h1 n)
    
g :: Int -> Int
g n 
    | n `mod` 2 == 0    = (n + 3) `mod` 4 
    | otherwise         = n 
    
primes = [1..10]
conjecture k = elem (product (take k primes) + 1) primes
