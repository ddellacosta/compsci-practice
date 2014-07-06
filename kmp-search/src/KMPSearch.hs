
-- | kmpTable generates the lookup table for back-tracking
-- as part of the Knuth-Morris-Pratt string search algorithm.
kmpTable :: Eq a => [a] -> Int -> [Int] -> Int -> [Int]
kmpTable w idx t cnd
  | idx > (length w) = t
  | (w !! (pred idx)) == (w !! cnd) = kmpTable w (succ idx) (t ++ [cnd]) (succ cnd)
  | cnd > 0 = kmpTable w idx t (t !! cnd)
  | otherwise = kmpTable w (succ idx) (t ++ [0]) cnd

charIsMatch :: Eq a => [a] -> Int -> [a] -> Int -> Bool
charIsMatch str idx p ppos = (str !! idx == p !! ppos)

kmpSearch_ :: Eq a => [a] -> [a] -> Int -> Int -> [Int] -> Int
kmpSearch_ p str idx ppos tbl
  | idx >= (length str) = -1
  | charIsMatch str idx p ppos && ((succ ppos) == (length p)) = idx - ppos
  | charIsMatch str idx p ppos && ((succ ppos) /= (length p)) = kmpSearch_ p str (succ idx) (succ ppos) tbl
  | (tbl !! ppos) > -1 = kmpSearch_ p str (idx + (tbl !! ppos)) (tbl !! ppos) tbl
  | otherwise = kmpSearch_ p str (succ idx) 0 tbl

-- | Uses the Knuth-Morris-Pratt algorithm to find a pattern p in a string str.
-- returns index of first character in the sub-string match, or -1 on failure.
-- (https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm)
kmpSearch :: Eq a => [a] -> [a] -> Int
kmpSearch p str = kmpSearch_ p str 0 0 (kmpTable p 2 [-1, 0] 0)
